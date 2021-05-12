package com.roon.board.service.posts;

import com.roon.board.domain.posts.Posts;
import com.roon.board.domain.posts.PostsRepository;
import com.roon.board.web.dto.PostsListResponseDto;
import com.roon.board.web.dto.PostsReponseDto;
import com.roon.board.web.dto.PostsSaveRequestDto;
import com.roon.board.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    //등록
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //수정
    @Transactional
    public Long update(Long id,PostsUpdateRequestDto requestDto){
        Optional<Posts> optPost = postsRepository.findById(id);
        if(optPost.isPresent()==false) {
            throw new IllegalArgumentException("해당 게시글이 없습니다");
        }

        Posts post=optPost.get();

        //update시에는 repository를 사용하지 않고
        //그저 post 객체를 update 해주는 것만으로 update가 됨!!
        //JPA 영속성 컨텍스트 때문에
        post.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }


    //조회
    public PostsReponseDto findById(Long id){
        Optional<Posts> optPost = postsRepository.findById(id);
        if(optPost.isPresent()==false) {
            throw new IllegalArgumentException("해당 게시글이 없습니다");
        }

        Posts post=optPost.get();   //entity
        return new PostsReponseDto(post);
    }

    //삭제
    @Transactional
    public void delete(Long id){
        Optional<Posts> optPost = postsRepository.findById(id);
        if(optPost.isPresent()==false) {
            throw new IllegalArgumentException("해당 게시글이 없습니다");
        }

        Posts post=optPost.get();   //entity
        postsRepository.delete(post);
    }

    //전체 조회
//    @Transactional(readOnly =true)
    public List<PostsListResponseDto> findAllDesc(){
        List<Posts> postsList = postsRepository.findAllDesc();

        return postsList.stream()
                        .map(PostsListResponseDto::new)
                        .collect(Collectors.toList());
    }

}
