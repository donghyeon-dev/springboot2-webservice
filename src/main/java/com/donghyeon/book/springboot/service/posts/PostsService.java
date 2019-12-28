package com.donghyeon.book.springboot.service.posts;

import com.donghyeon.book.springboot.domain.posts.Posts;
import com.donghyeon.book.springboot.domain.posts.PostsRepository;
import com.donghyeon.book.springboot.web.dto.PostsResponseDto;
import com.donghyeon.book.springboot.web.dto.PostsSaveRequestDto;
import com.donghyeon.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDTO){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        posts.update(requestDTO.getTitle(), requestDTO.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalArgumentException("해당사용자가 없습니다. id ="+id)
                );
        return new PostsResponseDto(entity);
    }
}
