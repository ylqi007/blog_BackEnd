package com.qyl.blog.web;

import com.qyl.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
//        int i = 9 / 0;

        String blog = null;
        if(blog == null) {
            throw  new NotFoundException("博客不春在");
        }
        return "index";
    }
}
