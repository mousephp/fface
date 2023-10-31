package com.fface.group.respository;

import com.fface.group.model.entity.GroupStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupStatusRepository extends JpaRepository<GroupStatus, Long> {
}
