package com.zyt.blog.web.admin;

import com.zyt.blog.po.Type;
import com.zyt.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String List(Integer pageNum, Integer pageSize, Model model){
        if (pageSize == null){
            pageSize = 5;
        }
        if (pageNum == null){
            pageNum = 1;
        }
        model.addAttribute("page",typeService.listType(pageNum,pageSize));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Integer saveType = typeService.saveType(type);
        if (saveType == 0) {
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id,RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Integer t = typeService.updateType(id, type);
        if (t == 0) {
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
