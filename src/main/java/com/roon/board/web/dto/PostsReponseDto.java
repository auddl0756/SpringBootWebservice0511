package com.roon.board.web.dto;

import com.roon.board.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
@Getter
public class PostsReponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsReponseDto(Posts entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.author=entity.getAuthor();
    }
}
