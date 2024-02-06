package com.yapapp.Controllers;

import com.yapapp.DTO.ChannelAdminDTO;
import com.yapapp.DTO.ChannelDTO;
import com.yapapp.Model.ChannelModel;
import com.yapapp.Model.UserModel;
import com.yapapp.Service.ChannelService;
import com.yapapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
