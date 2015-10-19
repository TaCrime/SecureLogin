package com.tatesttask.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextProvider {

    private final static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static ApplicationContext getApplicationContext() {
        return context;
    }

}
