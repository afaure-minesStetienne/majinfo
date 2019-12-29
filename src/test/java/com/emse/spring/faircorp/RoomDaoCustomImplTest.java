package com.emse.spring.faircorp;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.RoomDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoomDaoCustomImplTest{

    @Autowired
    private RoomDao roomDao;

    @Test
    public void shouldFindByNames(){
        Assertions.assertThat(roomDao.findOnName("Room1"))
                .hasSize(1)
                .extracting("id")
                .containsExactly(-10L);
    }
}