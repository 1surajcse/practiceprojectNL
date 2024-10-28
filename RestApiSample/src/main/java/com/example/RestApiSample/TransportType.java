package com.example.RestApiSample;/*
 * @author -Suraj Tiwari
 */

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class TransportType {


    @Autowired
    @Qualifier("car")
    private Vehicle vehicle;

    public void  type(){
     vehicle.drive();
    }
     @PostConstruct
    public void  first(){
        System.out.println("First");
    }

    @PreDestroy
    public void  after(){
        System.out.println("Last");
    }
}
