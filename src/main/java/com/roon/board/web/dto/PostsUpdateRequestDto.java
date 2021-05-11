package com.roon.board.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostsUpdateRequestDto {
    private String title;
    private String content;
//    private String author;

    @Builder
    public PostsUpdateRequestDto(String title,String content){
        this.title=title;
        this.content=content;
//        this.author=author;
    }


}
