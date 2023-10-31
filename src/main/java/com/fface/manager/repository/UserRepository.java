package com.fface.manager.repository;

import com.fface.manager.model.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    User findByUsername(String username);

    @Query(value="select * from user", nativeQuery = true)
    List<User> findAllUser();

    @Modifying
    @Query(value = "call deleteUser(?1)", nativeQuery = true)
    void deleteUserByAdmin(Long id);
}
