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

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
@Component
public class PeopleConsumerImpl implements PeopleConsumer {
    private static final Logger logger = LoggerFactory.getLogger(PeopleConsumerImpl.class);

    private static boolean startFlg = false;

    @Autowired
    private PeopleService peopleService;

//    @Autowired
//    private static PeopleConsumer peopleConsumer;
//    private PeopleConsumerImpl(){}
//    public static PeopleConsumer getSingletonPeopleConsumer(){
//        if(peopleConsumer==null){
//            synchronized (PeopleConsumerImpl.class) {
//                ApplicationContext applicationContext = new ClassPathXmlApplicationContext("");
//            }
//        }
//        return peopleConsumer;
//    }
    @Override
    public void startConsumer(){
        if(!startFlg){
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

}
