//package com.space.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
////import java.util.Date;
//
//
////@Entity
////@Table(name = "ship", schema = "cosmoport")
//public class ShipForUI {
//    private long id;
//    private String name;
//    private String planet;
//    private ShipType shipType;
//    private Long prodDate;
//    private Boolean isUsed;
//    private Double speed;
//    private Integer crewSize;
//    private Double rating;
//
//    public ShipForUI(Ship ship) {
//        this.id = ship.getId();
//        this.name = ship.getName();
//        this.planet = ship.getPlanet();
//        this.shipType = ShipType.valueOf(ship.getShipType());
//        this.prodDate = ship.getProdDate().getTime();
//        this.isUsed = ship.getUsed();
//        this.speed = ship.getSpeed();
//        this.crewSize = ship.getCrewSize();
//        this.rating = generateRating() != rating  ? generateRating() : rating;
//    }
//
//
//    public Double generateRating (){
//        return rating = (80 * speed* ( isUsed ? 1 : 0.5))/
//                (3019 - Integer.parseInt(new SimpleDateFormat("yyyy").format(prodDate)) + 1 );
//    }
//
//    @Override
//    public String toString() {
//        return "ShipForUI{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", planet='" + planet + '\'' +
//                ", shipType=" + shipType +
//                ", prodDate=" + prodDate +
//                ", isUsed=" + isUsed +
//                ", speed=" + speed +
//                ", crewSize=" + crewSize +
//                ", rating=" + rating +
//                '}';
//    }
//}
