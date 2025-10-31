package org.lilyana.controller;

import lombok.RequiredArgsConstructor;
import org.lilyana.common.convention.result.Result;
import org.lilyana.common.convention.result.Results;
import org.lilyana.dto.req.UserRegisterReqDTO;
import org.lilyana.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/api/admin/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO userRegisterReqDTO) {
        userService.register(userRegisterReqDTO);
        return Results.success();
    }
}
