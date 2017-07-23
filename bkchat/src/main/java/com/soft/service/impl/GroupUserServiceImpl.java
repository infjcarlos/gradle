package com.soft.service.impl;


import com.soft.domain.Group;
import com.soft.domain.GroupUser;
import com.soft.domain.User;
import com.soft.repository.GroupRepository;
import com.soft.repository.GroupUserRepository;
import com.soft.repository.UserRepository;
import com.soft.service.GroupUserService;
import com.soft.service.dto.GroupUserDTO;
import com.soft.service.mapper.GroupMapper;
import com.soft.service.mapper.GroupUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jcarlos on 7/23/2017.
 */
@Service
@Transactional
public class GroupUserServiceImpl implements GroupUserService{
    private final Logger log = LoggerFactory.getLogger(GroupUserServiceImpl.class);

    private final GroupUserRepository groupUserRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupUserMapper groupUserMapper;

    public GroupUserServiceImpl(GroupUserRepository groupUserRepository, UserRepository userRepository, GroupRepository groupRepository, GroupUserMapper groupUserMapper) {
        this.groupUserRepository = groupUserRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupUserMapper = groupUserMapper;
    }

    @Override
    public GroupUserDTO save(GroupUserDTO dDTO) {
        log.debug("Request to save GroupUserDTO : {}", dDTO);
        GroupUser groupUser = groupUserMapper.toEntity(dDTO);

        User user = userRepository.findOne(dDTO.getUserId());
        Group group = groupRepository.findOne(dDTO.getGroupId());

        groupUser.setGroup(group);
        groupUser.setUser(user);
        groupUser = groupUserRepository.save(groupUser);

        GroupUserDTO result = groupUserMapper.toDto(groupUser);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Groups Users");
        return groupUserRepository.findAll(pageable).map(groupUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupUserDTO findOne(Long id) {
        log.debug("Request to get Group User: {}", id);
        GroupUser groupUser = groupUserRepository.findOne(id);
        return groupUserMapper.toDto(groupUser);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Group User: {}", id);
        groupUserRepository.delete(id);
    }
}
