package com.pj.service.mq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.pj.core.util.threadPool.ThreadPoolProxyFactory;

/**
 * MQ发送者(长连接,暂时用http短连接版本发送的)
 * @author Xiaotao
 *
 */
public class MQProducerUtils {

	private static Logger log = Logger.getLogger(MQProducerUtils.class);

	private static Properties props;

	static {
		props = new Properties();
//		props.put(PropertyKeyConst.ProducerId, MqConfig.PRODUCER_ID); 暂时删除这句,看看有什么问题没,
		props.put(PropertyKeyConst.AccessKey, MqConfig.ACCESS_KEY);
		props.put(PropertyKeyConst.SecretKey, MqConfig.SECRET_KEY);
		props.put(PropertyKeyConst.ONSAddr, MqConfig.ONSADDR);
	}

	/**
	 * 发送集群MQ 
	 * @param topic 可理解为一级类别
	 * @param tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤
	 * @param body 消息体
	 * @return 消息的唯一ID 如果返回为null就表示发送失败，主流程不需要管返回
	 */
	public static String sendMQ(String topic, String tag, Object obj) {
		Message msg = new Message(topic, tag, ObjectAndByte.toByteArray(obj));
		return sendMQ(msg);
	}
	
	/**
	 * 设置延迟发送MQ
	 * @param topic 可理解为一级类别
	 * @param tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤
	 * @param body 消息体
	 * @param delayTime 延迟的时间单位毫秒
	 * @return 消息的唯一ID 如果返回为null就表示发送失败，主流程不需要管返回
	 */
	public static String sendMQDelayTime(String topic, String tag, String body,long delayTime){
		Message msg = new Message(topic, tag, body.getBytes());
		msg.setStartDeliverTime(System.currentTimeMillis() + delayTime);
		return sendMQ(msg);
	}

	/**
	 * 指定时间发送MQ
	 * @param topic 可理解为一级类别
	 * @param tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤
	 * @param body 消息体
	 * @param date 指定时间发送消息
	 * @return 消息的唯一ID 如果返回为null就表示发送失败，主流程不需要管返回
	 */
	public static String sendMqDelayDate(String topic, String tag, String body,Date date){
		Message msg = new Message(topic, tag, body.getBytes());
		msg.setStartDeliverTime(date.getTime());
		return sendMQ(msg);
	}
	
	/**
	 * 快速集群发送MQ
	 * @param topic
	 * @param tag
	 * @param infos
	 * @return
	 */
	public static List<SendResult> sendMqSpeed(String topic, String tag, List infos){
		if(CollectionUtils.isEmpty(infos)){
			return null;
		}
		List<SendResult> results = new ArrayList<SendResult>();
		try {
			Producer producer = ONSFactory.createProducer(props);
			// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
			producer.start();
			for(Object s : infos){
				Message msg = new Message(topic, tag, ObjectAndByte.toByteArray(s));
				SendResult sendResult = producer.send(msg);
				results.add(sendResult);
			}
			producer.shutdown();
		} catch (Exception e) {
			log.error("快速发送MQ异常：" + e.getMessage());
		}
		return results;
	}
	
	/**
	 * 快速集群发送MQ(线程)
	 * @param topic
	 * @param tag
	 * @param infos
	 * @return
	 */
	public static void sendMqSpeedThread(final String topic, final String tag, final List infos){
		if(!CollectionUtils.isEmpty(infos)){
			ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
				public void run() {
					List<SendResult> results = new ArrayList<SendResult>();
					try {
						Producer producer = ONSFactory.createProducer(props);
						// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
						producer.start();
						for(Object s : infos){
							Message msg = new Message(topic, tag, ObjectAndByte.toByteArray(s));
							SendResult sendResult = producer.send(msg);
							results.add(sendResult);
						}
						producer.shutdown();
						for (SendResult sendResult : results) {
							log.info(" 【MQ返回】:"+sendResult.getMessageId());
						}
					} catch (Exception e) {
						log.error("快速发送MQ异常(线程)：" ,e);
					}
				}
			});
		}
	}
	
	/**
	 * 快速集群发送MQ（Test）
	 * @param topic
	 * @param tag
	 * @param infos
	 * @return
	 */
	public static List<SendResult> sendMqSpeedStr(String topic, String tag){
		List<SendResult> results = new ArrayList<SendResult>();
		try {
			Producer producer = ONSFactory.createProducer(props);
			// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
			producer.start();
			for(int i = 0; i < 100; i++){
				Message msg = new Message(topic, tag, "content yes no".getBytes());
				SendResult sendResult = producer.send(msg);
				results.add(sendResult);
			}
			producer.shutdown();
		} catch (Exception e) {
			log.error("快速发送MQ(Test)异常：" + e.getMessage());
		}
		return results;
	}

	/**
	 * 发送消息
	 * @param msg
	 * @return
	 */
	private static String sendMQ(Message msg) {
		try {
			Producer producer = ONSFactory.createProducer(props);
			// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
			producer.start();
			// 发送消息，只要不抛异常就是成功
			SendResult sendResult = producer.send(msg);
			producer.shutdown();
			return sendResult.getMessageId();
		} catch (Exception e) {
			// 吃掉这个异常不影响主流程
			log.error("###########消息队列发送失败：" + e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) {
//		for (int i = 0; i < 10; i++) {
//			String messageId = sendMQ(MqConfig.TOPIC, MqConfig.TAG, "mq send test");
//			System.out.println(messageId);
//        }
//		List<SendResult> list = sendMqSpeedStr(MqConfig.TOPIC, MqConfig.TAG);
//		System.out.println(list.size());
	}
}
