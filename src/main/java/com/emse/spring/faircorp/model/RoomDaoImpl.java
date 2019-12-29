package com.emse.spring.faircorp.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl implements RoomDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Room findOnName(String name){
        String jpql = "select rm from Room rm where rm.name = :value";
        return em.createQuery(jpql, Room.class)
                .setParameter("value",name)
                .getResultList()
                .get(1);
    }
}
