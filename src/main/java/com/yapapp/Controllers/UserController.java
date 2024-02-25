package com.yapapp.Controllers;

import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.UserModel;
import com.yapapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Boolean userLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) throws Exception {

        return userService.userLogin(userName, password);
    }

    @GetMapping("/getUser")
    public UserModel getUser(@RequestParam("userId") Long userId) throws Exception {

        return userService.getUser(userId);
    }

    @PostMapping("/signup")
    public String addUser(@RequestBody UserDTO newUserDTO) throws Exception {

         return userService.addUser(newUserDTO);
    }

    @PutMapping("/editUserDetails")
    public String editUser(@RequestParam("userId") Long id,@RequestBody UserDTO editUser) throws Exception {

        return userService.editUserDetails(id, editUser);
    }


}
