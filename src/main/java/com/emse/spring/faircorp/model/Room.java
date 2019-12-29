package com.emse.spring.faircorp.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue
    @Column (nullable= false)
    private Long id;
    @OneToMany
    private Set<Light> light;

    @Column (nullable= false)
    private String name;

    @Column (nullable= false)
    private long floor;
     public Room(String name, long floor){
         this.name =name;
         this.floor=floor;
     }
    public Room(){}


}
