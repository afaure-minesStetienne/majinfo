package com.emse.spring.faircorp.model;

        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

        import java.util.List;

public interface RoomDaoCustom {
    Room findOnName(String name);
}
