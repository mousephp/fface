package com.fface.group.controller;

import com.fface.base.dto.GroupNameExistDto;
import com.fface.base.dto.GroupProjection;
import com.fface.base.exception.BadRequestException;
import com.fface.base.exception.NotFoundException;
import com.fface.friend.entity.ListFriend;
import com.fface.group.model.entity.Group;
import com.fface.group.model.entity.GroupMember;
import com.fface.group.model.request.GroupRequest;
import com.fface.group.respository.GroupRepository;
import com.fface.group.service.group.GroupService;
import com.fface.group.service.groupMember.GroupMemberService;
import com.fface.friend.service.listFriend.ListFriendService;
import com.fface.manager.model.entity.UserInfo;
import com.fface.manager.service.user.UserService;
import com.fface.manager.service.userInfo.UserInfoService;
import com.fface.base.utils.ResponseMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/groups")
@CrossOrigin("*")
public class GroupController {
    @Autowired
    private GroupService groupService;
    
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private ListFriendService listFriendService;

    @Autowired
    private GroupRepository groupRepository;

    /*Projection*/
    @GetMapping("/all")
    List<GroupProjection> getAllGroups(){
        return groupService.getAllGroups();
    }

    @GetMapping("/search")
    public List<GroupProjection> searchGroups(@RequestParam String name) {
        return groupRepository.findByNameContaining(name);
    }
    /*End Projection*/

    @GetMapping()
    public ResponseEntity<Page<Group>> findAllGroup(@PageableDefault(value = 100) Pageable pageable) {
        Page<Group> groups = groupService.findAll(pageable);

        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> findGroupById(@PathVariable Long id) {
       Optional<Group> group = groupService.findById(id);

       if (!group.isPresent()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }

       return new ResponseEntity<ResponseMessage>(new ResponseMessage("success", group.get()), HttpStatus.OK);
    }

    @GetMapping("/findByUserId/{userId}") //ps: phai pass token truoc
    public ResponseEntity<Page<Group>> findAllGroupCreateByUserId(@PathVariable Long userId, @PageableDefault(value = 100) Pageable pageable) {
        UserInfo admin = userInfoService.findByUserId(userId).orElse(null);

        if (admin == null) {
            throw new BadRequestException("Admin is null");
        }

        Page<Group> groupByUser = groupService.findByUserId(admin.getId(), pageable);

        if (groupByUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(groupByUser, HttpStatus.OK);
    }

    @GetMapping("/findByOtherUserId/{userId}")
    public ResponseEntity<Page<Group>>findAllGroupOtherUserId(@PathVariable Long userId, Pageable pageable){
        UserInfo userInfo = userInfoService.findByUserId(userId).orElseThrow(
                () -> new NotFoundException("NotificationAddGroup", "userId", userId)
        );

        Page<Group> groupOtherUser = groupService.findAllGroupOtherUserId(userInfo.getId(), pageable);

        if (groupOtherUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(groupOtherUser, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    @Transactional
    public ResponseEntity<Group> storeGroup(@PathVariable Long userId, @RequestBody Group group) {
        Optional<UserInfo> admin = userInfoService.findById(userId);

        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        group.setUser(admin.get());

        group.setAvatar("1651423516341minh.png");

        Group newGroup = groupService.save(group);

        groupMemberService.save(new GroupMember(group, admin.get(), 0));

        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }
    @PostMapping("/create/{userId}")
    @Transactional
    public ResponseEntity<?> createGroup(@PathVariable Long userId, @RequestBody GroupRequest req) {
        Group group = groupService.createGroup(userId, req);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Group created successfully", group),
                HttpStatus.OK);
    }

   @PutMapping("/{id}")
   @Transactional
   public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group group) {
       Optional<Group> groupOption = groupService.findById(id);

       Group newGroup = groupOption.get();

       if (!groupOption.isPresent()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }

       newGroup.setId(id);
       newGroup.setName(group.getName());
       newGroup.setBackground(group.getBackground());
       newGroup.setGroupStatus(group.getGroupStatus());

       groupService.save(newGroup);

       return new ResponseEntity<>(newGroup, HttpStatus.OK);
   }

    @DeleteMapping("/{id}")
    ResponseEntity<Group> deleteGroup(@PathVariable Long id) {
        Optional<Group> group = groupService.findById(id);

        if (!group.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        groupRepository.deleteById(id);

        return new ResponseEntity<>(group.get(), HttpStatus.OK);
    }

    @GetMapping("/search-name")
    public ResponseEntity<List<Group>> searchAllByName(@RequestParam String name) {
        if ("".equals(name)) {
            throw new BadRequestException("Từ khóa tìm kiếm không được để trống");
        }

        List<Group> groups = groupService.getAllByName(name);

        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
    
    @GetMapping("/checkNameExist/{id}/{name}")
    public ResponseEntity<GroupNameExistDto>groupNameExistCheck(@PathVariable String name, @PathVariable Long id) {
        List<Group> groups = groupService.findAllGroupOfUser(id);

        GroupNameExistDto nameExist = new GroupNameExistDto();

        nameExist.setStatus(false);

        for (int i = 0; i < groups.size(); i++){
            if (groups.get(i).getName().equals(name)){ // or: groups.get(i).getName().contains(name)
                nameExist.setStatus(true);

                break;
            }
        }

        return new ResponseEntity<>(nameExist, HttpStatus.OK);
    }

    @GetMapping("/add/user/{id}/{groupId}")
    public ResponseEntity<List<ListFriend>> listFriendAdd(@PathVariable Long id, @PathVariable Long groupId) {
        UserInfo userInfo = userInfoService.findByUserId(id).get();

        List<ListFriend> listFriend = listFriendService.listUserAdd(userInfo.getId(), groupId);

        return new ResponseEntity<>(listFriend, HttpStatus.OK);
    }
}
