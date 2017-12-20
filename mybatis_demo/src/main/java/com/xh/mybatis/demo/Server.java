package com.xh.mybatis.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 转码模板微服务程序入口
 * @author : zhouliming
 * @version 1.0
 * @date 创建时间：2017年11月8日 上午10:09:57
 */

@SpringBootApplication
@EnableCaching
@MapperScan("com.xh.mybatis.demo.dao.*")
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}
