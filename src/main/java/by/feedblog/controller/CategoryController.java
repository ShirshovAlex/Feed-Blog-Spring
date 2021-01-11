package by.feedblog.controller;

import by.feedblog.entity.Category;
import by.feedblog.service.CategoryService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Data
@Controller
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = "/save")
    public String getSavePage() {
        return "saveCategory";
    }

    @PostMapping(path = "/save")
    public String saveAction(String name) {
        categoryService.save(new Category(name));
        return "redirect:/";
    }

    @GetMapping(path = "/update")
    public String getUpdatePage(){
        return "updateCategory";
    }

    @PostMapping(path = "/update")
    public String updateAction(long id, String name){
        categoryService.updateName(id, name);
        return "redirect:/";
    }

    @GetMapping(path = "/deleteById")
    public String getDeleteByIdPage(){
        return "deleteById";
    }

    @PostMapping(path = "/deleteById")
    public String deleteByIdAction(long id){
        categoryService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(path = "/getAllCategory")
    public String getAllPage(Model model){
        List<Category> all = categoryService.getAll();
        model.addAttribute("all", all);
        return "getAllCategory";
    }

    @GetMapping(path = "/getByIdCategory")
    public String getByIdCategory(){
        return "getCategoryById";
    }

    @PostMapping(path = "/getByIdCategory")
    public String getCategoryById(long id, Model model){
        Category byId = categoryService.getById(id);
        model.addAttribute("byId", byId);
        return "getCategoryById";
    }

    @GetMapping(path = "/getByNameCategory")
    public String getByNameCategory(){
        return "getCategoryByName";
    }

    @PostMapping(path = "/getByNameCategory")
    public String getCategoryByName(String name, Model model){
        Category byName = categoryService.getByName(name);
        model.addAttribute("byName", byName);
        return "getCategoryByName";
    }


}
