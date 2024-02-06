package com.yapapp.Repository;

import com.yapapp.Model.ChannelModel;
import com.yapapp.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChannelRepository extends JpaRepository<ChannelModel, Long> {

    @Query(value = "select * from channel where id = ?1", nativeQuery = true)
    ChannelModel getChannel(Long channelId);
}
