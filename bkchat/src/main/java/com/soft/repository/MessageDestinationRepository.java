package com.soft.repository;

import com.soft.domain.DestinationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jcarlos on 7/25/2017.
 */
public interface MessageDestinationRepository extends JpaRepository<DestinationMessage, Long>{
}
