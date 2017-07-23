package com.soft.service.mapper;

import com.soft.domain.GroupUser;
import com.soft.service.dto.GroupUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by jcarlos on 7/23/2017.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class,GroupMapper.class,})
public interface GroupUserMapper extends EntityMapper<GroupUserDTO, GroupUser>{

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "group.id", target = "groupId")
    GroupUserDTO toDto(GroupUser groupUser);
    GroupUser toEntity(GroupUserDTO groupUserDTO);
}
