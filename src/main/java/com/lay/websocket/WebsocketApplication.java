package com.lay.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class WebsocketApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }

    //定义3个可以登陆的内存用户
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码加密器
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        //加入3个内存用户，密码分别为加密后的 p1,p2,p3
        //可以通过passwordEncoder.encode("p1")这样获得加密后的密码
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("user1").password(passwordEncoder.encode("p1"))
                .roles("USER").and()
                .withUser("user2").password(passwordEncoder.encode("p2"))
                .roles("USER").and()
                .withUser("user3").password(passwordEncoder.encode("p3"))
                .roles("USER");
    }
}
