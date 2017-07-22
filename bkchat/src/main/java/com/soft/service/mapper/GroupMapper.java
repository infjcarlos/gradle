package com.soft.service.mapper;

import com.soft.domain.Group;
import com.soft.service.dto.GroupDTO;
import org.mapstruct.Mapper;

/**
 * Created by jcarlos on 7/22/2017.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupMapper extends EntityMapper<GroupDTO, Group>{
    GroupDTO toDto(Group user);
    Group toEntity(GroupDTO userDTO);
}
