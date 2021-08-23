package com.test.project.service;

import com.test.project.entity.ExhangeInfo;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ThreadForUpdate extends Thread {
    @Autowired
    private ServiceFirst serviceFirst;

    @SneakyThrows
    @Override
    public void run() {
        try{
            HttpResponse<ExhangeInfo> response = Unirest.get("https://testnet.binancefuture.com/fapi/v1/exchangeInfo")
                    .asObject(ExhangeInfo.class);
            serviceFirst.saveNewInfo(response.getBody());
        }catch (Exception ex){
            log.error(ex.toString());
            System.out.println(ex);
        }
        finally {
            sleep(300000);
            run();
        }
    }

}