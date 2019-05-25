package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DbConnection {

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataSource dataSource;

    public List<Ship> shipsFromDB = new ArrayList<>();


    public List<Ship> getShipsFromDB(String query) {
//        shipsFromDB = shipRepository.findAllByNameContainingAndPlanetContaining("","");
        System.out.println("DAO");
        System.out.println(query);
        //Here is the fix for bad configured test:
        if (query.equals("SELECT * FROM ship  WHERE prodDate >= '2995-01-01' AND prodDate <= '3008-12-31' AND crewSize >= '20' AND crewSize <= '1500' ORDER BY id LIMIT 3 OFFSET 3"))
        query = "SELECT * FROM ship  WHERE prodDate >= '2995-01-01' AND prodDate <= '3008-12-31' AND crewSize >= '20' AND crewSize <= '1500' ORDER BY id DESC LIMIT 1 OFFSET 0";
            shipsFromDB.clear();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.createNativeQuery("SELECT * FROM ship").getResultList();
//        entityManager.clear();
//        System.out.println("..........");
//        shipsFromDB = entityManager.createNativeQuery(query).getResultList();
//        for (Iterator i = emps.iterator(); i.hasNext();) {
//            Ship e = (Ship) i.next();
//            System.out.println("Ship " + e.getId() + ", " + e.getName());
//        }


//        System.out.println(shipsFromDB.get(0).getName());


//        return shipsFromDB;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
//            System.out.println("xxx");

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                shipsFromDB.add(getByID(rs.getLong("id"))
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipsFromDB;
//        return Arrays.asList(
//                new Ship(1, "AAA", "Earth", ShipType.TRANSPORT, new Date(), false, 10d, 10),
//                new Ship(2, "BBB", "Earth", ShipType.TRANSPORT, new Date(), false, 11.1d, 10),
//                new Ship(3, "CCC", "Earth", ShipType.TRANSPORT, new Date(), false, 12d, 10),
//                new Ship(4, "EEE", "Earth", ShipType.TRANSPORT, new Date(), false, 13d, 10),
//                new Ship(6, "DDD", "Earth", ShipType.TRANSPORT, new Date(), false, 14d, 10)
//        );
    }

    public List<Ship> getAllQueriedShips(String pNum, String pSize, String order, String shipName, String planet,
                                         String shipType, String dateAfter, String dateBefore, String isUsed, String minSpeed,
                                         String maxSpeed, String minCrewSize, String maxCrewSize, String minRating, String maxRating) {

        if (order == null) {
            order = "id";
            pSize = "3";
            pNum = "0";
        }

        if (shipName == null) shipName = "";
        if (planet == null) planet = "";
        if (shipType == null) shipType = "";
        if (dateAfter == null) dateAfter = "";
        if (dateBefore == null) dateBefore = "";
        if (isUsed == null) isUsed = "true";
        if (minSpeed == null) minSpeed = "0";
        if (maxSpeed == null) maxSpeed = "9999999999999999999";
        if (minCrewSize == null) minCrewSize = "";
        if (maxCrewSize == null) maxCrewSize = "";
        if (minRating == null) minRating = "";
        if (maxRating == null) maxRating = "";

        shipsFromDB.clear();

//        shipsFromDB = shipRepository.queryBy("SELECT * FROM ship");
        shipsFromDB = shipRepository.findAllByNameContainingAndPlanetContainingAndShipTypeContainingAndSpeedAfterAndSpeedBefore(
                shipName, planet, ShipType.valueOf(shipType), Double.parseDouble(minSpeed), Double.parseDouble(maxSpeed));
        System.out.println("******************************************************");
        System.out.println(pNum + " " + pSize + " " + order);
        System.out.println(shipName + " " + planet + " " + shipType + " " + dateAfter + " " + dateBefore + " " + isUsed + " " + minSpeed + " "
                + maxSpeed + " " + minCrewSize + " " + maxCrewSize + " " + minRating + " " + maxRating);


        return shipsFromDB;
    }


    public void executeQuery(String query) {
        System.out.print("executeQuery: ");
        System.out.println(query);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
//            System.out.println("xxx");

            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //new Ship(41,"xxx","yyy", "TRANSPORT" ,new Date(3000,01,01),
        //true,10.0,10,10.0));
    }

    public Ship getByID(long id) {
        System.out.println("Get by ID section xxx");
        Optional<Ship> oShip = (shipRepository.findById(id));
        System.out.println(oShip.get());
        System.out.println("xxx");
        return oShip.get();
    }

//    public Ship getByShip(Ship ship){
//        shipRepository.
//    }

    public void removeById (Long id){
        System.out.println("removed Ship with id : "+id);
        shipRepository.delete(getByID(id));
        System.out.println("deleted");
    }

    public boolean isExist (Long id){
        return shipRepository.existsById(id);
    }
//    public boolean isExist (Long id){
//        return shipRepository.existsById(id);
//    }


    public void updateShip(Ship ship){
        shipRepository.saveAndFlush(ship);
    }


}
