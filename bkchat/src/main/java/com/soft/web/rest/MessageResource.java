package com.soft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.soft.service.MessageService;
import com.soft.service.dto.MessageDTO;
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
 * Created by jcarlos on 7/25/2017.
 */
@RestController
@RequestMapping("/api")
public class MessageResource {
    private final Logger log = LoggerFactory.getLogger(MessageResource.class);
    private static final String ENTITY_NAME = "message";

    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message/{destinationUserId}")
    @Timed
    public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Long destinationUserId) throws URISyntaxException {
        log.debug("REST request to save Message : {}", messageDTO);
        if (messageDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new Message cannot already have an ID")).body(null);
        }
        MessageDTO result = messageService.save(messageDTO, destinationUserId);
        return ResponseEntity.created(new URI("/api/message/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
}
