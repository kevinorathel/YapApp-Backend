package com.yapapp.Controllers;

import com.yapapp.DTO.ChannelAdminDTO;
import com.yapapp.DTO.ChannelDTO;
import com.yapapp.Model.ChannelModel;
import com.yapapp.Model.UserModel;
import com.yapapp.Service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping("/getChannel")
    public ChannelModel getChannel(@RequestParam("channelId") Long channelId) throws Exception {

        return channelService.getChannel(channelId);
    }

    @PostMapping("/addChannel")
    public String createChannel(@RequestBody ChannelDTO newChannelDTO) throws Exception {

        return channelService.createChannel(newChannelDTO);
    }

    @GetMapping("/getChannelAdmin")
    public ChannelAdminDTO getChannelAdmin(@RequestParam("channelId") Long channelId) throws Exception {

        return channelService.getChannelAdmin(channelId);
    }

    @GetMapping("/getUsersForChannel")
    public List<UserModel> getUsersForChannel(@RequestParam("channelId") Long channelId) throws Exception {

        return channelService.getUsersForChannel(channelId);
    }

    @PostMapping("/addUserToChannel")
    public String addUserToChannel(@RequestBody Long userId, @RequestBody Long channelId) throws Exception {

        return channelService.addUserToChannel(userId,channelId);
    }

}
