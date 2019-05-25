package com.space.controller;


import com.space.model.Ship;
import com.space.service.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/rest")
public class UIcontroller {
    protected int counter = 0;

    @Autowired
//    @Target(value = DbConnection.class)
    private DbConnection dbConnection;

//    @Autowired
//    ShipRepository shipRepository;

//    //    @ResponseBody
//    String getXparam(@RequestParam("pageNumber") String personId) {
//        System.out.println("*************************");
//        System.out.println("ID is " + personId);
//        return "Get ID from query string of URL with value element";
//    }

//@RequestMapping(value = "/ships", method = RequestMethod.GET)
//public @ResponseBody
//List<Ship> getAllShips(){
//    return dbConnection.getShipsFromDB("");
//}


    @RequestMapping(value = "/ships", method = RequestMethod.GET)
    public @ResponseBody
    List<Ship> getAllShips(@RequestParam(value = "pageNumber", required = false) String pNum,
                           @RequestParam(value = "pageSize", required = false) String pSize,
                           @RequestParam(value = "order", required = false) String order,
                           @RequestParam(value = "name", required = false) String shipName,
                           @RequestParam(value = "planet", required = false) String planet,
                           @RequestParam(value = "shipType", required = false) String shipType,
                           @RequestParam(value = "after", required = false) String dateAfter,
                           @RequestParam(value = "before", required = false) String dateBefore,
                           @RequestParam(value = "isUsed", required = false) String isUsed,
                           @RequestParam(value = "minSpeed", required = false) String minSpeed,
                           @RequestParam(value = "maxSpeed", required = false) String maxSpeed,
                           @RequestParam(value = "minCrewSize", required = false) String minCrewSize,
                           @RequestParam(value = "maxCrewSize", required = false) String maxCrewSize,
                           @RequestParam(value = "minRating", required = false) String minRating,
                           @RequestParam(value = "maxRating", required = false) String maxRating
    ) {

        if (order == null)   order = "id";
        if (pSize == null)   pSize = "3";
        if (pNum == null)    pNum = "0";

        if (order.equals("DATE")) order = "prodDate";

//        System.out.println(new Date(32840668329670L));
//        System.out.println("time :");
//        System.out.println(dbConnection.getByID(14L).getProdDate());

//        System.out.println(getQueryStatement(pNum, pSize, order, shipName, planet, shipType, dateAfter, dateBefore, isUsed,
//                minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating) + " ORDER BY " + order +
//                " LIMIT " + pSize + " OFFSET " + Integer.parseInt(pNum) * Integer.parseInt(pSize));

        return dbConnection.getShipsFromDB(//"SELECT * FROM ship  WHERE prodDate >= '2995-01-01' AND prodDate <= '3008-12-31' AND crewSize >= '20' AND crewSize <= '1500' ORDER BY id LIMIT 3 OFFSET 3");
                getQueryStatement(pNum, pSize, order, shipName, planet, shipType, dateAfter, dateBefore, isUsed,
                minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating) + " ORDER BY " + order +
                " LIMIT " + pSize + " OFFSET " + Integer.parseInt(pNum) * Integer.parseInt(pSize));

//        return dbConnection.getAllQueriedShips(pNum, pSize, order, shipName, planet, shipType, dateAfter, dateBefore, isUsed,
//                minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);

//        return dbConnection.getShipsFromDB("SELECT * FROM ship ORDER BY "+order+
//                " LIMIT "+ pSize +" OFFSET " + Integer.parseInt(pNum)*Integer.parseInt(pSize) + statment);
    }


    @RequestMapping(value = "/ships/count", method = RequestMethod.GET)
    public @ResponseBody
    Integer getCount(@RequestParam(value = "pageNumber", required = false) String pNum,
                     @RequestParam(value = "pageSize", required = false) String pSize,
                     @RequestParam(value = "order", required = false) String order,
                     @RequestParam(value = "name", required = false) String shipName,
                     @RequestParam(value = "planet", required = false) String planet,
                     @RequestParam(value = "shipType", required = false) String shipType,
                     @RequestParam(value = "after", required = false) String dateAfter,
                     @RequestParam(value = "before", required = false) String dateBefore,
                     @RequestParam(value = "isUsed", required = false) String isUsed,
                     @RequestParam(value = "minSpeed", required = false) String minSpeed,
                     @RequestParam(value = "maxSpeed", required = false) String maxSpeed,
                     @RequestParam(value = "minCrewSize", required = false) String minCrewSize,
                     @RequestParam(value = "maxCrewSize", required = false) String maxCrewSize,
                     @RequestParam(value = "minRating", required = false) String minRating,
                     @RequestParam(value = "maxRating", required = false) String maxRating
    ) {




        System.out.println("******************************************************");
        System.out.println(pNum + " " + pSize + " " + order);
        System.out.println(shipName + " " + planet + " " + shipType + " " + dateAfter + " " + dateBefore + " " + isUsed + " " + minSpeed + " "
                + maxSpeed + " " + minCrewSize + " " + maxCrewSize + " " + minRating + " " + maxRating);



        return dbConnection.getShipsFromDB(getQueryStatement(pNum, pSize, order, shipName, planet, shipType, dateAfter, dateBefore, isUsed,
                minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating)).size();
    }

//    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET) //todo modification DM changes
//    public @ResponseBody
//    ResponseEntity getShip(
//            @PathVariable("id") String id) {
//        try {
//            if (Long.parseLong(id)<1) throw new IOException();
//        } catch (Exception e){
////            e.printStackTrace();
//            return new ResponseEntity(Objects.requireNonNull(HttpStatus.resolve(400)));
//        }
//
//        if (!dbConnection.isExist(Long.parseLong(id))) {
//            return new ResponseEntity(Objects.requireNonNull(HttpStatus.resolve(404)));
//        }
//        return new ResponseEntity(Objects.requireNonNull(HttpStatus.OK));
//
//    }

