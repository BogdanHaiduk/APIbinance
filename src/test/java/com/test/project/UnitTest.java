package com.test.project;

import com.test.project.entity.ExhangeInfo;
import com.test.project.service.ServiceFirst;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {

    @Autowired
    private ServiceFirst serviceFirst;

    @Test
    public void checkHashCode(){
        HttpResponse<ExhangeInfo> response = Unirest.get("https://testnet.binancefuture.com/fapi/v1/exchangeInfo")
                .asObject(ExhangeInfo.class);
        HttpResponse<ExhangeInfo> response2 = Unirest.get("https://testnet.binancefuture.com/fapi/v1/exchangeInfo")
                .asObject(ExhangeInfo.class);

        boolean resultHashCode;
        resultHashCode = response.hashCode() == response2.hashCode();
        Assert.assertFalse(resultHashCode);
        Assert.assertNotEquals(response, response2);
    }

    @Test
    public void checkNewInfo(){
        HttpResponse<ExhangeInfo> response = Unirest.get("https://testnet.binancefuture.com/fapi/v1/exchangeInfo")
                .asObject(ExhangeInfo.class);
        serviceFirst.saveNewInfo(response.getBody());
        Assert.assertFalse(serviceFirst.checkNewInfo(response.getBody()));
    }

}
