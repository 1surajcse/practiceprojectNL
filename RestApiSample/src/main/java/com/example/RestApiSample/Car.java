package com.example.RestApiSample;/*
 * @author -Suraj Tiwari
 */

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Car implements Vehicle{
    @Override
    public void drive() {
        System.out.println("Driving car");
    }
}
