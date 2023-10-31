package com.fface.manager.service.role;


import com.fface.manager.model.entity.Role;
import com.fface.base.service.GeneralService;

public interface RoleService extends GeneralService<Role> {
    Iterable<Role> findAll();
}
