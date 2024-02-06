package com.yapapp.Repository;

import com.yapapp.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query(value = "select * from users where id = ?1", nativeQuery = true)
    UserModel getUser(Long userId);
}
