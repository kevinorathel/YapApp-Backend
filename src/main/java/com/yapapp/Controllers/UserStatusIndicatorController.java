package com.yapapp.Controllers;

import com.yapapp.Model.UserModel;
import com.yapapp.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userStatus")
public class UserStatusIndicatorController {

    @Autowired
    private UserStatusIndicatorService userStatusIndicatorService;

    @GetMapping("/getOnlineUsers")
    public List<UserModel> getUser() throws Exception {

        return userStatusIndicatorService.getOnlineUsers();
    }

    @PutMapping("/updateUser")
    public String addUser(@RequestParam("userId") Long userId,@RequestParam("status") String status) throws Exception {

        return userStatusIndicatorService.updateUser(userId, status);
    }
}
