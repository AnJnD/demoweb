package com.example.demo1.security;

import com.example.demo1.entity.User;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username); // Change from findByEmail to findByUsername
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        System.out.println("this is user:" + username);
        return new CustomUserDetails(user);
    }
}
