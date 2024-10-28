package com.example.moniteringservice.controller;/*
 * @author -Suraj Tiwari
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/test")
@RestController
public class SampleController {
    @GetMapping
    public String getData(){
//        for (int i = 0; i < 10000; i++) {
//            System.out.println("print");
//        }
        return "Welcome..!!";
    }


}
