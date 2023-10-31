package com.fface.group.service.notificationCheckStatus;

import com.fface.group.model.entity.NotificationCheckStatus;
import com.fface.group.respository.NotificationCheckStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationCheckStatusServiceImpl implements NotificationCheckStatusService {
    @Autowired
    private NotificationCheckStatusRepository notificationCheckStatusRepository;
    
    @Override
    public Page<NotificationCheckStatus> findAll(Pageable pageable) {
        return notificationCheckStatusRepository.findAll(pageable);
    }

    @Override
    public Optional<NotificationCheckStatus> findById(Long id) {
        return notificationCheckStatusRepository.findById(id);
    }

    @Override
    public NotificationCheckStatus save(NotificationCheckStatus notificationCheckStatus) {
        return notificationCheckStatusRepository.save(notificationCheckStatus);
    }

    @Override
    public void deleteById(Long id) {
            notificationCheckStatusRepository.deleteById(id);
    }

    @Override
    public List<NotificationCheckStatus> listNoti(Long adminId) {
        return notificationCheckStatusRepository.listNoti(adminId);
    }
}
