package com.lay.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 15:11 2018/11/22
 * @Modified By:IntelliJ IDEA
 */
@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    //跳转websocket页面
    @GetMapping("/index")
    public String websocket(){
        return "websocket";
    }
}
