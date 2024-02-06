package com.yapapp.Service;

import com.yapapp.DTO.ChannelAdminDTO;
import com.yapapp.DTO.ChannelDTO;
import com.yapapp.DTO.UserDTO;
import com.yapapp.Model.ChannelModel;
import com.yapapp.Model.UserModel;
import com.yapapp.Repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
