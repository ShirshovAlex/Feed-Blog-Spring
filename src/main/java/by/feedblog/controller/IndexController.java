package by.feedblog.controller;

import by.feedblog.dao.inmemory.InMemoryPostDao;
import by.feedblog.entity.Post;
import by.feedblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @Autowired
    private PostService postService;


    @GetMapping
    public String getIndexPage(Model model){
        List<Post> all = postService.isChecked();
        model.addAttribute("all", all);
        return "index";
    }





 //  @PostMapping
//    public String process(double num1, double num2, String operation, Model model){
//        model.addAttribute("num1", num1);
//        model.addAttribute("num2", num2);
//        model.addAttribute("operation", operation);
//        switch (operation) {
//            case "sum":
//                double v = num1 + num2;
//                model.addAttribute("message", v);
//                break;
//            case "sub":
//                double v1 = num1 - num2;
//                model.addAttribute("message", v1);
//                break;
//            case "mul":
//                double v2 = num1 * num2;
//                model.addAttribute("message", v2);
//                break;
//            case "div":
//                double v3 = num1 / num2;
//                model.addAttribute("message", v3);
//                break;
//
//        }
//        return "index";
//    }
}
