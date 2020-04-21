package com.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.service.OrderService;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @NacosValue(value = "${flag:false}", autoRefreshed = true)
    private boolean flag;

    @GetMapping("getEcho")
    public String getEcho() {
        ServiceInstance instance;
        if (flag) {
            List<ServiceInstance> order = discoveryClient.getInstances("pay-application");
            instance = order.size() > 0 ? order.get(0) : null;
        } else {
            instance = loadBalancerClient.choose("pay-application");
        }
        if (instance == null) {
            throw new NullPointerException("找不到实例");
        }
        String url = instance.getUri() + "/pay/echo?name=" + "order";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }

    @GetMapping("getFeignEcho")
    public String getFeignEcho() {
        String result = orderService.getEcho("Feign");
        System.out.println(result);
        return result;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
