package com.soft.service;

import com.soft.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jcori on 7/22/2017.
 */
public interface EntityService<D, E> {
    D save(D dDTO);
    Page<D> findAll(Pageable pageable);
    D findOne(E id);
    void delete(E id);
}
