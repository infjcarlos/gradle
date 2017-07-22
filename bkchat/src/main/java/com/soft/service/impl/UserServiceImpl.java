package com.soft.service.impl;

import com.soft.domain.User;
import com.soft.domain.enumeration.UserType;
import com.soft.repository.UserRepository;
import com.soft.service.UserService;
import com.soft.service.dto.UserDTO;
import com.soft.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jcori on 6/30/2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("Request to save UserDTO : {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        UserDTO result = userMapper.toDto(user);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Users");
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findOne(Long id) {
        log.debug("Request to get User : {}", id);
        User user = userRepository.findOne(id);
        return userMapper.toDto(user);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete User : {}", id);
        userRepository.delete(id);
    }

    /*
    @Override
    public User save(UserDTO userDTO) {
        log.debug("Request to save user : {}", userDTO);
        User user = new User();
        user.setFirstname(userDTO.getFirstName());
        user.setLastname(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setStatus(UserType.STATUS_ACTIVE.getKey());
        user = userRepository.save(user);
        return user;
    }
    */
/*
    @Override
    public User updateUser(UserDTO userDTO) {
        log.debug("Request to Update By user", userDTO);
        User user = userRepository.findOne(userDTO.getId());
        user.setFirstName(userDTO.getFirstName() );
        user.setLastName(userDTO.getLastName() );
        user.setUsername(userDTO.getUsername() );
        user.setPassword(userDTO.getPassword() );
        user.setEmail( userDTO.getEmail() );
        user.setStatus( userDTO.getStatus() );
        userRepository.save(user);
        return user;
    }
    @Override
    @Transactional(readOnly = true)
    public User findOneByUserName(String username) {
        log.debug("Request to get User : {}", username);
        User user = userRepository.findOneWithEagerUser(username);
        return user;
    }

    @Override
    public User updateUserById(UserDTO userDTO, Long id){
        log.debug("Request to Update By Id user", userDTO);
        User user = userRepository.findOne(id);
        user.setFirstName(userDTO.getFirstName() );
        user.setLastName(userDTO.getLastName() );
        user.setUsername(userDTO.getUsername() );
        user.setPassword(userDTO.getPassword() );
        user.setEmail( userDTO.getEmail() );
        user.setStatus( userDTO.getStatus() );
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        log.debug("Request to get all users");
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAllUserActive(Pageable pageable, String status) {
        log.debug("Request to get all users activies");
        return userRepository.findAllUserActive(pageable, status);
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete User : {}", id);
        userRepository.delete(id);
    }

    @Override
    public void deleteLogic(Long id) {
        User user = userRepository.findOne(id);
        user.setStatus( UserType.STATUS_REMOVED.getKey() );
        userRepository.save(user);
    }
*/
}
