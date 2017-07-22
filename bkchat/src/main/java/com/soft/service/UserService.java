package com.soft.service;

import com.soft.domain.User;
import com.soft.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jcori on 6/30/2017.
 */
public interface UserService extends EntityService<UserDTO, Long>{
    //User save(UserDTO userDTO);

    /*
    UserDTO save(UserDTO userDTO);
    Page<UserDTO> findAll(Pageable pageable);
    UserDTO findOne(Long id);
    void delete(Long id);
    */
    /*
    Page<User> findAll(Pageable pageable);
    Page<User> findAllUserActive(Pageable pageable, String status);
    User findOne(Long id);
    User findOneByUserName(String username);
    User updateUser(UserDTO userDTO);
    User updateUserById(UserDTO userDTO, Long id);
    void delete(Long id);
    void deleteLogic(Long id);
    */
}
