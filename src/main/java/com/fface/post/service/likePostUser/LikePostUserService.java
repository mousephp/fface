package com.fface.post.service.likePostUser;

import com.fface.manager.model.entity.UserInfo;
import com.fface.post.model.entity.LikePostUser;
import com.fface.base.service.GeneralService;

import java.util.List;
import java.util.Optional;

public interface LikePostUserService extends GeneralService<LikePostUser> {
    List<LikePostUser> findAll();

    Optional<LikePostUser> findLikePostUserByUserInfoIdAndPostUserId(Long userInfoId, Long postUserId);

    List<LikePostUser> totalLikeByPost(Long postUserId);

    List<UserInfo> likePost(Long userId, Long postUserId);

    List<UserInfo> unLikePost(Long userId, Long postUserId);
}
