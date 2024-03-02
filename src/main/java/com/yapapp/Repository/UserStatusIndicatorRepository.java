package com.yapapp.Repository;

import com.yapapp.Model.UserModel;
import com.yapapp.Model.UserStatusIndicatorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserStatusIndicatorRepository extends JpaRepository<UserStatusIndicatorModel, Long> {

    @Query(value = "select user_id from user_status_indicator where status like '%ONLINE%'", nativeQuery = true)
    public List<Long> getOnlineUsers();

    @Query(value = "select user_id from user_status_indicator where user_id = ?1", nativeQuery = true)
    public Long getUserById(Long userId);

    @Query(value = "select * from user_status_indicator where user_id = ?1", nativeQuery = true)
    public UserStatusIndicatorModel getUserStatusIndicator(Long userId);
}
