package com.example.demo1.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") // chỉ định Database nào được kết nối
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This item is required.")
    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "This item is required.")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "This item is required.")
    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @NotBlank
    @Column(name = "username", unique = true)
    private String username;

}
