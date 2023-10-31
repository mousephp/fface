package com.fface.base.dto;

import com.fface.manager.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagerDTO {
    private Long id;

    private User sender;

    private User receiver;

    private String content;

    private boolean status;

    private String time;
}
