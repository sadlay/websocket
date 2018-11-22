package com.lay.websocket.service.impl;

import com.lay.websocket.service.WebSocketService;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 11:51 2018/11/22
 * @Modified By:IntelliJ IDEA
 */
//@Service
@ServerEndpoint("/ws")
public class WebSocketServiceImpl implements WebSocketService {
    //静态变量，用来记录当前在线连接数，应该把它涉及成线程安全的
    private static int onlineCount = 0;
    // concurrent包含的线程安全Set，用来存放每个客户端对应的WebSocketServiceImpl对象
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //连接加你成功调用的方法
    @Override
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);//加入set中
        addOnllineCount();
        System.out.println("有新连接加入！当前在线人数为：" + getOnlineCount());
        try {
            sendMessage("有新的连接加入了！！");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }


    //连接关闭调用方法
    @Override
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为：" + getOnlineCount());
    }

    //收到客户端消息后调用方法
    @Override
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息：" + message);

        webSocketSet.forEach(p -> {
            //获取当前用户名称
            String userName = p.getSession().getUserPrincipal().getName();
            System.out.println(userName);
            try {
                p.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    //当发生错误时调用方法
    @Override
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    //发送消息
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    //返回在线人数
    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    //当连接人数增加时
    private static synchronized void addOnllineCount() {
        WebSocketServiceImpl.onlineCount++;
    }

    //当连接人数减少时
    private static synchronized void subOnlineCount() {
        WebSocketServiceImpl.onlineCount--;
    }

    public Session getSession() {
        return session;
    }
}
