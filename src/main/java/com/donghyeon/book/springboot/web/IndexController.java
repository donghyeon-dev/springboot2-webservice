package com.donghyeon.book.springboot.web;

import com.donghyeon.book.springboot.config.auth.LoginUser;
import com.donghyeon.book.springboot.service.posts.PostsService;
import com.donghyeon.book.springboot.web.dto.PostsResponseDto;
import com.donghyeon.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
        // posts.service.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달!
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
