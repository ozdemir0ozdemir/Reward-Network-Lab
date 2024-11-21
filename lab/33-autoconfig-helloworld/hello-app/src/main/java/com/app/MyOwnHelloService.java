package com.app;

import com.lib.HelloService;

public class MyOwnHelloService implements HelloService {

    @Override
    public void greet() {
        System.out.println("HelloService: Hi from MyOwnHelloService");
    }
}
