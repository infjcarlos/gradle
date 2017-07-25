package com.soft.service.impl;

import com.soft.service.MessageService;
import com.soft.service.dto.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jcarlos on 7/25/2017.
 */
public class MessageServiceImpl implements MessageService{
    @Override
    public MessageDTO save(MessageDTO dDTO) {
        return null;
    }

    @Override
    public Page<MessageDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public MessageDTO findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
