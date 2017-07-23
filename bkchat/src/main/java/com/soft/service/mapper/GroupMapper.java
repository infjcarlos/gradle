package com.soft.service.mapper;

import com.soft.domain.Group;
import com.soft.service.dto.GroupDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by jcarlos on 7/22/2017.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupMapper extends EntityMapper<GroupDTO, Group>{
    //@Mapping(source = "group.owner", target = "user_id")
    GroupDTO toDto(Group group);
    Group toEntity(GroupDTO userDTO);
}
