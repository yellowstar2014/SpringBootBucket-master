package com.xncoding.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * STOMP协议的WebStocket
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/28
 */
@Configuration
@EnableWebSocketMessageBroker//注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * 注册STOMP协议的节点，并指定映射的URL
     * @param stompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //addEndpoint(“/simple”).withSockJS(); 用来注册STOMP协议节点，同时指定使用SockJS
        stompEndpointRegistry.addEndpoint("/simple")
                .setAllowedOrigins("*") //解决跨域问题
                .withSockJS();
    }

    /**
     * 配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
    }
}
