package com.fface.base.config.security;

import com.fface.base.config.CustomAccessDeniedHandler;
import com.fface.base.config.JwtAuthenticationFilter;
import com.fface.base.config.RestAuthenticationEntryPoint;
import com.fface.manager.model.entity.Role;
import com.fface.manager.model.entity.User;
import com.fface.manager.service.role.RoleServiceImpl;
import com.fface.manager.service.user.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @PostConstruct
    public void init() {
        List<User> users = (List<User>) userService.findAll();
        List<Role> roleList = (List<Role>) roleService.findAll();
        if (roleList.isEmpty()) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            roleService.save(roleAdmin);
            Role roleUser= new Role("ROLE_USER");
            roleService.save(roleUser);
            Role roleAdminGroup= new Role("ROLE_ADMIN_GROUP");
            roleService.save(roleUser);
        }
        if (users.isEmpty()) {
            User admin = new User("admin","thuthuyda1");
            userService.saveAdmin(admin);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/h2-console/**",
                        "/api/v1/**",
                        "/login",
                        "/register",
                        "/username/**",
                        "/findUserId/**",
                        "/username-password/**",
                        "/changePassword/**",
                        "/userBlock/**",
                        "/userInfo/email/**",
                        "/userInfo/phone/**",
                        "/userInfo/updateUserInfo/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/api-docs/**",
                        "/swagger-ui/index.html",
                        "/api/v1/groups/**",
                        "/groupStatus",
                        "/posts/**",
                        "/postGroups/**",
                        "/userInfo/**",
                        "/comments/**",
                        "/reply/**",
                        "/status/**",
                        "/likePosts/**",
                        "/likeComments/**",
                        "/chats/**",
                        "/messager/**",
                        "/userDetails/**",
                        "/images/**",
                        "/checkStatus/**",
                        "/messages/**"
                )
                .permitAll()
                .requestMatchers("/admin/**")
                .hasAnyRole("ADMIN")
                .and()
                .authorizeHttpRequests().requestMatchers("/api/**")
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

