package com.lay.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 11:39 2018/11/22
 * @Modified By:IntelliJ IDEA
 */
//@Configuration
public class WebSocketConfig {
    //创建服务端点
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
