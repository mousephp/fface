package com.fface.post.service.statusPost;

import com.fface.post.model.entity.StatusPost;
import com.fface.base.service.GeneralService;

import java.util.List;

public interface StatusService extends GeneralService<StatusPost> {
    List<StatusPost> findAll();
}
