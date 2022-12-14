package com.codevui.realworldapp.model.user.mapper;

import com.codevui.realworldapp.entity.User;
import com.codevui.realworldapp.model.user.dto.UserDTOCreate;
import com.codevui.realworldapp.model.user.dto.UserDTOResponse;
import com.codevui.realworldapp.model.user.dto.UserDTOUpdate;

public class UserMapper {
    public static UserDTOResponse toUserDTOResponse(User user) {
        return UserDTOResponse.builder().email(user.getEmail()).username(user.getUsername()).bio(user.getBio())
                .image(user.getImage()).build();
    }

    public static User toUser(UserDTOCreate userDTOCreate) {
        return User.builder().username(userDTOCreate.getUsername()).email(userDTOCreate.getEmail())
                .password(userDTOCreate.getPassword()).build();
    }

    public static void toUser(User user, UserDTOUpdate userDTOUpdate) {
        user.setEmail(userDTOUpdate.getEmail());
        user.setBio(userDTOUpdate.getBio());
        user.setImage(userDTOUpdate.getImage());
    }
}
