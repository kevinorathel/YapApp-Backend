package com.yapapp.Service;

import com.yapapp.Util.Constants;
import com.yapapp.Model.UserModel;
import com.yapapp.Model.UserStatusIndicatorModel;
import com.yapapp.Repository.UserStatusIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStatusIndicatorServiceImpl implements  UserStatusIndicatorService{

    @Autowired
    private UserStatusIndicatorRepository userStatusIndicatorRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;


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
                userStatus.setStatus(Constants.USER_STATUS_ONLINE);
            }else{
                userStatus.setStatus(Constants.USER_STATUS_OFFLINE);
            }
            userStatusIndicatorRepository.save(userStatus);
            message = "Success";

        }else{

            UserStatusIndicatorModel newUserStatus = new UserStatusIndicatorModel();
            newUserStatus.setUserId(user.getId());
            if(status.equalsIgnoreCase("Online")){
                newUserStatus.setStatus(Constants.USER_STATUS_ONLINE);
            }else{
                newUserStatus.setStatus(Constants.USER_STATUS_OFFLINE);
            }
            userStatusIndicatorRepository.save(newUserStatus);
            message = "Success";
        }

        return message;
    }
}
