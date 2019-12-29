package com.emse.spring.faircorp.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Light {

    @Id
    @GeneratedValue
    @Column (nullable= false)
    private Long id;
    @ManyToOne
    private Room room;

    private Integer level;


    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Status status;
    public Light() {
    }

    public Light(Integer level, Status status, Room room) {
        this.level = level;
        this.status = status;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}