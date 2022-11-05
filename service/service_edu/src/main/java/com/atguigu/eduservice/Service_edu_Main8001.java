package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: JiangH
 * @Date: 2022/10/31/14:59
 * @Description:
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
public class Service_edu_Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Service_edu_Main8001.class,args);
    }
}
