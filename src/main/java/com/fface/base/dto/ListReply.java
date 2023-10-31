package com.fface.base.dto;

import com.fface.post.model.entity.ReplyComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListReply {
    private List<ReplyComment> listReply;
}
