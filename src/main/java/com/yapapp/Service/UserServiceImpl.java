package com.yapapp.Service;

import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.UserModel;
import com.yapapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public UserModel getUser(int userId){

        UserModel user = userRepository.getUser(userId);
        return user;
    }

    public String addUser(UserDTO newUserDTO){

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

    public String editUserDetails(int id, UserDTO editUser){

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
