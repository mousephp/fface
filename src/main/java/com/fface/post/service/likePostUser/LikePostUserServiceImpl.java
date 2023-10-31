package com.fface.post.service.likePostUser;

import com.fface.base.exception.BadRequestException;
import com.fface.base.exception.NotFoundException;
import com.fface.manager.model.entity.UserInfo;
import com.fface.manager.repository.UserInfoRepository;
import com.fface.manager.service.userInfo.UserInfoService;
import com.fface.post.model.entity.LikePostUser;
import com.fface.post.model.entity.PostUser;
import com.fface.post.repository.LikePostRepository;
import com.fface.post.repository.PostUserRepository;
import com.fface.post.service.postUser.PostUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LikePostUserServiceImpl implements LikePostUserService {
    @Autowired
    private LikePostRepository likePostUserRepositoryl;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PostUserService postUserService;


    @Autowired
    private PostUserRepository postUserRepository;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public Page<LikePostUser> findAll(Pageable pageable) {
        return likePostUserRepositoryl.findAll(pageable);
    }

    @Override
    public Optional<LikePostUser> findById(Long id) {
        return likePostUserRepositoryl.findById(id);
    }

    @Override
    public LikePostUser save(LikePostUser likePostUser) {
        return likePostUserRepositoryl.save(likePostUser);
    }

    @Override
    public void deleteById(Long id) {
        likePostUserRepositoryl.deleteById(id);
    }

    @Override
    public List<LikePostUser> findAll() {
        return likePostUserRepositoryl.findAll();
    }

    @Override
    public Optional<LikePostUser> findLikePostUserByUserInfoIdAndPostUserId(Long userInfoId, Long postUserId) {
        return this.likePostUserRepositoryl.findLikePostUserByUserInfoIdAndPostUserId(userInfoId, postUserId);
    }

    @Override
    public List<LikePostUser> totalLikeByPost(Long postUserId) {
        return this.likePostUserRepositoryl.totalLikeByPost(postUserId);
    }

    /*==================test (-) ========================*/
    @Override
    public List<UserInfo> likePost(Long userId, Long postUserId) {
        Optional<PostUser> post = Optional.of(postUserRepository.findById(postUserId).get());

        if (post.isEmpty()){
            return (List<UserInfo>) new NotFoundException("Post", "postUserId", postUserId);
        }

        UserInfo userInfo = userInfoService.getByUser(userId);

        if (ObjectUtils.isEmpty(userInfo)) {
            return (List<UserInfo>) new NotFoundException("User", "userId", userId);
        }

        post.get().getLikePostUser().forEach(item -> {
            if(item.getId() == userInfo.getId()){
                throw new BadRequestException("Post already liked");
            }
        });

        post.get().getLikePostUser().add(userInfo);

        postUserService.save(post.get());

        return post.get().getLikePostUser();
    }

    /*==================test false========================*/
    @Override
    public List<UserInfo> unLikePost(Long userId, Long postUserId) {
        Optional<PostUser> post = Optional.of(postUserRepository.findById(postUserId).get());

        if (post.isEmpty()) {
            throw new NotFoundException("Post is not found");
        }

        UserInfo profile = userInfoService.getByUser(userId);

        if (ObjectUtils.isEmpty(profile)) {
            throw new NotFoundException("User id is not exist");
        }

        if (!post.get().getLikePostUser().contains(profile)) {
            throw new BadRequestException("Post has not yet been liked");
        }

        System.out.println("profile.getId():"+profile.getId());

        post.get().getLikePostUser().forEach(item -> {
            System.out.println("item.getId():"+item.getId());
            if (item.getId() == profile.getId()) {
                /*error o doan nay*/
                post.get().getLikePostUser().remove(profile);
            }
        });

        postUserRepository.save(post.get());

        return post.get().getLikePostUser();
    }
}
