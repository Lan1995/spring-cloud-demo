package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pay")
public class PayController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("echo")
    public String getEcho(@RequestParam("name") String name) {
        return port + "-" + name + "provider";
    }
}
