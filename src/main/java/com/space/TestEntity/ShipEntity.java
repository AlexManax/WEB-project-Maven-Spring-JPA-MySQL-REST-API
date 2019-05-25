package com.space.TestEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ship", schema = "cosmoport", catalog = "")
public class ShipEntity {
    private long id;
    private String name;
    private String planet;
    private String shipType;
    private Date prodDate;
    private Boolean isUsed;
    private Double speed;
    private Integer crewSize;
    private Double rating;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "planet", nullable = true, length = 50)
    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    @Basic
    @Column(name = "shipType", nullable = true, length = 9)
    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    @Basic
    @Column(name = "prodDate", nullable = true)
    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    @Basic
    @Column(name = "isUsed", nullable = true)
    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    @Basic
    @Column(name = "speed", nullable = true, precision = 0)
    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Basic
    @Column(name = "crewSize", nullable = true)
    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    @Basic
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

        ShipEntity that = (ShipEntity) o;

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
}
