package com.soft.web.rest;
/*
import com.codahale.metrics.annotation.Timed;
import com.soft.chat.backend.domain.User;
import com.soft.chat.backend.domain.enumeration.UserType;
import com.soft.chat.backend.service.UserService;
import com.soft.chat.backend.service.dto.UserDTO;
import com.soft.chat.backend.web.rest.util.PaginationUtil;
import com.soft.chat.backend.web.rest.util.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
*/
import org.springframework.http.HttpHeaders;
//import com.soft.chat.backend.web.rest.util.HeaderUtil;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Created by jcori on 6/30/2017.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private static final String ENTITY_NAME = "user";

    @RequestMapping("/")
    public String index(){
        return "hola";
    }
}
