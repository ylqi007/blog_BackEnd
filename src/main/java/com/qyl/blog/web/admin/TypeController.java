package com.qyl.blog.web.admin;

import com.qyl.blog.po.Type;
import com.qyl.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type t1 = typeService.getTypeByName(type.getName());
        if(t1 != null) {
            result.rejectValue("name", "Name Error", "不能重复添加分类。");
        }
        if(result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if(t == null) {
            // fail
            attributes.addFlashAttribute("message", "Added failed.");
        } else {
            // success
            attributes.addFlashAttribute("message", "Added success.");
        }

        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Type t1 = typeService.getTypeByName(type.getName());
        if(t1 != null) {
            result.rejectValue("name", "NameError", "不能重复添加分类。");
        }
        if(result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.updateType(id, type);
        if(t != null) {
            attributes.addFlashAttribute("message", "Update failed.");
        } else {
            attributes.addFlashAttribute("message", "Update Success.");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功。");
        return "redirect:/admin/types";
    }
}
