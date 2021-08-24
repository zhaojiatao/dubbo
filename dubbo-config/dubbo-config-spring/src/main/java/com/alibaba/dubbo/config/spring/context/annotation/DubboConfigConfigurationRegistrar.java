/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.config.spring.context.annotation;

import com.alibaba.dubbo.config.AbstractConfig;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import static com.alibaba.dubbo.config.spring.util.AnnotatedBeanDefinitionRegistryUtils.registerBeans;

/**
 * ImportBeanDefinitionRegistrar类只能通过其他类@Import的方式来加载，通常是启动类或配置类。
 * 使用@Import，如果括号中的类是ImportBeanDefinitionRegistrar的实现类，则会调用接口方法，将其中要注册的类注册成bean。
 * 实现该接口的类拥有注册bean的能力。
 */

/**
 * Dubbo {@link AbstractConfig Config} {@link ImportBeanDefinitionRegistrar register}
 *
 * @see EnableDubboConfig
 * @see DubboConfigConfiguration
 * @see Ordered
 * @since 2.5.8
 */
public class DubboConfigConfigurationRegistrar implements ImportBeanDefinitionRegistrar {

    /***
    * <p>
    * 功能：
    * </p>
    *
    * @author zhaojiatao
    * @Date 09:47 2021/8/24
    * @param importingClassMetadata 当前类的注解信息
	* @param registry 注册类，其registerBeanDefinition()可以注册bean
    * @return void
    */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableDubboConfig.class.getName()));
        //获取@EnableDubboConfig注解的multiple属性值，默认是true
        boolean multiple = attributes.getBoolean("multiple");

        // Single Config Bindings
        registerBeans(registry, DubboConfigConfiguration.Single.class);

        if (multiple) { // Since 2.6.6 https://github.com/apache/incubator-dubbo/issues/3193
            registerBeans(registry, DubboConfigConfiguration.Multiple.class);
        }
    }
}