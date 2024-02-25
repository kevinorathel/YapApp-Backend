package com.yapapp.Service;

import com.yapapp.DTO.ChannelAdminDTO;
import com.yapapp.DTO.ChannelDTO;
import com.yapapp.Model.ChannelModel;
import com.yapapp.Model.UserModel;
import com.yapapp.Model.UserToChannelModel;
import com.yapapp.Repository.ChannelRepository;
import com.yapapp.Repository.UserRepository;
import com.yapapp.Repository.UserToChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService{

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserToChannelRepository userToChannelRepository;

    public ChannelModel getChannel(Long channelId){

        return channelRepository.getChannel(channelId);
    }

    public String createChannel(ChannelDTO newChannelDTO){

        String message;
        try {
            ChannelModel newChannel = new ChannelModel();
            newChannel.setName(newChannelDTO.getName());
            newChannel.setCode(newChannelDTO.getCode());
            newChannel.setAdminUserId(newChannelDTO.getAdminUserId());
            channelRepository.save(newChannel);
            addUserToChannel(newChannelDTO.getAdminUserId(), newChannel.getId());
            message = "Success!";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed";
        }
        return message;
    }

    public ChannelAdminDTO getChannelAdmin(Long channelId){

        List<Object[]> admin = channelRepository.getChannelAdmin(channelId);
        ChannelAdminDTO adminUser = new ChannelAdminDTO();
        for(Object[] adminObj : admin){
            adminUser.setId(adminObj[0] != null ? (Long) adminObj[0] : null);
            adminUser.setFirstName(adminObj[1] != null ? (String) adminObj[1] : null);
            adminUser.setLastName(adminObj[2] != null ? (String) adminObj[2] : null);
        }

        return adminUser;
    }

    public List<UserModel> getUsersForChannel(Long channelId){

        List<UserModel> channelUsers = new ArrayList<>();
        List<Long> usersInChannel = channelRepository.getUsersForChannel(channelId);
        for(Long userId : usersInChannel){
            UserModel user = userRepository.getUserById(userId);
            channelUsers.add(user);
        }
        return channelUsers;
    }

    public String addUserToChannel(Long userId, Long channelId){

        String message = "";
        UserModel user = userRepository.getUserById(userId);
        if(user == null){
            message = "User not found!";
            return message;
        }
        ChannelModel channel = getChannel(channelId);
        if(channel == null){
            message = "Channel not found!";
            return message;
        }
        UserToChannelModel addUser = new UserToChannelModel();
        addUser.setUserId(userId);
        addUser.setChannelId(channelId);
        userToChannelRepository.save(addUser);
        message = "Added User to channel successfully!";

        return message;

    }
}
