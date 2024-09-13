package com.example.demo;

import com.example.demo.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {
    private final String BASE_URL = "/customers";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getNonExistingCustomerIdShouldReturn404() throws Exception {
        long id = 99543545L;

        mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + id))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    void postRequestBodyShouldEqualToResponseBody() throws Exception {
        Customer customerDTO = new Customer("firstName","lastName","email@adress.com");

        String body = objectMapper.writeValueAsString(customerDTO);

        String response = mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Customer returnedCustomer = objectMapper.readValue(response, Customer.class);

        assertEquals(returnedCustomer,customerDTO);
    }

    @Test
    void updateFirstNameShouldOnlyUpdateThisField() throws Exception {
        long id = 1L;
        String response1 = mvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/" + id))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Customer customerDTO1 = objectMapper.readValue(response1, Customer.class);

        String first_name = customerDTO1.getFirst_name().equals("first_name") ? "name_first" : "first_name";

        customerDTO1.setFirst_name(first_name);

        Map<String,Object> data = new HashMap<>();

        data.put("first_name",first_name);
        data.put("id", id);

        String body = objectMapper.writeValueAsString(data);

        String response2 = mvc.perform(MockMvcRequestBuilders.put(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Customer customerDTO2 = objectMapper.readValue(response2, Customer.class);

        assertEquals(customerDTO1,customerDTO2);
    }
}
