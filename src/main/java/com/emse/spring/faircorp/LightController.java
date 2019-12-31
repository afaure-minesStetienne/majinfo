package com.emse.spring.faircorp;


import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController  // 1. RestController is a Spring stereotype to mark a class as a rest service
@RequestMapping("/api") // 2. @RequestMapping is used to define the URL prefix used to manage a resource (in our example we manage lights)
@Transactional // 3. In this example we will use a DAO and this DAO is injected via @Autowired
public class LightController {

    @Autowired
    private LightDao lightDao; // 4. @GetMapping indicates that the following method will respond to a GET request. This method will return a light list. We transform our entities Light in LightDto
    @Autowired
    private RoomDao roomDao;

    // GET ALL LIGHTS
    @GetMapping(path = "/lights") // 5.
    public List<LightDto> findAllLights() {
        return lightDao.findAll()
                .stream()
                .map(LightDto::new)
                .collect(Collectors.toList());
    }

    // GET A LIGHT BY ITS ID
    @GetMapping(path = "/lights/{id}")
    public LightDto findLightById(@PathVariable Long id) {
        return lightDao.findById(id).map(light -> new LightDto(light)).orElse(null);
    }

    /*// GET ALL LIGHTS OF A ROOM BY ITS ID
    @GetMapping(path = "/rooms/{id}")
    public List<LightDto> findLightByRoomId(@PathVariable Long id) {
        return roomDao.findById(id).map(room -> new RoomDto(room)).orElse(null);
    }*/

    // GET ALL LIGHTS OF A ROOM BY ITS NAME
    @GetMapping(path = "/rooms/{name}")
    public RoomDto findRoomByName(@PathVariable String name){
        return(new RoomDto(roomDao.findOnName(name)));
    }

    //
    @PutMapping(path = "lights/{id}/switch")
    public LightDto switchStatus(@PathVariable Long id) {
        Light light = lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON);
        return new LightDto(light);
    }

    @PutMapping(path = "lights/{id}/change")
    public LightDto changeValues(@PathVariable Long id, @RequestBody LightDto dto){
        Light light = lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setA(dto.getA());
        light.setB(dto.getB());
        light.setLevel(dto.getLevel());
        return new LightDto(light);
    }


    //
    /*@PostMapping
    public LightDto create(@RequestBody LightDto dto) {
        Light light = null;
        if (dto.getId() != null) {
            light = lightDao.findById(dto.getId()).orElse(null);
        }

        if (light == null) {
            light = lightDao.save(new Light(roomDao.getOne(dto.getRoom_Id()), dto.getLevel(), dto.getStatus()));
        } else {
            light.setLevel(dto.getLevel());
            light.setStatus(dto.getStatus());
            lightDao.save(light);
        }

        return new LightDto(light);
    }*/

    /*@DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        lightDao.deleteById(id);
    }*/
}