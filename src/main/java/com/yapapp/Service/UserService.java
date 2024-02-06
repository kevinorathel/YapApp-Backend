package com.yapapp.Service;

import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.UserModel;

public interface UserService {

    public UserModel getUser(Long userId);

    public String addUser(UserDTO newUserDTO);

    public String editUserDetails(Long id, UserDTO editUser);
}
