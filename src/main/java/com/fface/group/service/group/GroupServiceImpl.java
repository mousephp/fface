package com.fface.group.service.group;

import com.fface.base.dto.GroupProjection;
import com.fface.base.exception.NotFoundException;
import com.fface.group.model.entity.Group;
import com.fface.group.model.entity.GroupMember;
import com.fface.group.model.request.GroupRequest;
import com.fface.group.respository.GroupMemberRepository;
import com.fface.group.respository.GroupRepository;
import com.fface.group.service.groupMember.GroupMemberService;
import com.fface.manager.model.entity.UserInfo;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private GroupMemberService groupMemberService;

    @Override
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
        // get with projection
        // return groupRepository.findBy(GroupProjection.class);
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteById(Long id) {
        this.groupRepository.deleteById(id);
    }

    @Override
    public Page<Group> findByUserId(Long id, Pageable pageable) {
        return groupRepository.findAllByUserId(id, pageable);
    }

    @Override
    public Page<Group> findAllGroupOtherUserId(Long id, Pageable pageable) {
        return groupRepository.findAllGroupOtherUserId(id, pageable);
    }

    @Override
    public List<Group> findAllGroupOfUser(Long id) {
        return this.groupRepository.findAllGroupOfUser(id);
    }

    public List<GroupProjection> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    public List<Group> getAllByName(String name) {
        return groupRepository.findByName(name);
    }

    public boolean existsByNameAndUserId(String name, Long userId) {
        List<Group> group = groupRepository.findByNameAndUserId(name, userId);

        if (group.isEmpty()) {
            return false;
        }

        return true;
    }

    public Group createGroup(Long userId, GroupRequest req) {
        Optional<UserInfo> admin = userInfoService.findById(userId);

        if (ObjectUtils.isEmpty(admin)) {
            throw new NotFoundException("User id is not exist");
        }

        Group group = Group.builder()
                .name(req.getName())
                .avatar("1651423516341minh.png")
                .background(req.getBackground())
                .user(admin.get())
                .groupStatus(req.getGroupStatus())
                .build();

        groupRepository.save(group);

        GroupMember memberRequest = GroupMember.builder()
                .group(group)
                .userInfo(admin.get())
                .status(0)
                .build();

        return groupMemberRepository.save(memberRequest).getGroup();
    }

}
