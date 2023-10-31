package com.fface.post.service.statusPost;

import com.fface.post.model.entity.StatusPost;
import com.fface.post.repository.StatusPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusPostRepository statusPostRepository;
    
    @Override
    public Page<StatusPost> findAll(Pageable pageable) {
        return statusPostRepository.findAll(pageable);
    }

    @Override
    public Optional<StatusPost> findById(Long id) {
        return statusPostRepository.findById(id);
    }

    @Override
    public StatusPost save(StatusPost statusPost) {
        return statusPostRepository.save(statusPost);
    }

    @Override
    public void deleteById(Long id) {
        statusPostRepository.deleteById(id);
    }

    @Override
    public List<StatusPost> findAll() {
        return statusPostRepository.findAll();
    }
}
