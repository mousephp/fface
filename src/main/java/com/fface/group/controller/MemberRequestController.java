package com.fface.group.controller;

import com.fface.base.utils.ResponseMessage;
import com.fface.group.model.entity.Group;
import com.fface.group.model.request.GroupMemberRequest;
import com.fface.group.model.request.JoinGroup;
import com.fface.group.model.request.SetManager;
import com.fface.group.service.MemberRequest.MemberRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MemberRequestController {
    @Autowired
    private MemberRequestService memberRequestService;

    @PostMapping(value = "/groups/join")
    public ResponseEntity<?> joinGroup(@RequestBody JoinGroup req) {
        Group group = memberRequestService.joinGroup(req);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Request success", group),
                HttpStatus.OK);
    }

    @PostMapping(value = "/groups/approve")
    public ResponseEntity<?> approveGroup(@RequestBody JoinGroup req) {
        Group group = memberRequestService.approveGroup(req);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Approved", group),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/groups/removeManager")
    public ResponseEntity<?> removeManager(@RequestBody SetManager req) {
        Group group = memberRequestService.removeManager(req);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Remove success", group),
                HttpStatus.OK);
    }

    @PostMapping(value = "/groups/addManager")
    public ResponseEntity<?> addManager(@RequestBody SetManager req) {
        Group group = memberRequestService.addManager(req);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Request success", group),
                HttpStatus.OK);
    }

    /*=====chua test========*/
    @DeleteMapping(value = "/groups/removeMember")
    public ResponseEntity<?> removeMember(@RequestBody SetManager req) {
        Group group = memberRequestService.removeMember(req);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Remove success", group),
                HttpStatus.OK);
    }
    /*=====chua test========*/

}
