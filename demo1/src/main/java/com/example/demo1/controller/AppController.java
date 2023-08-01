package com.example.demo1.controller;

import com.example.demo1.entity.User;
import com.example.demo1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class AppController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showHome(){
        return "home";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);  //Lấy tất cả dữ liệu rồi gán vào "users", để trong file index gọi ra
        return "index";
    }
//
//    @RequestMapping(value = "/add")
//    public String addUser(Model model) {
//        model.addAttribute("user", new User());
//        return "addUser";
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//    public String editUserGet(@RequestParam("id") Long userId, Model model) {
//        Optional<User> userEdit = userService.findUserById(userId);
//        userEdit.ifPresent(user -> model.addAttribute("user", user));
//        return "editUser";
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public String editUserPost(@ModelAttribute @Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "addUser";   //or return "editUser";
//        }
//
//        User existingUser = userService.findByEmail(user.getEmail());
//        if (existingUser != null && !existingUser.getId().equals(user.getId())) {
//            model.addAttribute("emailExistsError", "This email already exists.");
//            return "editUser";
//        }
//
//        userService.saveUser(user);
//        return "redirect:/";
//    }
//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String savePost(@ModelAttribute @Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "addUser";   //or return "editUser";
//        }
//
//        if (userService.existsByEmail(user.getEmail())) {
//            model.addAttribute("emailExistsError", "This email already exists.");
//            return "addUser";
//        }
//
//        userService.saveUser(user);
//        return "redirect:/";
//    }
//
//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    public String deleteUser(@RequestParam("id") Long userId, Model model) {
//        userService.deleteUser(userId);
//        return "redirect:/";
//    }
}