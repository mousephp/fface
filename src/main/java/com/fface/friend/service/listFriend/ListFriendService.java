package com.fface.friend.service.listFriend;

import com.fface.friend.entity.ListFriend;
import com.fface.base.service.GeneralService;

import java.util.List;
import java.util.Optional;

public interface ListFriendService extends GeneralService<ListFriend> {
    List<ListFriend> findAll(Long toUserId);

    Optional<ListFriend> findFriends(Long fromUserId, Long toUserId);

    List<ListFriend> listUserAdd(Long userID, Long groupId);
}
