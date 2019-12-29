
package com.emse.spring.faircorp.model;

        import org.springframework.data.jpa.repository.JpaRepository;

public interface LightDao extends LightDaoCustom, JpaRepository<Light,Long> {
}