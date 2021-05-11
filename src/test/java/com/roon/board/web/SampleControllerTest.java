package com.roon.board.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SampleController.class)
public class SampleControllerTest {
    private final static String template ="hello %s";

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello이름_리턴() throws Exception {
        String name="leemr";

        mvc.perform(get("/hello?name="+name))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello "+name));
    }

    @Test
    public void helloDTO_리턴() throws Exception{
        String name="hello";
        int amount=1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}