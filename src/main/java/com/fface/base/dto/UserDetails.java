package com.fface.base.dto;

import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private UserInfo userinfo;

    private List<PostUserFrontEndDto> postUserFrontEnds;


}
