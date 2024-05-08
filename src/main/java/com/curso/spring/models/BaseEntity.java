package com.curso.spring.models;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "created_date", columnDefinition = "DATETIME", updatable = false, nullable = false)
    private Date createdDate;

    @Column(name = "updated_date", columnDefinition = "DATETIME", updatable = false, nullable = false)
    private Date updatedDate;

    @PrePersist
    protected void onCreate(){
        updatedDate = new Date();
        if(createdDate == null){
            createdDate = new Date();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
