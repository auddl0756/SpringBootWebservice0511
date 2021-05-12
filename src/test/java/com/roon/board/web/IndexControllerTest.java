package com.roon.board.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지() {
        assertThat(restTemplate).isNotEqualTo(null);

        //정상 작동
//        String body = restTemplate.getForObject("/",String.class);

//        //오류
        String body = restTemplate.getForObject("http://localhost:8080/",String.class);
        assertThat(body).contains("spring boot web service");
    }

}