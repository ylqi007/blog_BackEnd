package com.qyl.blog.web;

import com.qyl.blog.NotFoundException;
import com.qyl.blog.po.Type;
import com.qyl.blog.service.BlogService;
import com.qyl.blog.service.TagService;
import com.qyl.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        System.out.println("----- / -----");
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String serach(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        System.out.println("----- /blog ------");
        return "blog";
    }


//    @GetMapping("/")
//    public String index() {
//        int i = 9 / 0;
//
//        String blog = null;
//        if(blog == null) {
//            throw  new NotFoundException("博客不春在");
//        }
//        return "index";
//    }


//    @GetMapping("/{id}/{name}")
//    public String index(@PathVariable Integer id, @PathVariable String name) {
//        System.out.println("----- index -----");
//        return "index";
//    }
}
