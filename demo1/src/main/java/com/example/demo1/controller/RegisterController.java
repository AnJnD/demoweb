package com.example.demo1.controller;

import com.example.demo1.entity.User;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@SpringBootApplication
public class RegisterController {

    @Autowired
    private UserService userService;


    //truyen du lieu nhap vao doi tuong model de giao tiep voi cac lop khac
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    //Sau khi dang ky thanh cong, chuyen huong toi 1 trang chuc mung
    @PostMapping("/process_register")
    public String processRegister(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();   // ma hoa mat khau trong database
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        userService.saveUser(user);
        return "register_success";
    }
}
