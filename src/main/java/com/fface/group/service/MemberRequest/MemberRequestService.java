package com.fface.group.service.MemberRequest;

import com.fface.base.exception.BadRequestException;
import com.fface.base.exception.NotFoundException;
import com.fface.group.model.entity.*;
import com.fface.group.model.request.JoinGroup;
import com.fface.group.model.request.SetManager;
import com.fface.group.respository.GroupMemberRepository;
import com.fface.group.respository.GroupRepository;
import com.fface.group.respository.ManagerRepository;
import com.fface.group.respository.MemberRequestRepository;
import com.fface.manager.model.entity.UserInfo;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Optional;

@Service
public class MemberRequestService {
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private MemberRequestRepository memberRequestRepository;

    @Autowired
    private UserInfoService userInfoService;

    public Group joinGroup(JoinGroup req) {
        Group group = groupMemberRepository.findById(req.getGroupId()).get().getGroup();
        if (ObjectUtils.isEmpty(group)) {
            throw new NotFoundException("Group id is not exist");
        }

        Optional<UserInfo> profile = userInfoService.findById(req.getUserInfoId());

        if (ObjectUtils.isEmpty(profile)) {
            throw new NotFoundException("User id is not exist");
        }

        group.getMemberRequest().forEach(item -> {
            if (item.getUserInfo().equals(profile)) {
                throw new BadRequestException("You has already been requested to join this group");
            }
        });

        group.getGroupMember().forEach(item -> {
            if (item.getUserInfo().equals(profile)) {
                throw new BadRequestException("You has already been be member of this group");
            }
        });

        MemberRequest memberRequest = MemberRequest.builder()
                .date(new Date())
                .userInfo(profile.get())
                .group(group)
                .build();

        memberRequestRepository.save(memberRequest);

        return groupRepository.save(group);
    }

    public Group approveGroup(JoinGroup req) {
        Group group = groupRepository.findById(req.getGroupId()).get();

        if (ObjectUtils.isEmpty(group)) {
            throw new NotFoundException("Group id is not exist");
        }

        Optional<UserInfo> profile = userInfoService.findByUserId(req.getUserInfoId());

        if (ObjectUtils.isEmpty(profile)) {
            throw new NotFoundException("User id is not exist");
        }

        group.getMemberRequest().forEach(item -> {
            if (!item.getUserInfo().equals(profile)) {
                throw new BadRequestException("There is not any request of this user");
            } else {
                group.getMemberRequest().remove(item);
            }
        });

        group.getGroupMember().forEach(item -> {
            if (!item.getUserInfo().equals(profile)) {
                throw new BadRequestException("You has already been be member of this group");
            } else {
                GroupMember member = GroupMember.builder()
                        .userInfo(profile.get())
                        .group(group)
                        .status(1)
                        .build();

                groupMemberRepository.save(member);
                group.getGroupMember().add(member);
            }
        });

        return groupRepository.save(group);
    }

    public Group removeManager(SetManager req) {
        Group group = groupRepository.findById(req.getGroupId()).get();

        if (ObjectUtils.isEmpty(group)) {
            throw new NotFoundException("Group id is not exist");
        }

        Optional<UserInfo> profile = userInfoService.findByUserId(req.getUserInfoId());

        if (ObjectUtils.isEmpty(profile)) {
            throw new NotFoundException("User id is not exist");
        }

        Manager manager = managerRepository.findByUserInfo(profile);

        if (!group.getManagers().contains(manager)) {
            throw new BadRequestException("You has not yet been manager of this group");
        } else {
            group.getManagers().remove(manager);  
        }

        return groupRepository.save(group);
    }

    public Group addManager(SetManager req) {
        Group group = groupRepository.findById(req.getGroupId()).get();

        if (ObjectUtils.isEmpty(group)) {
            throw new NotFoundException("Group id is not exist");
        }

        Optional<UserInfo> profile = userInfoService.findByUserId(req.getUserInfoId());

        if (ObjectUtils.isEmpty(profile)) {
            throw new NotFoundException("User id is not exist");
        }

        group.getManagers().forEach(item -> {
            if (item.getUserInfo().equals(profile)) {
                throw new BadRequestException("You has already been set manger to this group");
            }
        });

        Manager manager = Manager.builder()
                .userInfo(profile.get())
                .date(new Date())
                .role(RoleManager.valueOf(req.getRole()))
                .build();

        group.getManagers().add(manager);

        return groupRepository.save(group);
    }







    /*========== test false===========*/
    public Group removeMember(SetManager req) {
        Group group = groupRepository.findById(req.getGroupId()).get();

        if (ObjectUtils.isEmpty(group)) {
            throw new NotFoundException("Group id is not exist");
        }

        Optional<UserInfo> profile = userInfoService.findByUserId(req.getUserInfoId());


        if (ObjectUtils.isEmpty(profile)) {
            throw new NotFoundException("User id is not exist");
        }

        Optional<GroupMember> member = groupMemberRepository.findByUserAndGroup(req.getUserInfoId(), req.getGroupId());

        if (group.getGroupMember().isEmpty()){
            throw new BadRequestException("Bạn  empty");
        }

            if (!group.getGroupMember().contains(member)) {
                throw new BadRequestException("You has not yet been member of this group");
            } else  if (group.getGroupMember().size() == 1) {
                throw new BadRequestException("You are last member of this group. Cannot delete");
            } else {
                group.getGroupMember().remove(member);
            }
        return groupRepository.save(group);
    }
    /*==========chua test===========*/
}