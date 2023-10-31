package com.fface.group.service.postGroup;

import com.fface.group.model.entity.PostGroup;
import com.fface.base.service.GeneralService;

import java.util.List;

public interface PostGroupService extends GeneralService<PostGroup> {
    List<PostGroup> listPost(Long groupId);

}
