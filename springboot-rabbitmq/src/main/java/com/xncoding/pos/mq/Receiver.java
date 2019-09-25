package com.xncoding.pos.mq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息监听器
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/1
 */
@Component
public class Receiver {
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    /**
     * FANOUT广播队列监听一.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"FANOUT_QUEUE_A"})
    public void on(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("FANOUT_QUEUE_A " + new String(message.getBody()));
    }

    /**
     * FANOUT广播队列监听二.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception   这里异常需要处理
     */
    @RabbitListener(queues = {"FANOUT_QUEUE_B"})
    public void t(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("FANOUT_QUEUE_B " + new String(message.getBody()));
    }

    /**
     * DIRECT模式.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"DIRECT_QUEUE"})
    public void message(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("DIRECT " + new String(message.getBody()));
    }

    /**
     * DIRECT模式.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"TOP_QUEUE"})
    public void top_message(Message message, Channel channel) throws IOException,InterruptedException {
        String str =new String(message.getBody());
        if(str.contains("kkk1")){
            System.out.println("拒收该条消息："+str);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);//如果只有这一条消息，requeue为true的时候会造成消息重复消费
        }
        else  if(str.contains("kkk5")){
            System.out.println("异常消息："+str);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,false);//如果只有这一条消息，requeue为true的时候会造成消息重复消费
        }
        else {
            //手工应答，如果不应答，队列中的消息会一直存在，重新连接的时候,记住是重新连接的时候，会重复消费消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            log.debug("Top2 " + new String(message.getBody()));
            System.out.println("top2");
            //Thread.sleep(5000L);////为了验证exchange和queu持久化,消息不丢失
        }

    }

    /**
     * top模式.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
//    @RabbitListener(queues = {"TOP_QUEUE"})
//    public void top_message2(Message message, Channel channel) throws IOException,InterruptedException{
//        String str =new String(message.getBody());
//        if(str.contains("kkk10")){
//            System.out.println("拒收该条消息："+str);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);//如果只有这一条消息，requeue为true的时候会造成消息重复消费
//        }
//        else  if(str.contains("kkk9")){
//            System.out.println("异常消息："+str);
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,false);//如果只有这一条消息，requeue为true的时候会造成消息重复消费
//        }
//        else {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);//手工应答，如果不应答，队列中的消息会一直存在，重新连接的时候，会重复消费消息
//            log.debug("Top2 " + new String(message.getBody()));
//            System.out.println("top2");
//            Thread.sleep(5000L);////为了验证exchange和queu持久化,消息不丢失
//        }
//
//    }
}
