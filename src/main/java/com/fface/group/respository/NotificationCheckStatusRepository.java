package com.fface.group.respository;

import com.fface.group.model.entity.NotificationCheckStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationCheckStatusRepository extends JpaRepository<NotificationCheckStatus, Long> {
    @Query(value = "select * from notification_check_status where admin_id = ?1 ", nativeQuery = true)
    List<NotificationCheckStatus> listNoti(Long adminId);
}
