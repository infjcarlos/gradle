package com.soft.service.mapper;

import com.soft.domain.Message;
import com.soft.service.dto.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by jcarlos on 7/25/2017.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface MessageMapper extends EntityMapper<MessageDTO, Message>{

    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "user.id", target = "userId")
    MessageDTO toDto(Message message);
    Message toEntity(MessageDTO messageDTO);
}
