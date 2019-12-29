package com.emse.spring.faircorp;

import com.emse.spring.faircorp.model.Light;
import com.emse.spring.faircorp.model.Status;

public class LightDto {

    private Long room_Id;
    private  Long id;

    private Integer level;
    private double a;
    private double b;
    private Status status;

    public LightDto() {
    }

    public LightDto(Light light) {
        this.room_Id = light.getRoom().getId();
        this.id = light.getId();
        this.level = light.getLevel();
        this.status = light.getStatus();
        this.a = light.getA();
        this.b = light.getB();
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }

    public Long getRoom_Id() {
        return room_Id;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}