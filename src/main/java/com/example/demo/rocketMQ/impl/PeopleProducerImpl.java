package com.example.demo.rocketMQ.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.example.demo.model.People;
import com.example.demo.rocketMQ.PeopleProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
@Service
@ConfigurationProperties(prefix = "host")
public class PeopleProducerImpl implements PeopleProducer {
    private static final Logger logger = LoggerFactory.getLogger(PeopleProducerImpl.class);
    private String ip;
    private String port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    private static DefaultMQProducer producer = null;

    private DefaultMQProducer getProducer() {
        if(producer == null) {
            synchronized(this){
                if(producer == null) {
                    producer = new DefaultMQProducer("PeopleProducer");
                    producer.setNamesrvAddr(ip + ":" + port);
                    producer.setInstanceName("producer");
                    try {
                        producer.start();
                    } catch (MQClientException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return producer;
    }

    @Override
    public void sendPeopleMQ(People people) throws Exception {
        Message message = new Message("people", "save", "people" + people.getId(), JSON.toJSONBytes(people));
        SendResult sendResult = getProducer().send(message);
        logger.info("sendPeopleMQ ==========>{}", sendResult);
    }
}
