package com.fface.friend.service.listFriend;

import com.fface.friend.entity.ListFriend;
import com.fface.friend.repository.ListFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListFriendServiceImpl implements ListFriendService {
    @Autowired
    private ListFriendRepository listFriendRepository;

    @Override
    public Page<ListFriend> findAll(Pageable pageable) {
        return listFriendRepository.findAll(pageable);
    }

    @Override
    public Optional<ListFriend> findById(Long id) {
        return listFriendRepository.findById(id);
    }

    @Override
    public ListFriend save(ListFriend listFriend) {
        return listFriendRepository.save(listFriend);
    }

    @Override
    public void deleteById(Long id) {
        listFriendRepository.deleteById(id);
    }

    @Override
    public List<ListFriend> findAll(Long toUserId) {
        return listFriendRepository.showAllFriends(toUserId);
    }

    @Override
    public Optional<ListFriend> findFriends(Long fromUserId, Long toUserId) {
        return this.listFriendRepository.findFriends(fromUserId, toUserId);
    }

    @Override
    public List<ListFriend> listUserAdd(Long userID, Long groupId) {
        return listFriendRepository.listUserAdd(userID, groupId);
    }
}
