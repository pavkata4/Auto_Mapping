package com.example._16_auto_mapping.entities;

import javax.persistence.*;
import java.lang.annotation.RetentionPolicy;

@MappedSuperclass
public abstract class BaseClass {


    private long id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }
}
