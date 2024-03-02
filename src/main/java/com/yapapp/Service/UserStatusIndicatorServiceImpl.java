package com.yapapp.Service;

import com.yapapp.Constants;
import com.yapapp.Model.UserModel;
import com.yapapp.Model.UserStatusIndicatorModel;
import com.yapapp.Repository.UserStatusIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserStatusIndicatorServiceImpl implements  UserStatusIndicatorService{

    @Autowired
    private UserStatusIndicatorRepository userStatusIndicatorRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private Constants constants;

    public List<UserModel> getOnlineUsers(){

        List<UserModel> onlineUsers = new ArrayList<>();
        List<Long> userIds = userStatusIndicatorRepository.getOnlineUsers();

        for(Long userId : userIds){

            UserModel user = userServiceImpl.getUser(userId);
            onlineUsers.add(user);
        }

        return onlineUsers;
    }

    public String updateUser(Long userId, String status){

        String message = "Failed";

        Long userExists = userStatusIndicatorRepository.getUserById(userId);
        UserModel user = userServiceImpl.getUser(userId);
        if(userExists != null){

            UserStatusIndicatorModel userStatus = userStatusIndicatorRepository.getUserStatusIndicator(userId);
            if(status.equalsIgnoreCase("Online")){
                userStatus.setStatus(constants.USER_STATUS_ONLINE);
            }else{
                userStatus.setStatus(constants.USER_STATUS_OFFLINE);
            }
            userStatusIndicatorRepository.save(userStatus);
            message = "Success";

        }else{

            UserStatusIndicatorModel newUserStatus = new UserStatusIndicatorModel();
            newUserStatus.setUserId(user.getId());
            if(status.equalsIgnoreCase("Online")){
                newUserStatus.setStatus(constants.USER_STATUS_ONLINE);
            }else{
                newUserStatus.setStatus(constants.USER_STATUS_OFFLINE);
            }
            userStatusIndicatorRepository.save(newUserStatus);
            message = "Success";
        }

        return message;
    }
}
