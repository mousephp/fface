package com.fface.group.respository;

import com.fface.group.model.entity.PostGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostGroupRepository extends JpaRepository<PostGroup, Long> {
    @Query(value = "select * from post_group where status_check = true and group_id = ?1 ", nativeQuery = true)
    List<PostGroup> listPost(Long groupId);
}
