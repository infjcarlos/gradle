package com.soft.repository;

import com.soft.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jcarlos on 7/22/2017.
 */
public interface GroupRepository extends JpaRepository<Group,Long> {
}
