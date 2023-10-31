package com.fface.group.respository;

import com.fface.group.model.entity.GroupMember;
import com.fface.group.model.entity.MemberRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MemberRequestRepository extends JpaRepository<MemberRequest, Long> {
}