package com.roon.board.web;

import com.roon.board.domain.posts.Posts;
import com.roon.board.domain.posts.PostsRepository;
import com.roon.board.web.dto.PostsSaveRequestDto;
import com.roon.board.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{    //테스트 끝나면 다 삭제하도록.
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록() throws Exception{
        String title="title";
        String content ="content";
        String author="author";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                                .title(title)
                                                .content(content)
                                                .author(author)
                                                .build();

        String url="http://localhost:"+port+"/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void Post_수정() throws Exception{
        Posts savedPost=postsRepository.save(
                Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build()
        );

        String expectedTitle="title2";
        String expectedContent="content2";

        PostsUpdateRequestDto requestDto
                = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        Long updateId=savedPost.getId();
        String url="http://localhost:"+port+"/api/v1/posts/"+updateId;

//        public class HttpEntity<T> extends Object
//        : Represents an HTTP request or response entity, consisting of headers and body.
//          Typically used in combination with the RestTemplate,

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity
                = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}