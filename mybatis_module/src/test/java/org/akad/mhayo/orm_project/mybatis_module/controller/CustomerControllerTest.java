package org.akad.mhayo.orm_project.mybatis_module.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllCustomersTest() throws Exception {
        mockMvc.perform(get("/api/customer"))
                .andExpect(status().isOk());
    }


    @Test
    public void getCustomerByIdTest() throws Exception {
        mockMvc.perform(get("/api/customer/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void createCustomerTest() throws Exception {
        String customerJson = """
            {
                "username": "Donny.darko",
                "name": "Bard",
                "surname": "User",
                "birthday": "1991-01-01",
                "country": "Test Country",
                "zipcode": "12345",
                "city": "Test City",
                "street": "Test Street",
                "housenumber": 123
            }
            """;

        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCustomerTest() throws Exception {
        String customerJson = """
            {
                "id": 1,
                "username": "Darko",
                "name": "Made",
                "surname": "User",
                "birthday": "1990-01-01",
                "country": "Updated Country",
                "zipcode": "54321",
                "city": "Updated City",
                "street": "Updated Street",
                "housenumber": 321
            }
            """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isOk());
    }


}
