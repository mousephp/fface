package com.fface.manager.repository;

import com.fface.manager.model.entity.User;
import com.fface.manager.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query(value = "select user_id from  user_info where email = ?1 and phone_number = ?2",nativeQuery = true)
    Long findUserId(String email, String phone_number) ;

    @Query(value="select  * from user_info where user_id = ?1", nativeQuery = true)
    Optional<UserInfo> findByUserId(Long userId);

    @Query(value="select * from user_info where user_id <> ?1", nativeQuery = true)
    List<UserInfo> showAllUserInfo(Long currentUser);

    @Query(value="select * from user_info", nativeQuery = true)
    List<UserInfo> findAllUser();
    
    @Query(value = "call findByFullName(?1)", nativeQuery = true)
    List<UserInfo> findByFullNameContaining(String fullName);

    UserInfo findByUser(User user);
}
