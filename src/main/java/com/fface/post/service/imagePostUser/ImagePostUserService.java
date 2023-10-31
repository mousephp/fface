package com.fface.post.service.imagePostUser;

import com.fface.post.model.entity.ImagePostUser;
import com.fface.base.service.GeneralService;

import java.util.Optional;

public interface ImagePostUserService extends GeneralService<ImagePostUser> {

    Iterable<ImagePostUser> findAll();

    ImagePostUser[] listImage(Long postUser);

    Optional<ImagePostUser> findByPostUserId(Long postUserId);

    ImagePostUser findImageById(Long imageId);

}
