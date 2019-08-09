package com.qyl.blog.web;

import com.qyl.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        int i = 9 / 0;

        String blog = null;
        if(blog == null) {
            throw  new NotFoundException("博客不春在");
        }
        return "index";
    }

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id, @PathVariable String name) {
        System.out.println("----- index -----");
        return "index";
    }
}
