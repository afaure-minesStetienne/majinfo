package com.emse.spring.faircorp;

import com.emse.spring.faircorp.model.Light;
import com.emse.spring.faircorp.model.Room;

import java.util.Set;

public class RoomDto {
    private Long id;
    private String name;
    private Long floor;
    private Set<Light> lights;

    public RoomDto() {}

    public RoomDto(Room room){
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.lights = room.getLights();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getFloor() {
        return floor;
    }

    public Set<Light> getLights() {
        return lights;
    }
}
