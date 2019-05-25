package com.space.repository;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
//    @Query("SELECT s FROM Ship s WHERE s.name = :name")
//    List<Ship> queryAllBy(String query);

    Optional<Ship> findById (Long id);
    List<Ship> findAllByNameContainingAndPlanetContaining(String name, String planet);
    List<Ship> findAllByNameContainingAndPlanetContainingAndShipTypeContaining(String name, String planet, ShipType shipType);
    List<Ship> findAllByNameContainingAndPlanetContainingAndShipTypeContainingAndSpeedAfterAndSpeedBefore(String name, String planet, ShipType shipType, Double speed, Double speed2);



//    Ship getById(Long aLong);

//    Ship findByNameAndSpeedAndShipType(String name, double speed, String shipType );
//    Ship findByNameLikeAndSpeed(String name, double speed, String shipType );
//    void delete(long id);
}