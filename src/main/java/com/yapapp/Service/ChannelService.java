package com.yapapp.Service;

import com.yapapp.DTO.ChannelAdminDTO;
import com.yapapp.DTO.ChannelDTO;
import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.ChannelModel;
import com.yapapp.Model.UserModel;

import java.util.List;

public interface ChannelService {

    public ChannelModel getChannel(Long channelId);

    public String createChannel(ChannelDTO newChannelDTO);

    public ChannelAdminDTO getChannelAdmin(Long channelId);

    public List<UserModel> getUsersForChannel(Long channelId);

    public String addUserToChannel(Long userId, Long channelId);
}
