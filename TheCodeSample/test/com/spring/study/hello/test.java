package com.spring.study.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.spring.study.hello.Action;

public class test
{
    public void testQuickStart()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("ioc.xml");
        
        Action action = (Action) ctx.getBean("TheAction");
        
        System.out.println(action.execute(" Rod Johnson"));
    }
    
    public static void main(String[] args)
    {
        test test = new test();
        
        test.testQuickStart();
    }
}
