package com.yapapp.Service;

import com.yapapp.DTO.ChannelDTO;
import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.ChannelModel;
import com.yapapp.Model.UserModel;
import com.yapapp.Repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService{

    @Autowired
    ChannelRepository channelRepository;

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
            message = "Success!";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed";
        }
        return message;
    }
}