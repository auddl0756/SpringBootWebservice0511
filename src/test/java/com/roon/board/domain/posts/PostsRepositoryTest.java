package com.roon.board.domain.posts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository repository;

    @After
    public void cleanUp(){
        repository.deleteAll();
    }

    //C R U D 테스트 해야겠지
    @Test
    public void 글작성(){
        Long id=1L;
        String title="test title";
        String content="test content";
        String author="test author";

        Posts post = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();


//        System.out.println(post.getTitle());

        repository.save(post);

//        Posts post_get = repository.getOne(id);   //이거 쓰면 org.hibernate.LazyInitializationException 발생함.
        Posts post_get = repository.findAll().get(0);
        assertThat(post_get.getTitle()).isEqualTo(title);
        assertThat(post_get.getContent()).isEqualTo(content);
        assertThat(post_get.getAuthor()).isEqualTo(author);
    }

    @Test
    public void 글수정(){
//        Long id=1L;
        String title="test title";
        String content="test content";
        String author="test author";

        Posts post = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

//        System.out.println(post.getTitle());

        repository.save(post);

        Posts post_get = repository.findAll().get(0);
        assertThat(post_get.getTitle()).isEqualTo(title);
        assertThat(post_get.getContent()).isEqualTo(content);
        assertThat(post_get.getAuthor()).isEqualTo(author);


        String title_changed = "test changed title";

        post_get.updateTitle(title_changed);

        assertThat(post_get.getTitle()).isEqualTo(title_changed);

    }

    @Test
    public void BaseTimeEntity_등록(){
        LocalDateTime beforeTime = LocalDateTime.now();

        repository.save(
                Posts.builder()
                    .title("t")
                    .content("c")
                    .author("a")
                    .build()
        );

        List<Posts> postsList = repository.findAll();
        Posts post = postsList.get(0);
        System.out.println("before time = "+beforeTime);
        System.out.println("saved time ="+post.getCreatedDate());
        System.out.println(post.getModifiedDate());

        assertThat(post.getCreatedDate()).isAfter(beforeTime);
        assertThat(post.getModifiedDate()).isAfter(beforeTime);
    }
}