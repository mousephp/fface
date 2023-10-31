package com.fface.post.service.postUser;

import com.fface.base.exception.BadRequestException;
import com.fface.base.exception.NotFoundException;
import com.fface.manager.model.entity.UserInfo;
import com.fface.manager.service.userInfo.UserInfoService;
import com.fface.post.model.entity.PostUser;
import com.fface.post.repository.PostUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostUserServiceImpl implements PostUserService {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PostUserRepository postUserRepository;

    @Override
    public Page<PostUser> findAll(Pageable pageable) {
        return postUserRepository.findAll(pageable);
    }

    @Override
    public Optional<PostUser> findById(Long id) {
        return postUserRepository.findById(id);
    }

    @Override
    public PostUser save(PostUser postUser) {
        return postUserRepository.save(postUser);
    }

    @Override
    public void deleteById(Long id) {
        postUserRepository.deleteById(id);
    }

    @Override
    public Iterable<PostUser> findAll() {
        return postUserRepository.findAll();
    }

    @Override
    public void deletePost(Long postUserId) {
        this.postUserRepository.deletePost(postUserId);
    }

    @Override
    public List<PostUser> showAllPostByUser(Long userInfoId) {
        return this.postUserRepository.showAllPostByUser(userInfoId);
    }

    @Override
    public String getDiffDays(Date time1, Date time2) {
        long timeDifferenceMilliseconds = (time2.getTime() - time1.getTime());
        long diffSeconds = timeDifferenceMilliseconds / 1000;
        long diffMinutes = timeDifferenceMilliseconds / (60 * 1000);
        long diffHours = timeDifferenceMilliseconds / (60 * 60 * 1000);
        long diffDays = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
        long diffWeeks = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 7);
        long diffMonths = (long) (timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 30.41666666));
        long diffYears = timeDifferenceMilliseconds / ((long)60 * 60 * 1000 * 24 * 365);

        if (diffSeconds < 1) {
            return "vừa xong";
        } else if (diffMinutes < 1) {
            return diffSeconds + " giây";
        } else if (diffHours < 1) {
            return diffMinutes + " phút";
        } else if (diffDays < 1) {
            return diffHours + " giờ";
        } else if (diffWeeks < 1) {
            return diffDays + " ngày";
        } else if (diffMonths < 1) {
            return diffWeeks + " tuần";
        } else if (diffYears < 1) {
            return diffMonths + " tháng";
        } else {
            return diffYears + " năm";
        }
    }

    @Override
    public List<PostUser> postUserFriends(Long userId) {
        return postUserRepository.postUserFriends(userId);
    }



    /*========================*/
    public List<PostUser> sharePost(Long userId, Long postId) {

        Optional<PostUser> post = Optional.ofNullable(postUserRepository.findById(postId).get());
        if (post.isEmpty()) {
            throw new NotFoundException("Post is not found");
        }

        UserInfo profile = userInfoService.getByUser(userId);
        if (ObjectUtils.isEmpty(profile)) {
            throw new NotFoundException("User id is not exist");
        }

        if (profile.getShare().contains(post.get())) {
            throw new BadRequestException("Post already shared");
        }

        profile.getShare().add(post.get());
        userInfoService.save(profile);
        return (List<PostUser>) profile.getShare();

    }

    public List<PostUser> removeSharePost(Long userId, Long postId) {

        Optional<PostUser> post = Optional.ofNullable(postUserRepository.findById(postId).get());
        if (post.isEmpty()) {
            throw new NotFoundException("Post is not found");
        }

        UserInfo profile = userInfoService.getByUser(userId);
        if (ObjectUtils.isEmpty(profile)) {
            throw new NotFoundException("User id is not exist");
        }

        if (!profile.getShare().contains(post.get())) {
            throw new BadRequestException("'Post has not yet been shared");
        }

        profile.getShare().remove(post.get());
        userInfoService.save(profile);
        return (List<PostUser>) profile.getShare();

    }

}

