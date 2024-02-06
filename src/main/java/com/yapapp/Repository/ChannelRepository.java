package com.yapapp.Repository;

import com.yapapp.Model.ChannelModel;
import com.yapapp.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChannelRepository extends JpaRepository<ChannelModel, Long> {

    @Query(value = "select * from channel where id = ?1", nativeQuery = true)
    ChannelModel getChannel(Long channelId);

    @Query(value = "select u.id,u.first_name,u.last_name from channel c " +
            "left join users u on c.admin_user_id = u.id " +
            "where c.id = ?1", nativeQuery = true)
    List<Object[]> getChannelAdmin(Long channelId);
}
