package com.pj.app.server.netty;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.pj.app.constant.AppConstants;
import com.pj.app.service.OrderService;
import com.pj.core.constant.NettyAppConstants;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.model.Result;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpRequest;
  

public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {
	  private static Logger logger  = LoggerFactory.getLogger(HttpServerInboundHandler.class);  
	    private ByteBufToBytes reader;  
	    private HttpRequest request;
	    @Override  
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
	        if (msg instanceof HttpRequest) {  
	            request = (HttpRequest) msg;  
	            if (HttpHeaders.isContentLengthSet(request)) {  
	                reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(request));  
	            }  
	        }  
	        if (msg instanceof HttpContent) {
	            HttpContent httpContent = (HttpContent) msg;  
	            ByteBuf content = httpContent.content();  
	            if(NettyAppConstants.HTTP_PARAMS_EMPTYBYTEBUFBE.equals(content.toString())){
	            }else{
	            	reader.reading(content);  
	            	content.release();  
	            }
	            if(reader==null){
	            	writeAndFlush(ctx, JSON.toJSONString(new Result(HttpStatusEnums.ERROR_BODY_NULL)));
	            }else if(reader!=null && reader.isEnd()) {
					Result result = null;
					String readerStr = new String(reader.readFull());
					try {
//						public static BlockingQueue<UpstreamModel> concurrentMapUpstreamCounts = new LinkedBlockingQueue<>();  性能出现瓶颈的时候用队列这个
						if(StringUtils.isNotBlank(readerStr)){
							String requestUrl = request.getUri().substring(1);
							InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
							String clientIP = insocket.getAddress().getHostAddress();
							if (requestUrl.equals(AppConstants.APP_RECEIVE_KEY)) {
								result = OrderService.receive(readerStr, clientIP);
							} else if (requestUrl.equals(AppConstants.APP_BALANCE_KEY)) {
								result = OrderService.findBill(readerStr, clientIP);
							} else if (requestUrl.equals(AppConstants.APP_STATE_KEY)) {
								
							} else {
								result = new Result(HttpStatusEnums.ERROR_FORMAT);
							}
						}else{
							result = new Result(HttpStatusEnums.ERROR_BODY_NULL);
						}
					} catch (Exception e) {
						logger.error("channelRead()", e);
						result = new Result(HttpStatusEnums.ERROR);
					}
					writeAndFlush(ctx, JSON.toJSONString(result));
				}
	        }  
	    }
	    
	    /**
	     * 返回结果,清空通道
	     * @param ctx
	     * @param res
	     */
	    private void writeAndFlush(ChannelHandlerContext ctx,String res) throws UnsupportedEncodingException{
			FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(res.getBytes(NettyAppConstants.ENCODING_TYPE_UTF_8)));
			response.headers().set(CONTENT_TYPE, NettyAppConstants.HTTP_PARAMS_HEADERS_CONTENT_TYPE);
			response.headers().set(CONTENT_LENGTH,response.content().readableBytes());
			if (HttpHeaders.isKeepAlive(request)) {
				response.headers().set(CONNECTION, Values.KEEP_ALIVE);
			}
			ctx.writeAndFlush(response);
	    }
	  
	    @Override  
	    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
	        ctx.flush();
	    }  


	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	    	logger.error(cause.getMessage());
	        ctx.close();
	    }
	  
}
