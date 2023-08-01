package com.example.demo1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//    @Autowired
//    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsManager userDetailsManager(){
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
@Bean
public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/register").permitAll() // allow everyone to access /register
                                .antMatchers("/process_register/**").permitAll()
                                .antMatchers("/webjars/**").permitAll() // allow everyone to access webjars
                                .antMatchers("/css/**", "/js/**", "/images/**").permitAll() // allow everyone to access static resources
//                                .antMatchers("/admin/**").hasRole("ADMIN") // only allow ADMIN to access /admin and its subpaths
//                                .antMatchers("/users").hasAnyRole("EMPLOYEE", "ADMIN") // only allow EMPLOYEE and ADMIN to access /users
                                .anyRequest().authenticated()
                        // all other requests need to be authenticated
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginProcessingUrl("/authenticateTheUser") // redirect to /index after successful login
                                .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied") // redirect to /access-denied when access is denied
                );
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

}


