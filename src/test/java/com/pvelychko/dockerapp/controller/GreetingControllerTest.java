package com.pvelychko.dockerapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPersonalAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/greeting/personal?id=3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{\"message\":\"Hi, userId 3\"}"));
    }

    @Test(expected = NestedServletException.class)
    public void getPersonalAccountNegativeId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/greeting/personal?id=-25")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void getBusinessAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/greeting/business?type=big")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{\"message\":\"Welcome, business user!\"}"));
    }
}
