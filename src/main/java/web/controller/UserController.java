package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userServiceImp;

    @Autowired
    public UserController(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userServiceImp.getUsers());
        return "index";
    }

    @GetMapping("/users/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("users", userServiceImp.getUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @GetMapping("/users/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImp.getUser(id));
        return "edit";
    }

    @RequestMapping("/users//{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userServiceImp.removeUser(id);
        return "redirect:/users";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userServiceImp.addUser(user);
        return "redirect:/users";
    }

    @RequestMapping("/users/{id}/update")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServiceImp.updateUser(id, user);
        return "redirect:/users";
    }
}
