package com.yapapp.Service;

import com.yapapp.DTO.ChannelDTO;
import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.ChannelModel;
import com.yapapp.Model.UserModel;

public interface ChannelService {

    public ChannelModel getChannel(Long channelId);

    public String createChannel(ChannelDTO newChannelDTO);
}
