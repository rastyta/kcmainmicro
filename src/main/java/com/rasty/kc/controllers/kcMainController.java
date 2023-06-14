package com.rasty.kc.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class kcMainController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/requestDemomicro")
    public ResponseEntity<String> makeRequestToApp2() {
        List<ServiceInstance> urls = discoveryClient.getInstances("kc-service");
        String demomicroUrl = urls.get(0).getUri().toString();
        String url = demomicroUrl + "/airlines/airline_10";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Process the response and return

        return response;
    }
}
