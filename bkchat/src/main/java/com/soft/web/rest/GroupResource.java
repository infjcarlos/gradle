package com.soft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.soft.service.GroupService;
import com.soft.service.dto.GroupDTO;
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
 * Created by jcarlos on 7/22/2017.
 */
@RestController
@RequestMapping("/api")
public class GroupResource {

    private final Logger log = LoggerFactory.getLogger(GroupResource.class);
    private static final String ENTITY_NAME = "groups";

    private final GroupService groupService;

    public GroupResource(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/groups")
    @Timed
    public ResponseEntity<GroupDTO> createGroup(@Valid @RequestBody GroupDTO groupDTO) throws URISyntaxException {
        log.debug("REST request to save Group : {}", groupDTO);
        if (groupDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new Group cannot already have an ID")).body(null);
        }
        GroupDTO result = groupService.save(groupDTO);
        return ResponseEntity.created(new URI("/api/groups/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/groups")
    @Timed
    public ResponseEntity<GroupDTO> updateGroup(@Valid @RequestBody GroupDTO groupDTO) throws URISyntaxException {
        log.debug("REST request to update Group : {}", groupDTO);
        if (groupDTO.getId() == null) {
            return createGroup(groupDTO);
        }
        GroupDTO result = groupService.save(groupDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groupDTO.getId().toString()))
                .body(result);
    }

    @GetMapping("/groups")
    @Timed
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " +"Default sort order is ascending. " +"Multiple sort criteria are supported.")
    })
    public ResponseEntity<List<GroupDTO>> getAllGroups(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Groups");
        Page<GroupDTO> page = groupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/groups/{id}")
    @Timed
    public ResponseEntity<GroupDTO> getGroup(@PathVariable Long id) {
        log.debug("REST request to get Group : {}", id);
        GroupDTO groupDTO = groupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(groupDTO));
    }

    @DeleteMapping("/groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        log.debug("REST request to delete Group : {}", id);
        groupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
