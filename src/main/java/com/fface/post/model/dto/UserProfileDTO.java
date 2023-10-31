package com.fface.post.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
}
