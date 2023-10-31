package com.fface.group.service.imagePostGroup;

import com.fface.group.model.entity.ImagePostGroup;
import com.fface.base.service.GeneralService;

public interface ImagePostGroupService extends GeneralService<ImagePostGroup> {
    ImagePostGroup[] listImage(Long postGroupId);

}
