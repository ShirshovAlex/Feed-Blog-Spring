package by.feedblog.controller;

import by.feedblog.entity.Category;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import by.feedblog.service.CategoryService;
import by.feedblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/create")
    public String getPost(Model model) {
        List<Category> all = categoryService.getAll();
        model.addAttribute("all", all);
        return "post";
    }

    @PostMapping(path = "/create")
    public String postPost(String title, String description, long categoryId, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Category byId = categoryService.getById(categoryId);
        if (postService.save(new Post(title, description, byId, user))) {
            model.addAttribute("message", "Save is ok");
        } else {
            model.addAttribute("message", "Post is exist!");
        }
        return "redirect:/";
    }

    @GetMapping(path = "/updateDescription")
    public String getUpdatePost() {
        return "updatePostDescription";
    }

    @PostMapping(path = "/updateDescription")
    public String postUpdateDescription(long id, String name) {
        postService.updateDescription(id, name);
        return "redirect:/";
    }

    @GetMapping(path = "/getAllPosts")
    public String getGetAll(Model model) {
        List<Post> all = postService.getAll();
        model.addAttribute("all", all);
        return "getAllPosts";
    }

    @GetMapping(path = "/deleteById")
    public String getDeleteById() {
        return "deletePostById";
    }

    @PostMapping(path = "/deleteById")
    public String postDeleteById(long id) {
        postService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(path = "/deleteByTitle")
    public String getDeleteByTitle() {
        return "deletePostByTitle";
    }

    @PostMapping(path = "/deleteByTitle")
    public String postDeleteByTitle(String title) {
        postService.deleteByTitle(title);
        return "redirect:/";
    }

    @GetMapping(path = "/getById")
    public String getGetById() {
        return "getPostById";
    }

    @PostMapping(path = "/getById")
    public String postGetById(long id, Model model) {
        Post byId = postService.getById(id);
        model.addAttribute("byId", byId);
        return "getPostById";
    }

    @GetMapping(path = "/getByTitle")
    public String getGetByTitle() {
        return "getPostByTitle";
    }

    @PostMapping(path = "/getByTitle")
    public String postGetByTitle(String title, Model model) {
        Post byTitle = postService.getByTitle(title);
        model.addAttribute("byTitle", byTitle);
        return "getPostByTitle";
    }


    @GetMapping(path = "/view/{id}")
    public String postView(@PathVariable long id, Model model) {
        Post byId = postService.getById(id);
        postService.count(byId);
        model.addAttribute("post", byId);
        return "postView";
    }

    @PostMapping(path = "/like")
    public String postLike(long id) {
        Post byId = postService.getById(id);
        postService.like(byId);
        return "redirect:/post/view/" + id;
    }

    @GetMapping(path = "/edit/{id}")
    public String getEdit(@PathVariable long id, Model model) {
        Post byId = postService.getById(id);
        List<Category> all = categoryService.getAll();
        model.addAttribute("post", byId);
        model.addAttribute("categories", all);
        return "editPost";
    }

    @PostMapping(path = "/edit")
    public String postEdit(long postId, String description, long categoryId) {
        postService.updateDescription(postId, description);
        postService.updateCategory(postId, categoryService.getById(categoryId));
        postService.check(postId, false);
        return "redirect:/post/view/" + postId;
    }

    @GetMapping(path = "/moderate")
    public String getModerate(Model model) {
        List<Post> checked = postService.isUnChecked();
        model.addAttribute("checked", checked);
        return "/moderatePost";
    }

    @GetMapping(path = "/moderViewPost/{id}")
    public String getModerViewPost(@PathVariable long id, Model model) {
        Post byId = postService.getById(id);
        model.addAttribute("byId", byId);
        return "checkedPost";
    }

    @PostMapping(path = "/moderViewPost")
    public String postModerViewPost(long id) {
        postService.check(id, true);
        return "redirect:/";
    }

}
