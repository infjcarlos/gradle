package com.soft.service.impl;

import com.soft.domain.Group;
import com.soft.repository.GroupRepository;
import com.soft.service.GroupService;
import com.soft.service.dto.GroupDTO;
import com.soft.service.mapper.GroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jcarlos on 7/22/2017.
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService{

    private final Logger log = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupDTO save(GroupDTO dDTO) {
        log.debug("Request to save GroupDTO : {}", dDTO);
        Group group = groupMapper.toEntity(dDTO);
        group = groupRepository.save(group);
        GroupDTO result = groupMapper.toDto(group);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Groups");
        return groupRepository.findAll(pageable).map(groupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupDTO findOne(Long id) {
        log.debug("Request to get Group : {}", id);
        Group group = groupRepository.findOne(id);
        return groupMapper.toDto(group);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Group : {}", id);
        groupRepository.delete(id);
    }
}
