package com.yapapp.Service;

import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.UserModel;
import com.yapapp.Repository.UserRepository;
import com.yapapp.Util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public UserModel getUser(Long userId) {

        UserModel user = userRepository.getUserById(userId);
        return user;
    }

    public Boolean userLogin(String userName, String password){

        Boolean status = false;
        UserModel user = userRepository.getUserByUsername(userName);
        if(user != null){
            String decryptedPassword = AESUtil.decryptPassword(user.getPassword());
            if(password == decryptedPassword){
                status = true;
            }
        }
        return status;
    }

    public String addUser(UserDTO newUserDTO){

        String message;
        try {
            UserModel newUser = new UserModel();
            newUser.setUserName(newUserDTO.getUserName());
            newUser.setEmailId(newUserDTO.getEmailId());
            newUser.setFirstName(newUserDTO.getFirstName());
            newUser.setLastName(newUserDTO.getLastName());
            String password = AESUtil.encryptPassword(newUserDTO.getPassword());
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
        UserModel user = userRepository.getUserById(id);
        if(user != null){
            user.setUserName(editUser.getUserName() != null ? editUser.getUserName() : user.getUserName());
            user.setFirstName(editUser.getFirstName() != null ? editUser.getFirstName() : user.getFirstName());
            user.setLastName(editUser.getLastName() != null ? editUser.getLastName() : user.getLastName());
            user.setEmailId(editUser.getEmailId() != null ? editUser.getEmailId() : user.getEmailId());
            user.setImageUrl(editUser.getImageUrl() != null ? editUser.getImageUrl() : user.getImageUrl());
            user.setPassword(editUser.getPassword() != null ? AESUtil.encryptPassword(editUser.getPassword()) : user.getPassword());

            userRepository.save(user);
            message = "User Updated successfully!";
        }
        else{
            message = "User not found";
        }
        return message;
    }


}
