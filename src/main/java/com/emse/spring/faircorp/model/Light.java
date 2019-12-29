package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

// 1:Mark this class as a JPA entity
@Entity //indicates that this class is an entity managed by Hibernate
public class Light {

    @ManyToOne
    private Room room;

    // 2:Declare this field as the table ID. This ID must to be auto generated
    @Id
    @GeneratedValue
    private Long light_id;

    // 4:status is also not nullable, and this field is an enumeration. You have to add this information
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Status status;

    // 3:This field must be not nullable
    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private double a;

    @Column(nullable = false)
    private double b;




    public Light() {
    }

    public Light(Room room){
        setRoom(room);
    }

    public Light(Integer level, Status status, double a, double b) {
        this.level = level;
        this.status = status;
        this.a = a;
        this.b = b;
    }

    public Long getId() {
        return this.light_id;
    }

    public void setId(Long id) {
        this.light_id = id;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}