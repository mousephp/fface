package com.fface.group.respository;

import com.fface.group.model.entity.ImagePostGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagePostGroupRepository extends JpaRepository<ImagePostGroup, Long> {
    @Query(value = "select * from image_post_group where post_group_id = ?1", nativeQuery = true)
    ImagePostGroup[] listImage(Long postGroupId);
}
