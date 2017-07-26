package com.soft.service.impl;

import com.soft.domain.DestinationMessage;
import com.soft.domain.Message;
import com.soft.domain.Status;
import com.soft.domain.User;
import com.soft.repository.DestinationMessageRepository;
import com.soft.repository.MessageRepository;
import com.soft.repository.StatusRepository;
import com.soft.repository.UserRepository;
import com.soft.service.MessageService;
import com.soft.service.dto.MessageDTO;
import com.soft.service.mapper.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jcarlos on 7/25/2017.
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    private final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final MessageRepository messageRepository;
    private final DestinationMessageRepository destinationMessageRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageRepository messageRepository, DestinationMessageRepository destinationMessageRepository, UserRepository userRepository, StatusRepository statusRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.destinationMessageRepository = destinationMessageRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.messageMapper = messageMapper;
    }

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

    @Override
    public MessageDTO save(MessageDTO dDTO, Long destinationUserId) {

        log.debug("Request to save MessageDTO : {}", dDTO);
        Message message = messageMapper.toEntity(dDTO);

        User userMsg = userRepository.findOne(dDTO.getUserId());
        Status status = statusRepository.findOne(dDTO.getStatusId());

        message.setUser(userMsg);
        message.setStatus(status);

        Set<DestinationMessage> destinationMessages = new HashSet<DestinationMessage>();

        DestinationMessage destinationMessage = new DestinationMessage();
        destinationMessage.setUser( userRepository.findOne(destinationUserId) );
        destinationMessage.setMessage(message);

        destinationMessages.add(destinationMessage);
        message.setDestinationMessages(destinationMessages);

        message = messageRepository.save(message);
        MessageDTO result = messageMapper.toDto(message);

        return result;
    }
}
