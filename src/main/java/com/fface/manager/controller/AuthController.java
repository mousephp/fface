package com.fface.manager.controller;

import com.fface.base.dto.ChangePassword;
import com.fface.base.dto.JwtResponse;
import com.fface.base.dto.SignUpForm;
import com.fface.base.dto.UserInfoExitDTO;
import com.fface.manager.model.entity.User;
import com.fface.manager.model.entity.UserInfo;
import com.fface.base.service.JwtService;
import com.fface.manager.service.user.UserService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        //Kiểm tra username và pass có đúng hay không
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        //Lưu user đang đăng nhập vào trong context của security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody SignUpForm user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User newUser = new User(user.getUsername(), user.getPassword());
        userService.save(newUser);
        String avatar = "1651410713778facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg";
        String background = "1651390542985emirates-arsenal_gkpg.png";
        UserInfo userInfo = new UserInfo(user.getEmail(), user.getPhoneNumber(),
                user.getDateOfBirth(), avatar, background, newUser);
        userInfoService.save(userInfo);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/findUserId/{email}/{phone}")
    public ResponseEntity<User> findUserId(@PathVariable String email, @PathVariable String phone) {
        Long userId = this.userInfoService.findUserId(email, phone);
        Optional<User> user = this.userService.findById(userId);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserInfoExitDTO> usernameExitCheck(@PathVariable String username) {
        List<User> users = this.userService.findAllUser();
        UserInfoExitDTO usernameExitDTO = new UserInfoExitDTO();
        usernameExitDTO.setStatus(false);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                usernameExitDTO.setStatus(true);
                break;
            }
        }
        return new ResponseEntity<>(usernameExitDTO, HttpStatus.OK);
    }

    @GetMapping("/userBlock/{username}")
    public ResponseEntity<UserInfoExitDTO> userBlockCheck(@PathVariable String username) {
        List<User> users = this.userService.findAllUser();
        UserInfoExitDTO usernameExitDTO = new UserInfoExitDTO();
        usernameExitDTO.setStatus(false);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                if (users.get(i).getBlockStatus()) {
                    usernameExitDTO.setStatus(true);
                    break;
                }
            }
        }
        return new ResponseEntity<>(usernameExitDTO, HttpStatus.OK);
    }

    @GetMapping("/username-password/{username}/{password}")
    public ResponseEntity<UserInfoExitDTO> passwordTrueCheck(@PathVariable String username, @PathVariable String password) {
        List<User> users = this.userService.findAllUser();
        UserInfoExitDTO usernameExitDTO = new UserInfoExitDTO();
        usernameExitDTO.setStatus(false);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                if (passwordEncoder.matches(password, users.get(i).getPassword())) {
                    usernameExitDTO.setStatus(true);
                    break;
                }
            }
        }
        return new ResponseEntity<>(usernameExitDTO, HttpStatus.OK);
    }

    @PostMapping("/changePassword/{id}")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody ChangePassword changePassword) {
        Optional<User> user = this.userService.findById(id);
        String newPassword;
        String oldPassword = changePassword.getOldPassword();
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (passwordEncoder.matches(oldPassword, user.get().getPassword())) {
                if (changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
                    newPassword = changePassword.getNewPassword();
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        user.get().setPassword(newPassword);
        user.get().setId(id);
        this.userService.save(user.get());
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
