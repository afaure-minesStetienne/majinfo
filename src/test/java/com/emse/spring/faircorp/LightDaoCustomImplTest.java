package com.emse.spring.faircorp;

import com.emse.spring.faircorp.model.LightDao;
import com.emse.spring.faircorp.model.Status;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class LightDaoCustomImplTest {

    @Autowired
    private LightDao lightDao;

    @Test
    public void shouldFindOnLights() {
        Assertions.assertThat(lightDao.findOnLights())
                .hasSize(1)
                .extracting("id", "status")
                .containsExactly(Tuple.tuple(-1L, Status.ON));
    }

    @Test
    public void shouldFindById(){
        Assertions.assertThat(lightDao.findOnRoomId(-10L))
                .hasSize(2);
    }
}