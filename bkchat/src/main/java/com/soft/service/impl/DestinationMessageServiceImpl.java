package com.soft.service.impl;

import com.soft.service.DestinationMessageService;
import com.soft.service.dto.DestinationMessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jcarlos on 7/25/2017.
 */
public class DestinationMessageServiceImpl implements DestinationMessageService{
    @Override
    public DestinationMessageDTO save(DestinationMessageDTO dDTO) {
        return null;
    }

    @Override
    public Page<DestinationMessageDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public DestinationMessageDTO findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