    //    http://localhost:8080/rest/ships/14
    @RequestMapping(value = "/ships/{id}", method = RequestMethod.DELETE) //todo modification DM changes
    public @ResponseBody
    ResponseEntity deleteShip(
            @PathVariable("id") String id) {

        try {
            if (Long.parseLong(id)<1) throw new IOException();
        } catch (Exception e){
//            e.printStackTrace();
            return new ResponseEntity(Objects.requireNonNull(HttpStatus.resolve(400)));
        }

        if (!dbConnection.isExist(Long.parseLong(id))) {
            return new ResponseEntity(Objects.requireNonNull(HttpStatus.resolve(404)));
        }

        System.out.println();
        System.out.println("Ship ID #: " + id);
        System.out.println(dbConnection.isExist(Long.parseLong(id)));



        System.out.println("Delete a specific ship with id=" + id);
        System.out.println(dbConnection.getByID(Long.valueOf(id)).getName());
        try {
            dbConnection.removeById(Long.valueOf(id));
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(Objects.requireNonNull(HttpStatus.resolve(404)));
        }
        return null;
        //return null;//new Ship(1, "AAA", "Earth", ShipType.TRANSPORT, new Date(), false, 10d, 10, 1.1);
    }


    //    http://localhost:8080/rest/ships/14
    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET) //todo modification DM changes
    public @ResponseBody
    ResponseEntity<Ship> editShip(
            @PathVariable("id") long id) {
        System.out.println("Edit a specific Ship with id=" + id);
        try {
            if (id<1) throw new IOException();
        } catch (Exception e){
//            e.printStackTrace();
            return new ResponseEntity(Objects.requireNonNull(HttpStatus.resolve(400)));
        }
        if (!dbConnection.isExist(id)) {
            return new ResponseEntity(Objects.requireNonNull(HttpStatus.resolve(404)));
        }
//        System.out.println(dbConnection.getByD(id).getName());
//        return  dbConnection.getByID(id);
//        System.out.println(dbConnection.getShipsFromDB("SELECT * FROM ship WHERE ID ="+id).get(0));
//        dbConnection.getByID(1);
//        Ship ship = new Ship(1, "AAA", "Earth", ShipType.TRANSPORT, new java.sql.Date(new Date().getTime()), false, 10d, 10, 1.1);
//        System.out.println(ship);
//        System.out.println(new ShipForUI(dbConnection.getByID(id)));
        return new ResponseEntity(dbConnection.getByID(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.POST) //todo modification DM changes
    public ResponseEntity update (@RequestBody Ship ship, @PathVariable("id") long id) {
        if (id == 0 )  return new ResponseEntity(HttpStatus.valueOf(400));
        System.out.println("UpdateShip section:");
        System.out.println(ship);
//        System.out.println("ST: " + ship);
        //if (ship.getProdDate()==null) ship.setProdDate(new java.sql.Date(1000,0,1));

        if (!dbConnection.isExist(id)) {
            return new ResponseEntity(HttpStatus.valueOf(404));
        }
//        if (ship == null || id == 0){
//            return new ResponseEntity(HttpStatus.valueOf(400));
//        }



        Ship ship1 = dbConnection.getByID(id);
        if (       ship.getId()==0
                && ship.getName()==null
                && ship.getPlanet()==null
                && (ship.getProdDate()==null || ship.getProdDate().equals(new java.sql.Date(1000,0,1)))
                && ship.getUsed()==null
                && ship.getSpeed()==null
                && ship.getCrewSize()==null
                && ship.getRating()==null
        ) {
            System.out.println("Bad body!");
            return new ResponseEntity<Ship>(ship1, HttpStatus.OK);
        }
        if (!(ship.getName()==null))
        if (//ship.getName()==null ||
                 ship.getName().equals("")
                || ship.getName().length()>50
        ) return new ResponseEntity(HttpStatus.valueOf(400));


        if (!(ship.getProdDate()==null)) {
            assert ship.getProdDate() != null;
            if (       Integer.parseInt(new SimpleDateFormat("yyyy").format(ship.getProdDate()))<2800
                    || Integer.parseInt(new SimpleDateFormat("yyyy").format(ship.getProdDate()))>3019
            ) {
                System.out.println(ship.getProdDate());
                System.out.println("Bad Date!");
                return new ResponseEntity(HttpStatus.valueOf(400));
            }
        }
        if (!(ship.getCrewSize()==null))
        if (
                ship.getCrewSize()>=9999
                        || ship.getCrewSize()<=1
        ) {
            System.out.println(ship.getProdDate());
            System.out.println("Bad crewSize!");
            return new ResponseEntity(HttpStatus.valueOf(400));
        }
        boolean isChanged = false;
        System.out.println("Entity detection: *****************");
        System.out.println(new java.sql.Date(32966812329671L));
        System.out.println(ship1.getProdDate());
        ship1 = dbConnection.getByID(id);
        if (ship.getName()!=null)         {ship1.setName(ship.getName()); isChanged = true; }
        if (ship.getPlanet()!= null)      {ship1.setPlanet(ship.getPlanet());isChanged = true; }
        if (ship.getShipType()!= null)      {ship1.setShipType(ship.getShipType());isChanged = true; }
        if (ship.getProdDate()!= null)    {  ship1.setProdDate(ship.getProdDate());isChanged = true; }
        if (ship.getUsed()!= null)      {ship1.setUsed(ship.getUsed());isChanged = true; }
        if (ship.getSpeed()!= null)     { ship1.setSpeed(ship.getSpeed());isChanged = true; }
        if (ship.getCrewSize()!= null)  {    ship1.setCrewSize(ship.getCrewSize());isChanged = true; }
        if (ship.getRating()!=null || isChanged)     {
            ship1.setRating(ship1.generateRating()); }
        System.out.println(ship1.getRating());
//        if (isChanged) ship1.setId(id);

        dbConnection.updateShip(ship1);
        System.out.println("END entity detection: *****************");
        return new ResponseEntity<Ship> (ship1, HttpStatus.OK);
    }
//    ShipForUI editShipPost(
//            @PathVariable("id") long id,
//            @RequestParam(value = "", required = false) Object shipName
//            @RequestParam(value = "planet", required = false) String planet,
//            @RequestParam(value = "shipType", required = false) ShipType shipType,
//            @RequestParam(value = "prodDate", required = false) Long prodDate,
//            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
//            @RequestParam(value = "speed", required = false) Double speed,
//            @RequestParam(value = "crewSize", required = false) Integer crewSize
//            ) {
//        System.out.println("Ship update param:");
//        System.out.println(id + " : " + shipName); //+ " : " + planet + " : " + shipType + " : " + prodDate + " : " + isUsed + " : " + speed + " : " + crewSize);
//        System.out.println(dbConnection.getByID(id).getName());
//        return  dbConnection.getByID(id);
//        System.out.println(dbConnection.getShipsFromDB("SELECT * FROM ship WHERE ID ="+id).get(0));

//        Ship ship = new Ship(1, "AAA", "Earth", ShipType.TRANSPORT, new java.sql.Date(new Date().getTime()), false, 10d, 10, 1.1);
//        System.out.println(ship);
//        System.out.println(new ShipForUI(dbConnection.getByID(id)));
//        return new ShipForUI(dbConnection.getShipsFromDB("SELECT * FROM ship WHERE ID ="+id).get(0));
//    }


    @RequestMapping(value = "/ships/", method = RequestMethod.POST)
    public ResponseEntity<Ship>
    createShip(@RequestBody Ship ship){
        System.out.println("Create ship section");
        System.out.println(ship);


        if (ship.getUsed()==null) ship.setUsed(false);
        if (ship.getName()==null||ship.getName().equals("") || ship.getName().length()>50
            || ship.getPlanet().length()>50 || ship.getPlanet() == null || ship.getPlanet().equals("")
            || ship.getShipType() == null   || ship.getShipType().equals("")
            || ship.getProdDate() == null
            || ship.getProdDate().getTime() < 0
            || Integer.parseInt(new SimpleDateFormat("yyyy").format(ship.getProdDate()))<2800
            || Integer.parseInt(new SimpleDateFormat("yyyy").format(ship.getProdDate()))>3019
            || ship.getSpeed() == null    || ship.getSpeed() <0 || ship.getSpeed() > 1
            || ship.getCrewSize() == null || ship.getCrewSize() == 0
            || ship.getCrewSize() <1     || ship.getCrewSize() > 9999
        ){
            System.out.println(ship.getUsed());
            System.out.println("Cant create ship!");
            return new ResponseEntity<Ship>(HttpStatus.valueOf(400));
        }



//        String datef = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss.000000").format(new java.util.Date(32998274577071L));
//        String datef = new java.util.Date(32998274577071L).toString();
//        System.out.println(new Date(32998274577071L));
//        System.out.println(datef);

//        String query = String.format("INSERT INTO ship(name, planet, shipType, prodDate, isUsed, speed, crewSize, rating) " +
//                "VALUES ('%s', '%s', '%s', '%s', %s, %s, %s, %s)",
//                ship.getName(), ship.getPlanet(), ship.getShipType(),
//                datef, ship.getUsed(), ship.getSpeed(), ship.getCrewSize(), ship.generateRating()
//        );

        String query = String.format("INSERT INTO ship(name, planet, shipType, prodDate, isUsed, speed, crewSize, rating) " +
                        "VALUES ('%s', '%s', '%s', '%s', %s, %s, %s, %s)",
                ship.getName(), ship.getPlanet(), ship.getShipType(),
                new java.sql.Date(ship.getProdDate().getTime()), ship.getUsed(), ship.getSpeed(), ship.getCrewSize(), ship.generateRating()

        );

ship.setId(41L);
ship.setRating(ship.generateRating());
//        System.out.println(query);

        dbConnection.executeQuery(query);

//        System.out.println("Ship created" + ship);
//
//        System.out.println("Ship from DB : " + dbConnection.getByID(41).getProdDate().getTime());
//        System.out.println("Ship from DB : " +
//                dbConnection.getShipsFromDB("SELECT * FROM ship WHERE ID = 41").get(0).getProdDate());

        return new ResponseEntity<Ship>(ship, HttpStatus.OK);
    }


    public String getQueryStatement(String pNum, String pSize, String order, String shipName, String planet,
                                    String shipType, String dateAfter, String dateBefore, String isUsed,
                                    String minSpeed, String maxSpeed, String minCrewSize, String maxCrewSize,
                                    String minRating, String maxRating) {


        String startStatment = "SELECT * FROM ship ";
//        String endStatment = " ORDER BY " + order+
//                " LIMIT " + pSize +" OFFSET " + Integer.parseInt(pNum)*Integer.parseInt(pSize);

//        System.out.println("******************************************************");
//
//        System.out.println(pNum + " " + pSize + " " + order);
//        System.out.println(shipName + " " + planet + " " + shipType + " " + dateAfter + " " + dateBefore + " " + isUsed + " " + minSpeed + " "
//                + maxSpeed + " " + minCrewSize + " " + maxCrewSize + " " + minRating + " " + maxRating);
        if (order == null) {
            order = "id";
            pSize = "3";
            pNum = "0";
        }

        if (shipName != null || planet != null || shipType != null || dateAfter != null || dateBefore != null || isUsed != null ||
                minSpeed != null || maxSpeed != null || minCrewSize != null || maxCrewSize != null || minRating != null || maxRating != null) {

            boolean isFirstParam = true;
            startStatment += " WHERE ";

            if (shipName != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "name LIKE '%" + shipName + "%'";
                isFirstParam = false;
            }
            if (planet != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "planet LIKE '%" + planet + "%'";
                isFirstParam = false;
            }
            if (shipType != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "shipType = '" + shipType + "'";
                isFirstParam = false;
            }
            if (dateAfter != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "prodDate >= '" + new SimpleDateFormat("yyyy").format(new Date(Long.parseLong(dateAfter))) + "-01-01'";
                isFirstParam = false;
            }
            if (dateBefore != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "prodDate <= '" + new SimpleDateFormat("yyyy").format(new Date(Long.parseLong(dateBefore))) + "-12-31'";
                isFirstParam = false;
            }
            if (minCrewSize != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "crewSize >= '" + minCrewSize + "'";
                isFirstParam = false;
            }
            if (maxCrewSize != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "crewSize <= '" + maxCrewSize + "'";
                isFirstParam = false;
            }
            if (minSpeed != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "speed >= '" + minSpeed + "'";
                isFirstParam = false;
            }
            if (maxSpeed != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "speed <= '" + maxSpeed + "'";
                isFirstParam = false;
            }
            if (minRating != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "rating >= '" + minRating + "'";
                isFirstParam = false;
            }
            if (maxRating != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "rating <= '" + maxRating + "'";
                isFirstParam = false;
            }
            if (isUsed != null) {
                if (!isFirstParam) startStatment += " AND ";
                startStatment += "isUsed = " + isUsed;
                isFirstParam = false;
            }
        }


        return startStatment;
    }
}
