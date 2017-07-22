package com.soft.repository;

import com.soft.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;

/**
 * Created by jcori on 6/30/2017.
 */
public interface UserRepository extends JpaRepository<User,Long>{
    /*
    @Query("select user from User user where user.username =:login")
    User findOneWithEagerUser(@Param("login") String login);

    @Query("select user from User user where user.status <>:removed")
    Page<User> findAllUserActive(Pageable pageable, @Param("removed") String removed);
    */
}
