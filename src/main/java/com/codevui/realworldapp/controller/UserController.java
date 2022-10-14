package com.codevui.realworldapp.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codevui.realworldapp.exception.custom.CustomBadRequestException;
import com.codevui.realworldapp.exception.custom.CustomNotFoundException;
import com.codevui.realworldapp.model.user.dto.UserDTOCreate;
import com.codevui.realworldapp.model.user.dto.UserDTOLoginRequest;
import com.codevui.realworldapp.model.user.dto.UserDTOResponse;
import com.codevui.realworldapp.model.user.dto.UserDTOUpdate;
import com.codevui.realworldapp.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/login")
    public Map<String, UserDTOResponse> login(@RequestBody Map<String, UserDTOLoginRequest> userLoginRequestMap)
            throws CustomBadRequestException {
        return userService.authenticate(userLoginRequestMap);
    }

    @PostMapping("/users")
    public Map<String, UserDTOResponse> registerUser(
            @RequestBody Map<String, UserDTOCreate> userDTOCreateMap) {
        return userService.registerUser(userDTOCreateMap);
    }

    @GetMapping("/user")
    public Map<String, UserDTOResponse> getCurrentUser() throws CustomNotFoundException {
        return userService.getCurrentUser();
    }

    @PutMapping(value = "/user")
    public Map<String, UserDTOResponse> updateCurrentUser(@RequestBody Map<String, UserDTOUpdate> userDTOUpdateMap)
            throws CustomNotFoundException {

        return userService.updateCurrentUser(userDTOUpdateMap);
    }
}
