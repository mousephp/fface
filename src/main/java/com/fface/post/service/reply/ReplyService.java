package com.fface.post.service.reply;

import com.fface.post.model.entity.ReplyComment;
import com.fface.base.service.GeneralService;

import java.util.List;

public interface ReplyService extends GeneralService<ReplyComment> {

    List<ReplyComment> getAllReplyOfComment(Long postId);

    List<ReplyComment>  getAllRepLy(Long commentId);
}
