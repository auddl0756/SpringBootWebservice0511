package com.roon.board.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}