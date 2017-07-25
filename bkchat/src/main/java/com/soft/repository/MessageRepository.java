package com.soft.repository;

import com.soft.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jcarlos on 7/25/2017.
 */
public interface MessageRepository extends JpaRepository<Message, Long>{
}
