package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.demo.DemoService;

/**
 * 功能：
 *
 * xml配置方式初始化的入口：DubboNamespaceHandler
 *
 * @Author: zhaojiatao
 * @Date: 2021/8/19 14:14
 * @ClassName: ApiConsumerConfiguration
 */
public class ApiConsumerConfiguration {

    public static void main(String[] args) {
        ApplicationConfig application=new ApplicationConfig();
        application.setName("consumer-of-helloword-app");

        RegistryConfig registry=new RegistryConfig("127.0.0.1:2181");
        registry.setProtocol("zookeeper");

        ReferenceConfig<DemoService> reference=new ReferenceConfig<DemoService>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(DemoService.class);
        reference.setVersion("1.0.0");

        DemoService demoService=reference.get();
        String hello = demoService.sayHello("API demo");
        System.out.println(hello);


    }



}
