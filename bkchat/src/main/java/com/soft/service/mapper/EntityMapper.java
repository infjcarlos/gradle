package com.soft.service.mapper;

/**
 * Created by jcarlos on 7/21/2017.
 */
public interface EntityMapper <D, E> {
    public E toEntity(D dto);

    public D toDto(E entity);

}
