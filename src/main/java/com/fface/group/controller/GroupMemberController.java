package com.fface.group.controller;

import com.fface.group.model.entity.Group;
import com.fface.group.model.entity.GroupMember;
import com.fface.group.model.entity.NotificationAddGroup;
import com.fface.manager.model.entity.UserInfo;
import com.fface.group.service.group.GroupService;
import com.fface.group.service.groupMember.GroupMemberService;
import com.fface.group.service.notifiationAddGroup.NotificationGroupService;
import com.fface.manager.service.userInfo.UserInfoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/group-members")
public class GroupMemberController {
    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private NotificationGroupService notificationGroupService;

    @PostMapping("/{group_id}/{member_id}")
    @Transactional
    public ResponseEntity<GroupMember> acceptMember(@PathVariable Long group_id, @PathVariable Long member_id) {
        Group group = groupService.findById(group_id).get();

        UserInfo member = userInfoService.findByUserId(member_id).get();

        GroupMember newMember = groupMemberService.save(new GroupMember(group, member, 1));

        Optional<NotificationAddGroup> notificationAddGroup = notificationGroupService.findNotification(group_id, member.getId());

        if (!notificationAddGroup.isEmpty()) {
            notificationGroupService.deleteById(notificationAddGroup.get().getId());
        }

        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    @PostMapping("/{adminId}/{groupId}/{memberId}")
    public ResponseEntity<GroupMember> acceptMemberOnGroup(@PathVariable Long adminId, @PathVariable Long groupId, @PathVariable Long memberId){
        Group group = groupService.findById(groupId).get();

        UserInfo member = userInfoService.findByUserId(memberId).get();

        GroupMember newGroupMember = groupMemberService.save(new GroupMember(group, member, 1));

        Optional<NotificationAddGroup> notificationAddGroup = notificationGroupService.findNotificationByGroupIdAndFromUserId(adminId, groupId, memberId);

        if (!notificationAddGroup.isEmpty()) {
            notificationGroupService.deleteById(notificationAddGroup.get().getId());
        }

        return new ResponseEntity<GroupMember>(newGroupMember, HttpStatus.CREATED);
    }

    @DeleteMapping("/{group_id}/{member_id}")
    public ResponseEntity<GroupMember> deleteMember(@PathVariable Long group_id, @PathVariable Long member_id) {
        Group group = groupService.findById(group_id).get();

        UserInfo userInfo = userInfoService.findByUserId(member_id).get();

        GroupMember newMember = new GroupMember();
        System.out.println("userInfo::"+userInfo.getId());

        Optional<NotificationAddGroup> notificationAddGroup = notificationGroupService.findNotification(group_id, userInfo.getId());

        if (notificationAddGroup.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        notificationGroupService.deleteById(notificationAddGroup.get().getId());

        return new ResponseEntity<>(newMember, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GroupMember> deleteMemberById(@PathVariable Long id) {
        Optional<GroupMember> member = this.groupMemberService.findById(id);

        if (member.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        this.groupMemberService.deleteById(id);

        return new ResponseEntity<>(member.get(), HttpStatus.OK);
    }

    @DeleteMapping("/out/{groupId}/{userId}")
    public ResponseEntity<GroupMember> outGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        UserInfo userInfo = userInfoService.findByUserId(userId).get();

        Optional<GroupMember> member = groupMemberService.findMemberOut(groupId, userInfo.getId());

        if (member.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        this.deleteMemberById(member.get().getId());

        return new ResponseEntity<>(member.get(), HttpStatus.OK);
    }

    @GetMapping("/findGroup/{userId}")
    public ResponseEntity<List<GroupMember>> groupsOfMe(@PathVariable Long userId) {
        UserInfo userInfo = userInfoService.findByUserId(userId).get();

        List<GroupMember> groups = groupMemberService.findGroupsOfMe(userInfo.getId());

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<List<GroupMember>> showGroupMemberById(@PathVariable Long groupId) {
        List<GroupMember> groupMembers = groupMemberService.findGroupMemberByGroupId(groupId);

        return new ResponseEntity<>(groupMembers, HttpStatus.OK);
    }

    @GetMapping("/totalMembers/{groupId}")
    public ResponseEntity<Integer> totalMember(@PathVariable Long groupId) {
        List<GroupMember> member = groupMemberService.findGroupMemberByGroupId(groupId);

        return new ResponseEntity<>(member.size(), HttpStatus.OK);
    }

    @GetMapping("groups/{id}")
    public ResponseEntity<List<GroupMember>> showAllGroupByUserId(@PathVariable Long id) {
        UserInfo userInfo = userInfoService.findByUserId(id).get();

        List<GroupMember> groups = groupMemberService.findGroupByUserId(userInfo.getId());

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
}
