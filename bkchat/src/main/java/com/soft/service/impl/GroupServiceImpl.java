package com.soft.service.impl;

import com.soft.service.GroupService;
import com.soft.service.dto.GroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jcarlos on 7/22/2017.
 */
public class GroupServiceImpl implements GroupService{

    @Override
    public GroupDTO save(GroupDTO dDTO) {
        return null;
    }

    @Override
    public Page<GroupDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public GroupDTO findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
