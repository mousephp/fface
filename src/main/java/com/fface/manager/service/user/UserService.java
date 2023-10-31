package com.fface.manager.service.user;

import com.fface.manager.model.entity.User;
import com.fface.base.service.GeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends GeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    Iterable<User> findAll();

    User saveAdmin(User user);

    List<User> findAllUser();

    User saveBlock(User user);

}
