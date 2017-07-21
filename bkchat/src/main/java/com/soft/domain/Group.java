package com.soft.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jcarlos on 7/21/2017.
 */
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User owner;

    private String name;
    private String logo;
    private Date creationDate;
}
