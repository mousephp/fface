package com.fface.post.service.postUser;

import com.fface.post.model.entity.PostUser;
import com.fface.base.service.GeneralService;

import java.util.Date;
import java.util.List;

public interface PostUserService extends GeneralService<PostUser> {
    Iterable<PostUser> findAll();

    void deletePost(Long postUserId);

    List<PostUser>  showAllPostByUser(Long userInfoId);

    String getDiffDays(Date time1, Date time2);

    List<PostUser> postUserFriends(Long userId);

    List<PostUser> sharePost(Long id, Long postUserId);

    List<PostUser> removeSharePost(Long id, Long postUserId);
}
