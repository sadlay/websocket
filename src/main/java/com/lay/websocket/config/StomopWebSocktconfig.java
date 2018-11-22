package com.lay.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 17:46 2018/11/22
 * @Modified By:IntelliJ IDEA
 */
//启用STOMP协议
@EnableWebSocketMessageBroker
@Configuration
public class StomopWebSocktconfig implements WebSocketMessageBrokerConfigurer {
    //注册服务器端点
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //增加一个聊天服务端点
        registry.addEndpoint("/socket").withSockJS();
        //增加一个用户服务端点
        registry.addEndpoint("/wsuser").withSockJS();
    }

    //定义服务器端点请求和订阅前缀
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端订阅路径前缀
        //这里设置的simple broker是指可以订阅的地址，也就是服务器可以发送的地址
         registry.enableSimpleBroker("/sub","/queue");

        //服务端点请求前缀
        registry.setApplicationDestinationPrefixes("/request");
    }
}
