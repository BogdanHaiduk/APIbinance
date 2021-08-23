package com.test.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.requestDto.ImplDto;
import com.test.project.service.ServiceFirst;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ServiceFirst serviceFirst;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void startUpdate() throws Exception{
        for(int i=0;i<2;i++){
            this.mockMvc.perform(post("/startUpdate"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }
    @Test
    public void orderBook() throws Exception{
        this.mockMvc.perform(get("/orderBook?symbol=BTCUSDT&limit=10"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void metaData() throws Exception{
          this.mockMvc.perform(get("/metaData"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(ImplDto.impl(serviceFirst.lastElement()))));
    }
}