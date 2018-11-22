package com.lay.websocket.service;

import javax.websocket.Session;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 11:43 2018/11/22
 * @Modified By:IntelliJ IDEA
 */
public interface WebSocketService {
    //连接成功调用的方法
    public void onOpen(Session session);

    //连接关闭调用的方法
    public void onClose();

    //收到客户端消息后调用的方法
    public void onMessage(String message,Session session);

    //发生异常时调用的方法
    public void onError(Session session,Throwable error);

}
