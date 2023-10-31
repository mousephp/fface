package com.fface.manager.service.userInfo;

import com.fface.base.exception.NotFoundException;
import com.fface.manager.model.entity.User;
import com.fface.manager.model.entity.UserInfo;
import com.fface.manager.repository.UserInfoRepository;
import com.fface.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserInfo> findAll(Pageable pageable) {
        return userInfoRepository.findAll(pageable);
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        return userInfoRepository.findById(id);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public void deleteById(Long id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public Long findUserId(String email, String phone_number) {
        return userInfoRepository.findUserId(email, phone_number);
    }

    @Override
    public Optional<UserInfo> findByUserId(Long userId) {
        return userInfoRepository.findByUserId(userId);
    }

    @Override
    public List<UserInfo> showAllUserInfo(Long currentUserId) {
        return this.userInfoRepository.showAllUserInfo(currentUserId);
    }

    @Override
    public List<UserInfo> findAllUser() {
        return this.userInfoRepository.findAllUser();
    }

    @Override
    public List<UserInfo> findByFullNameContaining(String fullName) {
        return this.userInfoRepository.findByFullNameContaining("%" + fullName + "%");
    }

    public UserInfo getByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new NotFoundException("Not found user");
        }

        return userInfoRepository.findByUser(user.get());
    }
}
