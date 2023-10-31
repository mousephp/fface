package com.fface.manager.service.userInfo;

import com.fface.manager.model.entity.UserInfo;
import com.fface.base.service.GeneralService;

import java.util.List;
import java.util.Optional;

public interface UserInfoService extends GeneralService<UserInfo> {
    Long findUserId(String email, String phone_number) ;

    Optional<UserInfo> findByUserId(Long userId);

    List<UserInfo> showAllUserInfo(Long currentUserId);

    List<UserInfo> findAllUser();

    List<UserInfo> findByFullNameContaining(String fullName);

    UserInfo getByUser(Long userId);
}
