package com.soft.service.mapper;

import com.soft.domain.*;
import com.soft.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Created by jcarlos on 7/21/2017.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper <UserDTO, User> {
/*
    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.firstname", target = "firstname")
    @Mapping(source = "user.lastname", target = "lastname")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.password", target = "password")
    @Mapping(source = "user.status", target = "status")*/
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
    /*
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "firstname", source = "firstname"),
            @Mapping(target = "lastname", source = "lastname"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "status", source = "status")
    })
    User toEntity(UserDTO userDTO);
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "firstname", source = "firstname"),
            @Mapping(target = "lastname", source = "lastname"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "status", source = "status")
    })
    UserDTO toDto(User user);
    */
}
