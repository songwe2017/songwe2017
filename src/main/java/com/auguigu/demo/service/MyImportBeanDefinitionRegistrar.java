package com.auguigu.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author Songwe
 * @date 2021/1/5 17:39
 */
@Slf4j
@Component
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        log.info("MyImportBeanDefinitionRegistrar...registerBeanDefinitions");
        RootBeanDefinition myImportSelecter = new RootBeanDefinition("MyImportSelecter");
        registry.registerBeanDefinition("MyImportSelecter", myImportSelecter);
    }
}
