package com.example.demo.rocketMQ.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.example.demo.model.People;
import com.example.demo.rocketMQ.PeopleConsumer;
import com.example.demo.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
@Component
public class PeopleConsumerImpl implements PeopleConsumer {
    private static final Logger logger = LoggerFactory.getLogger(PeopleConsumerImpl.class);

    @Autowired
    private PeopleService peopleService;


    private static PeopleConsumerImpl peopleConsumer = null;
    private PeopleConsumerImpl(){}
    public static PeopleConsumerImpl getSingletonPeopleConsumer(){
        if(peopleConsumer==null){
            synchronized (PeopleConsumerImpl.class) {
                if(peopleConsumer==null) {
                    peopleConsumer = new PeopleConsumerImpl();
                }
            }
        }
        return peopleConsumer;
    }
    @Override
    public void startConsumer(){
        final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PeopleConsumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("Consumber");
        try {
            consumer.subscribe("people","*");
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs,context) ->{

                MessageExt msg = msgs.get(0);
                if(msg.getTopic().equals("people")){
                        if(msg.getTags().equals("save")){
                        logger.info("consumer:people-save=============={}",peopleService.save(JSON.toJavaObject((JSON) JSON.parse(msg.getBody()), People.class)));
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
            logger.info("people-Consumer:start");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

}
