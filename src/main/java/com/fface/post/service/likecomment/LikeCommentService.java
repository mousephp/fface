package com.fface.post.service.likecomment;

import com.fface.post.model.entity.LikeComment;
import com.fface.base.service.GeneralService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LikeCommentService extends GeneralService<LikeComment> {
    Optional<LikeComment> findLikeCommentByUser(Long cmPostUserId, Long fromUserId);
    List<LikeComment> listLikeComments(Long cmPostUserId);

}
