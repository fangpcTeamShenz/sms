/**
 * Copyright (C) 2010-2016 Alibaba Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pj.service.mq;

/**
 * @author Xiaotao
 */
public class MqConfig {
    
  //==============================商用Begin=================================//
      /**
       * 存入订单MQ
       * TA 订单接入服务器丢给业务服务器 :进入队列缓存准备存储
       * TB 执行服务器丢给业务服务器 :告诉短信发送结果
       * TC 执行服务器丢给业务服务器 :告诉短信上行结果
       */
      public static final String TOPIC_DB = "SY-DB-TOPIC";
      public static final String PRODUCER_DB_ID = "PID-DB-SY";
      public static final String CONSUMER_DB_ID_TA = "CID-DB-SY-TA";
      public static final String CONSUMER_DB_ID_TB = "CID-DB-SY-TB";
      public static final String CONSUMER_DB_ID_TC = "CID-DB-SY-TC";
      public enum TopicDbTag{
    	  TA,TB,TC
      } 
      
      /**
       * 路由发送短信MQ
       * TA 业务服务器丢给执行服务器,行业通道 :存储完毕准备调用发送短信
       * TB 业务服务器丢给执行服务器,营销通道 :存储完毕准备调用发送短信
//       * AA 回执接入服务器丢给执行服务器 :针对回调通知由执行服务器进行解析
       */
      public static final String TOPIC_SMS = "SY-SMS-TOPIC";
      public static final String PRODUCER_SMS_ID = "PID-SMS-SY";
      public static final String CONSUMER_SMS_ID = "CID-SMS-SY";
      public enum TopicSmsTag{//再加上具体执行服务器的code二位
    	  TA,TB
//    	  ,AA
	  } 
      
//      /**
//       * 回调MQ(短信发送状态回调通知)
//       * 用于接口回调修改状态
//       */
//      public static final String TOPIC_CALLBACK = "SY-CALLBACK-TOPIC";
//      public static final String PRODUCER_CALLBACK_ID = "PID-CALLBACK-SY";
//      public static final String CONSUMER_CALLBACK_ID = "CID-CALLBACK-SY";
//      
//      
//      /**
//       * 回调MQ(回复网关,短信上行通知)
//       * 用于接口回调修改状态
//       */
//      public static final String TOPIC_UPS = "SY-UPS-TOPIC";
//      public static final String PRODUCER_UPS_ID = "PID-UPS-TOPIC";
//      public static final String CONSUMER_UPS_ID = "CID-UPS-TOPIC";
      
      
      public static final String ACCESS_KEY = "LTAIawAXHvQvIDf8";
      public static final String SECRET_KEY = "a4yZZwHgG6b09QdruUAUPNIbZNu5tk";
      /**
       * ONSADDR 请根据不同Region进行配置
       * 公网测试: http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet
       * 公有云生产: http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
       * 杭州金融云: http://jbponsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
       * 深圳金融云: http://mq4finance-sz.addr.aliyun.com:8080/rocketmq/nsaddr4client-internal
       */
      
      public static final String ONSADDR = "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet";
      
      //==============================商用End=================================//
      
}
