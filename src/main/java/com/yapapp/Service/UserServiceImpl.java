package com.yapapp.Service;

import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.UserModel;
import com.yapapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public UserModel getUser(Long userId) {

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
            String password = AESUtilService.encryptPassword(newUserDTO.getPassword());
            newUser.setPassword(password);
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

    public String editUserDetails(Long id, UserDTO editUser) {

        String message="";
        UserModel user = userRepository.getUser(id);
        if(user != null){
            user.setUserName(editUser.getUserName() != null ? editUser.getUserName() : user.getUserName());
            user.setFirstName(editUser.getFirstName() != null ? editUser.getFirstName() : user.getFirstName());
            user.setLastName(editUser.getLastName() != null ? editUser.getLastName() : user.getLastName());
            user.setEmailId(editUser.getEmailId() != null ? editUser.getEmailId() : user.getEmailId());
            user.setImageUrl(editUser.getImageUrl() != null ? editUser.getImageUrl() : user.getImageUrl());
            user.setPassword(editUser.getPassword() != null ? AESUtilService.encryptPassword(editUser.getPassword()) : user.getPassword());

            userRepository.save(user);
            message = "User Updated successfully!";
        }
        else{
            message = "User not found";
        }
        return message;
    }


}
