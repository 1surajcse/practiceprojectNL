package com.backbase.userservice.controller;/*
 * @author -Suraj Tiwari
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public String hello(){
        return "Welcome to the app";

    }
}
