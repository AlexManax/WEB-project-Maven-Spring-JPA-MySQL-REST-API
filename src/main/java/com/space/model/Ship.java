package com.space.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.text.SimpleDateFormat;


@Entity
@Table(name = "ship", schema = "cosmoport")
public class Ship {
    private long id;
    private String name;
    private String planet;
    private ShipType shipType;
    private Date prodDate;
    private Boolean isUsed;
    private Double speed;
    private Integer crewSize;
    private Double rating;

    public Ship(long id, String name, String planet, ShipType shipType, Date prodDate, Boolean isUsed, Double speed, Integer crewSize, Double rating) {
        this.id = id;
        this.name = name;
        this.planet = planet;
        this.shipType = (shipType);
        this.prodDate = prodDate;
        System.out.println(prodDate.getTime());
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
        this.rating = generateRating() != rating  ? generateRating() : rating;

    }

    public Ship() {
    }

    public Double generateRating () {
        return new BigDecimal((80 * speed * (isUsed ? 0.5 : 1)) / (3019 - Integer.parseInt(new SimpleDateFormat("yyyy").format(prodDate)) + 1)).setScale(2, RoundingMode.DOWN).doubleValue();

//        return rating = (80 * speed* ( isUsed ? 1 : 0.5))/
//                (3019 - Integer.parseInt(new SimpleDateFormat("yyyy").format(prodDate)) + 1 );
    }

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "planet", nullable = true, length = 50)
    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }


    @Column(name = "shipType", nullable = true, length = 9)
    public String getShipType() {
        try {
            return shipType.name();
        }
        catch (Exception e){
            return null;
        }
    }


    public void setShipType(String shipType) {
        this.shipType = ShipType.valueOf(shipType);
    }


    @Column(name = "prodDate", nullable = true)
    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }


    @Column(name = "isUsed", nullable = true)
    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }


    @Column(name = "speed", nullable = true, precision = 0)
    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }


    @Column(name = "crewSize", nullable = true)
    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }


    @Column(name = "rating", nullable = true, precision = 0)
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship that = (Ship) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (planet != null ? !planet.equals(that.planet) : that.planet != null) return false;
        if (shipType != null ? !shipType.equals(that.shipType) : that.shipType != null) return false;
        if (prodDate != null ? !prodDate.equals(that.prodDate) : that.prodDate != null) return false;
        if (isUsed != null ? !isUsed.equals(that.isUsed) : that.isUsed != null) return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;
        if (crewSize != null ? !crewSize.equals(that.crewSize) : that.crewSize != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (planet != null ? planet.hashCode() : 0);
        result = 31 * result + (shipType != null ? shipType.hashCode() : 0);
        result = 31 * result + (prodDate != null ? prodDate.hashCode() : 0);
        result = 31 * result + (isUsed != null ? isUsed.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (crewSize != null ? crewSize.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", prodDate=" + prodDate +
                ", isUsed=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                ", rating=" + rating +
                '}';
    }
}
