package com.fface.group.service.group;

import com.fface.base.dto.GroupProjection;
import com.fface.group.model.entity.Group;
import com.fface.base.service.GeneralService;
import com.fface.group.model.request.GroupRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService extends GeneralService<Group> {
    public Page<Group>findByUserId(Long id, Pageable pageable);

    public Page<Group> findAllGroupOtherUserId(Long id, Pageable pageable);

    List<Group> findAllGroupOfUser(Long id);

    List<GroupProjection> getAllGroups();

    List<Group> getAllByName(String name);

    boolean existsByNameAndUserId(String name, Long userId);

    Group createGroup(Long userId, GroupRequest req);

}
