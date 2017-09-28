package com.example.demo.listener;

import com.example.demo.rocketMQ.impl.PeopleConsumerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        PeopleConsumerImpl.getSingletonPeopleConsumer().startConsumer();
        logger.info("RunListener ==== {}","ok/start");
    }
}
