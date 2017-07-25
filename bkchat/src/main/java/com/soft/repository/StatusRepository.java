package com.soft.repository;

import com.soft.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jcarlos on 7/25/2017.
 */
public interface StatusRepository extends JpaRepository<Status, Long>{
}
