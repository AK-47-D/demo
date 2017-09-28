package com.example.demo.listener;

import com.example.demo.rocketMQ.PeopleConsumer;
import com.example.demo.rocketMQ.impl.PeopleConsumerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;

/**
 * Created by wb-cmx239369 on 2017/9/28.
 */
@WebListener
@Component
public class RunListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(RunListener.class);

    @Autowired
    private PeopleConsumer peopleConsumer;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        peopleConsumer.startConsumer();
        logger.info("RunListener ==== {}","ok/start");
    }
}
