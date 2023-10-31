package com.fface.group.respository;

import com.fface.group.model.entity.Manager;
import com.fface.manager.model.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ManagerRepository extends CrudRepository<Manager, Long> {

    Manager findByUserInfo(Optional<UserInfo> userInfo);
    
}
