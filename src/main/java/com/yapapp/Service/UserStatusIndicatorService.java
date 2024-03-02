package com.yapapp.Service;

import com.yapapp.Model.UserModel;
import java.util.List;

public interface UserStatusIndicatorService {

    public List<UserModel> getOnlineUsers();

    public String updateUser(Long userId, String status);
}
