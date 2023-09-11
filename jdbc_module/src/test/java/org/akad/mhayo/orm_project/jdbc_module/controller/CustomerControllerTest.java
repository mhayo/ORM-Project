package org.akad.mhayo.orm_project.jdbc_module.controller;


import org.akad.mhayo.orm_project.jdbc_module.datasource.DataBaseConnection;
import org.akad.mhayo.orm_project.jdbc_module.datasource.InitDataBase;
import org.akad.mhayo.orm_project.util.config.MeasurementConfig;
import org.akad.mhayo.orm_project.util.exceptions.DataBaseConnectException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Connection;

import static org.akad.mhayo.orm_project.util.constants.ExceptionsConstants.CONNECTION_ERROR;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(MeasurementConfig.class)
public class CustomerControllerTest {


    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        Logger logger = org.slf4j.LoggerFactory.getLogger(CartControllerTest.class);

        logger.info("Setting up database");

        try {
            Connection connection = DataBaseConnection.getConnection();
            InitDataBase.dbInit(connection);
        } catch (Exception e) {
            throw new DataBaseConnectException(CONNECTION_ERROR);
        }
    }


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
