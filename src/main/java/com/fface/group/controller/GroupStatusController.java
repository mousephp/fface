package com.fface.group.controller;

import com.fface.group.model.entity.GroupStatus;
import com.fface.group.service.groupStatus.GroupStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/group-status")
public class GroupStatusController {
    @Autowired
    private GroupStatusService groupStatusService;

    @GetMapping
    public ResponseEntity<List<GroupStatus>> getAll(){
        List<GroupStatus> groupStatusList = this.groupStatusService.findAll();

        return new ResponseEntity<>(groupStatusList, HttpStatus.OK);
    }
}
