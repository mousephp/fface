package com.fface.post.repository;

import com.fface.post.model.entity.StatusPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPostRepository extends JpaRepository<StatusPost, Long> {
}
