package com.yapapp.Controllers;

import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.UserModel;
import com.yapapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {



    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUser")
    public UserModel getUser(@RequestParam("userId") int userId) throws Exception {

        UserModel user = userRepository.getUser(userId);
        return user;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserDTO newUserDTO) throws Exception {

        String message;
        try {
            UserModel newUser = new UserModel();
            newUser.setUserName(newUserDTO.getUserName());
            newUser.setEmailId(newUserDTO.getEmailId());
            newUser.setFirstName(newUserDTO.getFirstName());
            newUser.setLastName(newUserDTO.getLastName());
            newUser.setPassword(newUserDTO.getPassword());
            newUser.setImageUrl(newUserDTO.getImageUrl());

            userRepository.save(newUser);
            message = "Success!";
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed";
            return message;
        }
    }

    @PutMapping("/editUserDetails")
    public String editUser(@RequestParam("userId") int id,@RequestBody UserDTO editUser) throws Exception {

        String message="";
        UserModel user = userRepository.getUser(id);
        if(user != null){
            user.setUserName(editUser.getUserName() != null ? editUser.getUserName() : user.getUserName());
            user.setFirstName(editUser.getFirstName() != null ? editUser.getFirstName() : user.getFirstName());
            user.setLastName(editUser.getLastName() != null ? editUser.getLastName() : user.getLastName());
            user.setEmailId(editUser.getEmailId() != null ? editUser.getEmailId() : user.getEmailId());
            user.setImageUrl(editUser.getImageUrl() != null ? editUser.getImageUrl() : user.getImageUrl());

            userRepository.save(user);
            message = "User Updated successfully!";
        }
        else{
            message = "User not found";
        }
        return message;
    }


}
