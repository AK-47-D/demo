package com.example.demo.rocketMQ.impl;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
public class TestConsumer {
    public static void main(String[] args) throws Exception{
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                "ConsumerGroupName");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("Consumber");

        /**
         * 订阅指定topic下tags分别等于TagA或TagC或TagD
         */
        consumer.subscribe("TopicTest1","TagA || TagC || TagD");
        /**
         * 订阅指定topic下所有消息<br>
         * 注意：一个consumer对象可以订阅多个topic
         */
        consumer.subscribe("TopicTest2","*");
        consumer.subscribe("TopicTest3","*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                System.out.println(Thread.currentThread().getName()
                        +" Receive New Messages: " + msgs.size());

                MessageExt msg = msgs.get(0);
                if(msg.getTopic().equals("TopicTest1")) {
                    //执行TopicTest1的消费逻辑
                    if(msg.getTags() != null && msg.getTags().equals("TagA")) {
                        //执行TagA的消费
                        System.out.println(new String(msg.getBody()));
                    }else if (msg.getTags() != null
                            &&msg.getTags().equals("TagC")) {
                        //执行TagC的消费
                        System.out.println(new String(msg.getBody()));
                    }else if (msg.getTags() != null
                            &&msg.getTags().equals("TagD")) {
                        //执行TagD的消费
                        System.out.println(new String(msg.getBody()));
                    }
                }else if (msg.getTopic().equals("TopicTest2")) {
                    System.out.println(new String(msg.getBody()));
                }else if(msg.getTopic().equals("TopicTest3")){
                    System.out.println(new String(msg.getBody()));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

            }
        });

        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         */
        consumer.start();

        System.out.println("ConsumerStarted.");
    }
}