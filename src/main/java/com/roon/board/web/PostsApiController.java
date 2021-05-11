package com.roon.board.web;

import com.roon.board.service.posts.PostsService;
import com.roon.board.web.dto.PostsSaveRequestDto;
import com.roon.board.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,@RequestBody PostsUpdateRequestDto dto){
        return postsService.update(id,dto);
    }
}
