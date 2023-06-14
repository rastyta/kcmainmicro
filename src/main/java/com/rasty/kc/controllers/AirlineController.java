package com.rasty.kc.controllers;

import com.rasty.kc.model.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AirlineController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/airlines")
    public ResponseEntity<List<Airline>> airlines() {
        List<ServiceInstance> urls = discoveryClient.getInstances("service-kcmainmicro");
        String demomicroUrl = urls.get(0).getUri().toString();
        String url = demomicroUrl + "/airlines";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Airline>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Airline>>() {});

        // Process the response and return

        return response;
    }
}
