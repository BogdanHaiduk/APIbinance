package com.test.project.controller;

import com.test.project.requestDto.ImplDto;
import com.test.project.requestDto.RequestMetaDataDto;
import com.test.project.service.ServiceFirst;
import com.test.project.service.ThreadForUpdate;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
public class Controller {

 private ServiceFirst serviceFirst;
 private ThreadForUpdate threadForUpdate;

 @Autowired
    public Controller(ServiceFirst serviceFirst, ThreadForUpdate threadForUpdate) {
        this.serviceFirst = serviceFirst;
        this.threadForUpdate = threadForUpdate;
    }

    @PostMapping("/startUpdate")
    public void startUpdate () {
        if (!threadForUpdate.isAlive()) threadForUpdate.start();
        else log.info("threadForUpdate.isAlive() -> " + threadForUpdate.isAlive());
    }

    @GetMapping("/orderBook")
    public ResponseEntity<Object> orderBook(@RequestParam(name = "symbol") String symbol,
                                            @RequestParam(name = "limit") int limit){
        HttpResponse<Object> response = Unirest
                .get("https://testnet.binancefuture.com/fapi/v1/depth?symbol="
                        + symbol + "&" + "limit=" + limit)
                .asObject(Object.class);
        return new ResponseEntity(response.getBody(), HttpStatus.OK);

    }
    @GetMapping("/metaData")
    public ResponseEntity<Map<String,Map<String, RequestMetaDataDto>>> metaData(){
        return new ResponseEntity(ImplDto.impl(serviceFirst.lastElement()), HttpStatus.OK);
    }
}
