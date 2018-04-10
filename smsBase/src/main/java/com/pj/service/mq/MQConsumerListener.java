package com.pj.service.mq;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.PropertyValueConst;
import com.pj.service.mq.service.MQCallBack;

/**
 * 接收者
 * @author Xiaotao
 *
 */
public class MQConsumerListener {
	
	private static Logger log = Logger.getLogger(MQConsumerListener.class);

	private static Properties props;
	
	private static String LOG_INFO = "启动集群订阅";
	
	static {
		props = new Properties();
		props.put(PropertyKeyConst.AccessKey, MqConfig.ACCESS_KEY);
		props.put(PropertyKeyConst.SecretKey, MqConfig.SECRET_KEY);
	}

	/**
	 * 集群订阅消息(竞争者模式)
	 * @param topic MQ主题
	 * @param tag  二级类别
	 * @param callBack 回调包名+类名
	 * @param consumerId 订阅者ID
	 */
	public static void MQListener(String topic, String tag, final MQCallBack callBack,String consumerId){
		props.put(PropertyKeyConst.ConsumerId, consumerId);
		props.put(PropertyKeyConst.ONSAddr,MqConfig.ONSADDR);
//		props.put(PropertyKeyConst.ConsumeThreadNums,20);//设置消费线程数
		Consumer consumer = ONSFactory.createConsumer(props);
		consumer.subscribe(topic, tag, new MessageListener() {
            public Action consume(Message message, ConsumeContext context) {
            	boolean bool = callBack.exec(message, context);
            	Action result = Action.CommitMessage;
            	if(!bool){
            		result = Action.ReconsumeLater;
            	}
                return result;
            }
        });
		consumer.start();
		log.info(LOG_INFO);
		System.out.println("start mq consumer success.");
	}
	
	/**
	 * 广播的方式发送MQ
	 * @param topic MQ主题
	 * @param tag 二级类别
	 * @param callBack 回调包名+类名
	 * @param consumerId 订阅者ID
	 */
	public static void MQPubSubListener(final String topic,final String tag, final MQCallBack callBack,String consumerId){
		props.put(PropertyKeyConst.MessageModel,PropertyValueConst.BROADCASTING);
        Consumer consumer = ONSFactory.createConsumer(props);
        consumer.subscribe(topic, tag, new MessageListener() {
            public Action consume(Message message, ConsumeContext context) {
            	boolean bool = callBack.exec(message, context);
            	Action result = Action.CommitMessage;
            	if(!bool){
            		result = Action.ReconsumeLater;
            	}
                return result;
            }
        });
        consumer.start();
        log.info(String.format(LOG_INFO, topic,tag,consumerId,callBack));
	}
	
}
