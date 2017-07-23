package com.soft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.soft.service.GroupUserService;
import com.soft.service.dto.GroupUserDTO;
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
 * Created by jcarlos on 7/23/2017.
 */
@RestController
@RequestMapping("/api")
public class GroupUserResource {
    private final Logger log = LoggerFactory.getLogger(GroupUserResource.class);
    private static final String ENTITY_NAME = "groupUser";

    private final GroupUserService groupUserService;

    public GroupUserResource(GroupUserService groupUserService) {
        this.groupUserService = groupUserService;
    }

    @PostMapping("/groupusers")
    @Timed
    public ResponseEntity<GroupUserDTO> createGroupUser(@Valid @RequestBody GroupUserDTO groupUserDTO) throws URISyntaxException {
        log.debug("REST request to save GroupUser : {}", groupUserDTO);
        if (groupUserDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new GroupUser cannot already have an ID")).body(null);
        }
        GroupUserDTO result = groupUserService.save(groupUserDTO);
        return ResponseEntity.created(new URI("/api/groupusers/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/groupusers")
    @Timed
    public ResponseEntity<GroupUserDTO> updateGroupUser(@Valid @RequestBody GroupUserDTO groupUserDTO) throws URISyntaxException {
        log.debug("REST request to update GroupUser : {}", groupUserDTO);
        if (groupUserDTO.getId() == null) {
            return createGroupUser(groupUserDTO);
        }
        GroupUserDTO result = groupUserService.save(groupUserDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groupUserDTO.getId().toString()))
                .body(result);
    }

    @GetMapping("/groupusers")
    @Timed
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " +"Default sort order is ascending. " +"Multiple sort criteria are supported.")
    })
    public ResponseEntity<List<GroupUserDTO>> getAllGroupUsers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of GroupUsers");
        Page<GroupUserDTO> page = groupUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/groupusers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/groupusers/{id}")
    @Timed
    public ResponseEntity<GroupUserDTO> getGroupUser(@PathVariable Long id) {
        log.debug("REST request to get GroupUser : {}", id);
        GroupUserDTO groupUserDTO = groupUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(groupUserDTO));
    }

    @DeleteMapping("/groupusers/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroupUser(@PathVariable Long id) {
        log.debug("REST request to delete GroupUser : {}", id);
        groupUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
