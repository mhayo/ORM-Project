package org.akad.mhayo.orm_project.mybatis_module.controller;

import org.akad.mhayo.orm_project.util.config.MeasurementConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(MeasurementConfig.class)
public class UserOrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllOrdersTest() throws Exception {
        mockMvc.perform(get("/api/order"))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrderByIdTest() throws Exception {
        mockMvc.perform(get("/api/order/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitTest() throws Exception {
        mockMvc.perform(post("/api/submit/1"))
                .andExpect(status().isOk());
    }
}
