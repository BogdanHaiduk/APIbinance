package com.test.project;

import com.test.project.entity.ExhangeInfo;
import com.test.project.repo.ExhangeInfoRepo;
import com.test.project.service.ServiceFirst;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTestRepo {

    @Autowired
    private ServiceFirst serviceFirst;
    @MockBean
    private ExhangeInfoRepo exhangeInfoRepo;

    @Test
    public void testMethodSave(){
        HttpResponse<ExhangeInfo> response = Unirest.get("https://testnet.binancefuture.com/fapi/v1/exchangeInfo")
                .asObject(ExhangeInfo.class);
        serviceFirst.saveNewInfo(response.getBody());
        Mockito.verify(exhangeInfoRepo, Mockito.times(1)).save(any());
    }

    @Test
    public void testMethodSaveNewInfo(){
        HttpResponse<ExhangeInfo> response = Unirest.get("https://testnet.binancefuture.com/fapi/v1/exchangeInfo")
                .asObject(ExhangeInfo.class);
        List<ExhangeInfo>list = new ArrayList<>();
        list.add(response.getBody());
        Mockito.doReturn(list)
                .when(exhangeInfoRepo)
                .findAll();
        serviceFirst.saveNewInfo(response.getBody());
        Mockito.verify(exhangeInfoRepo, Mockito.times(0)).save(any());
    }
}
