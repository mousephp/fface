package com.fface.post.service.reply;

import com.fface.post.model.entity.ReplyComment;
import com.fface.post.repository.ReplyCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyCommentRepository replyCommentRepository;

    @Override
    public Page<ReplyComment> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ReplyComment> findById(Long id) {
        return replyCommentRepository.findById(id);
    }

    @Override
    public ReplyComment save(ReplyComment replyComment) {
        return replyCommentRepository.save(replyComment);
    }

    @Override
    public void deleteById(Long id) {
            replyCommentRepository.deleteById(id);
    }

    @Override
    public List<ReplyComment> getAllReplyOfComment(Long postId) {
        return this.replyCommentRepository.getAllReplyOfComment(postId);
    }

    @Override
    public List<ReplyComment> getAllRepLy(Long commentId) {
        return this.replyCommentRepository.getAllRepLy(commentId);
    }
}
