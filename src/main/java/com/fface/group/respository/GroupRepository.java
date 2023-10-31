package com.fface.group.respository;

import com.fface.base.dto.GroupProjection;
import com.fface.group.model.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(value = "select * from groups_ where user_id = ?1", nativeQuery = true)
    Page<Group> findAllByUserId(Long id, Pageable pageable);

    @Query(value = "select * from groups_ where user_id != ?1 and group_status_id = 1 and id not in (select group_id from group_members where user_info_id = ?1 and status = 1)", nativeQuery = true)
    Page<Group> findAllGroupOtherUserId(Long id, Pageable pageable);

    @Query(value = "select * from groups_ where user_id =?1", nativeQuery = true)
    List<Group> findAllGroupOfUser(Long id);

    @Query("select g from Group g")
    List<GroupProjection> getAllGroups();

    List<Group> findByName(@Param("name") String name);

    @Query("select g.name from Group g where g.name like %:name%")
    List<GroupProjection> findByNameContaining(@Param("name") String name);

    List<Group> findByNameAndUserId(String name, Long userId);
}
