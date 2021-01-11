package by.feedblog.controller;

import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import by.feedblog.service.CategoryService;
import by.feedblog.service.PostService;
import by.feedblog.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Data
@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final CategoryService categoryService;

    @GetMapping(path = "/registration")
    public String getUserRegistration() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String postUserRegistration(String name, String login, String password, Model model) {
        boolean save = userService.save(new User(name, login, password));
        if (save) {
            return "redirect:/";
        } else {
            model.addAttribute("message", "Login is exist!");
        }
        return "registration";
    }

    @GetMapping(path = "/authorization")
    public String getUserAuthorization() {
        return "authorization";
    }

    @PostMapping(path = "/authorization")
    public String postUserAuthorization(String login, String password, Model model, HttpSession httpSession) {
        User byLogin = userService.getByLogin(login);
        if (byLogin.getPassword().equals(password)) {
            httpSession.setAttribute("user", byLogin);
            return "redirect:/";
        } else {
            model.addAttribute("message", "Wrong password");
        }
        return "authorization";
    }

    @GetMapping(path = "/logout")
    public String getUserLogout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping(path = "/account")
    public String getUserAccount(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        List<Post> allByUser = postService.getAllByUser(user);
        model.addAttribute("all", allByUser);
        return "account";
    }


    @GetMapping(path = "/updateName")
    public String getUpdateName() {
        return "updateUserName";
    }

    @PostMapping(path = "/updateName")
    public String postUpdateName(String name, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        user.setName(name);
        userService.updateName(name, user.getId());
        return "redirect:/user/account";
    }

    @GetMapping(path = "/updatePassword")
    public String getUpdatePassword() {
        return "updateUserPassword";
    }

    @PostMapping(path = "/updatePassword")
    public String postUpdatePassword(String password, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        user.setPassword(password);
        userService.updatePassword(user.getId(), password);
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping(path = "/deleteById")
    public String getDeleteById(){
        return "deleteUserById";
    }

    @PostMapping(path = "/deleteById")
    public String postDeleteById(long id){
        userService.deleteById(id);
        return "redirect:/";
    }


    @GetMapping(path = "/deleteByLogin")
    public String getDeleteByLogin(){
        return "deleteUserByLogin";
    }

    @PostMapping(path = "/deleteByLogin")
    public String postDeleteByLogin(String login){
        userService.deleteByLogin(login);
        return "redirect:/";
    }

    @GetMapping(path = "/getAll")
    public String getGetAllUsers(Model model){
        List<User> all = userService.getAll();
        model.addAttribute("all", all);
        return "getAllUsers";
    }

    @GetMapping(path = "/getAllByName")
    public String getGetAllByName(){
        return "/getAllUsersByName";
    }

    @PostMapping(path = "getAllByName")
    public String postGetAllByName(String name, Model model){
        List<User> allByName = userService.getAllByName(name);
        model.addAttribute("all", allByName);
        return "/getAllUsersByName";
    }

}
