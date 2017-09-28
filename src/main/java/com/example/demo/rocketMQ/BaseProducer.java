package com.example.demo.rocketMQ;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
public interface BaseProducer<T,ID> {
    void sendPeopleMQ(T t) throws Exception;
}
