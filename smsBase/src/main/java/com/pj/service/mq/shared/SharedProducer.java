package com.pj.service.mq.shared;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;

import com.aliyun.openservices.ons.api.SendResult;
import com.pj.core.util.Logs;
import com.pj.service.mq.MqConfig;
import com.pj.service.mq.MqConfig.TopicDbTag;
import com.pj.service.mq.MqConfig.TopicSmsTag;

import java.util.Date;
import java.util.Properties;

/**
 * 线程安全的MQ(TCP版本)
 * @author Fangpc
 *
 */
public enum SharedProducer {
	
	/**
     * DB实例
     */
    instanceDb,
	
	instanceSms;
	
	private Producer producerDb;
	
	private Producer producerSms;
	
	static{
		//Db实例
		Properties propertiesDb = new Properties();
		propertiesDb.put(PropertyKeyConst.ProducerId, MqConfig.PRODUCER_DB_ID);
		propertiesDb.put(PropertyKeyConst.AccessKey,MqConfig.ACCESS_KEY);
		propertiesDb.put(PropertyKeyConst.SecretKey, MqConfig.SECRET_KEY);
		propertiesDb.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
		propertiesDb.put(PropertyKeyConst.ONSAddr, MqConfig.ONSADDR);
		SharedProducer.instanceDb.producerDb = ONSFactory.createProducer(propertiesDb);
		SharedProducer.instanceDb.producerDb.start();
		//SMS实例
		Properties propertiesSms = new Properties();
		propertiesSms.put(PropertyKeyConst.ProducerId, MqConfig.PRODUCER_SMS_ID);
		propertiesSms.put(PropertyKeyConst.AccessKey,MqConfig.ACCESS_KEY);
		propertiesSms.put(PropertyKeyConst.SecretKey, MqConfig.SECRET_KEY);
		propertiesSms.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
		propertiesSms.put(PropertyKeyConst.ONSAddr, MqConfig.ONSADDR);
		SharedProducer.instanceSms.producerSms = ONSFactory.createProducer(propertiesSms);
		SharedProducer.instanceSms.producerSms.start();
	}
	
	/**
	 * 发送db消息
	 * @param json  传送的json值
	 * @param tag   分类标签
	 * @param retry 失败重复发送
	 */
	public void sendDb(String json,TopicDbTag tag,boolean retry) {
		try {
			final Message msg = new Message(
					MqConfig.TOPIC_DB,
					tag.toString(),
					json.getBytes());
//			msg.setKey("");幂等
			SendResult sendResult = SharedProducer.instanceDb.producerDb.send(msg);
			// 同步发送消息，只要不抛异常就是成功
			if (sendResult != null) {
				Logs.info(" Send mq message success. Topic is:" + MqConfig.TOPIC_DB
						+ " msgId is: " + sendResult.getMessageId());
			}
		} catch (Exception e) {
			Logs.error(" Send mq message failed. Topic is:" + MqConfig.TOPIC_DB, e);
			if(retry)//重试一次
				sendDb(json, tag,false);
		}
	}

	/**
	 * @param json  传送的json值
	 * @param tag   分类标签
	 * @param moduleCode 执行服务器code
	 * @param timeStamp 定时发送时间
	 * @param retry 失败重复发送
	 */
	public void sendSms(String json,TopicSmsTag tag,String moduleCode,Long timeStamp,boolean retry) {
		try {
			final Message msg = new Message(
					MqConfig.TOPIC_SMS,
					tag.toString()+moduleCode,
					json.getBytes());
			if(timeStamp!=null)
				msg.setStartDeliverTime(timeStamp);
			SendResult sendResult = SharedProducer.instanceSms.producerSms.send(msg);
			// 同步发送消息，只要不抛异常就是成功
			if (sendResult != null) {
				Logs.info(" Send mq message success. Topic is:" + MqConfig.TOPIC_SMS
						+ " msgId is: " + sendResult.getMessageId());
			}
		} catch (Exception e) {
			Logs.error(" Send mq message failed. Topic is:" + MqConfig.TOPIC_SMS, e);
			if(retry)//重试一次
				sendSms(json, tag,moduleCode,timeStamp,false);
		}
	}
	
//    public static void main(String[] args) {
//        //创建的 Producer 和 Consumer 对象为线程安全的，可以在多线程间进行共享，避免每个线程创建一个实例。
//        final Message msg = new Message( //
//            // Message 所属的 Topic
//        	MqConfig.TOPIC_DB,
//            // Message Tag 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在 MQ 服务器过滤
//            "TagA",
//            // Message Body 可以是任何二进制形式的数据， MQ 不做任何干预，
//            // 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
//            "Hello MQ".getBytes());
//
//        //在 thread 和 anotherThread 中共享 producer 对象，并发地发送消息至 MQ。
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    SendResult sendResult = SharedProducer.instance.producer.send(msg);
//                    // 同步发送消息，只要不抛异常就是成功
//                    if (sendResult != null) {
//                        System.out.println(" Send mq message success. Topic is:" + MqConfig.TOPIC_DB + " msgId is: " + sendResult.getMessageId());
//                    }
//                } catch (Exception e) {
//                    // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
//                    System.out.println(" Send mq message failed. Topic is:" + MqConfig.TOPIC_DB);
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//
//
//        Thread anotherThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    SendResult sendResult = SharedProducer.instance.producer.send(msg);
//                    // 同步发送消息，只要不抛异常就是成功
//                    if (sendResult != null) {
//                        System.out.println(" Send mq message success. Topic is:" + MqConfig.TOPIC_DB + " msgId is: " + sendResult.getMessageId());
//                    }
//                } catch (Exception e) {
//                    // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
//                    System.out.println(" Send mq message failed. Topic is:" + MqConfig.TOPIC_DB);
//                    e.printStackTrace();
//                }
//            }
//        });
//        anotherThread.start();
//
//        // producer 实例若不再使用时，可将 producer 关闭，进行资源释放
////        SharedProducer.instance.producer.shutdown();
//    }

}
