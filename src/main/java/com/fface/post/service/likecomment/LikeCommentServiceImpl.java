package com.fface.post.service.likecomment;

import com.fface.post.model.entity.LikeComment;
import com.fface.post.repository.LikeCommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeCommentServiceImpl implements LikeCommentService {
    @Autowired
    private LikeCommentRepo likeCommentRepo;
    
    @Override
    public Page<LikeComment> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<LikeComment> findById(Long id) {
        return likeCommentRepo.findById(id);
    }

    @Override
    public LikeComment save(LikeComment likeComment) {
        return likeCommentRepo.save(likeComment);
    }

    @Override
    public void deleteById(Long id) {
            likeCommentRepo.deleteById(id);
    }

    @Override
    public Optional<LikeComment> findLikeCommentByUser(Long cmPostUserId, Long fromUserId) {
        return likeCommentRepo.findLikeCommentByUser(cmPostUserId,fromUserId);
    }

    @Override
    public List<LikeComment> listLikeComments(Long cmPostUserId) {
        return likeCommentRepo.listLikeComments(cmPostUserId);
    }
}
