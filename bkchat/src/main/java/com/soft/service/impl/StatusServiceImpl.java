package com.soft.service.impl;

import com.soft.service.StatusService;
import com.soft.service.dto.StatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jcarlos on 7/25/2017.
 */
public class StatusServiceImpl implements StatusService{
    @Override
    public StatusDTO save(StatusDTO dDTO) {
        return null;
    }

    @Override
    public Page<StatusDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public StatusDTO findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
