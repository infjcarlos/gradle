package com.soft.web.rest;

//import com.codahale.metrics.annotation.Timed;
import com.codahale.metrics.annotation.Timed;
import com.soft.domain.User;
import com.soft.domain.enumeration.UserType;
import com.soft.service.UserService;
import com.soft.service.dto.UserDTO;
import com.soft.web.rest.util.HeaderUtil;
import com.soft.web.rest.util.PaginationUtil;
import com.soft.web.rest.util.ResponseUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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

    private final UserService userService;
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @Timed
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);
        if (userDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new user cannot already have an ID")).body(null);
        }
        UserDTO result = userService.save(userDTO);
        return ResponseEntity.created(new URI("/api/users/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/cities")
    @Timed
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to update User : {}", userDTO);
        if (userDTO.getId() == null) {
            return createUser(userDTO);
        }
        UserDTO result = userService.save(userDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userDTO.getId().toString()))
                .body(result);
    }

    @GetMapping("/users")
    @Timed
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " +"Default sort order is ascending. " +"Multiple sort criteria are supported.")
    })
    public ResponseEntity<List<UserDTO>> getAllUsers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Users");
        Page<UserDTO> page = userService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @Timed
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        UserDTO userDTO = userService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userDTO));
    }

    @DeleteMapping("/users/{id}")
    @Timed
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User : {}", id);
        userService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    //    @Timed
    /*
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);
        if (userDTO.getId() != 0) { //null
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new user cannot already have an ID")).body(null);
        }
        User result = userService.save(userDTO);
        return ResponseEntity.created(new URI("/api/users/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    */
/*
    @PutMapping("/users")
    @Timed
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to update User : {}", userDTO);
        if (userDTO.getId() == 0) {//null
            return createUser(userDTO);
        }
        User result = userService.updateUser(userDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userDTO.getId().toString()))
                .body(result);
    }

    @PutMapping("/users/{id}")
    @Timed
    public ResponseEntity<User> updateUserById(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to update User with Id: {}", userDTO);
        User result = userService.updateUserById(userDTO, id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userDTO.getId().toString()))
                .body(result);
    }

    @GetMapping("/users")
    @Timed
    public ResponseEntity<List<User>> getAllUsers(@ApiParam Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get a page of Users");
        Page<User> page = userService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/users/active")
    @Timed
    public ResponseEntity<List<User>> getAllUsersActive(@ApiParam Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get a page of Users Active");
        Page<User> page = userService.findAllUserActive(pageable, UserType.STATUS_REMOVED.getKey());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users/active");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @Timed
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        User user = userService.findOne(id);
        return Optional.ofNullable(user)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/find/{username}")
    @Timed
    public ResponseEntity<User> getUser(@PathVariable String username) {
        log.debug("REST request to get User : {}", username);
        User user = userService.findOneByUserName(username);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(user));
    }


    @DeleteMapping("/users/{id}")
    @Timed
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User : {}", id);
        userService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @DeleteMapping("/users/removed/{id}")
    @Timed
    public ResponseEntity<Void> disableUser(@PathVariable Long id) {
        log.debug("REST request to delete User Logic: {}", id);
        userService.deleteLogic(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    */
}
