package cz.muni.fi.pa165.blablacar.api.dto;

import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Create Drive DTO
 *
 * @author Samuel Macko
 */
public class DriveCreateDTO {

    @NotNull
    private Long id;

    @NotNull
    private User driver;

    @NotNull
    @Min(value = 1, message = "capacity must be at leas one")
    private int capacity;

    @NotNull
    private City fromCity;

    @NotNull
    private City toCity;

    @NotNull
    @Min(value = 1, message = "drive must have a positive cost")
    private BigDecimal price;

    @NotNull
    private Date date;

    public Long getId() {
        return id;
    }

    public User getDriver() {
        return driver;
    }

    public int getCapacity() {
        return capacity;
    }

    public City getFromCity() {
        return fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DriveCreateDTO))
            return false;
        DriveCreateDTO other = (DriveCreateDTO) obj;
        if (driver == null) {
            if (other.driver != null)
                return false;
        } else if (!driver.equals(other.getDriver()))
            return false;
        if (fromCity == null) {
            if (other.fromCity != null)
                return false;
        } else if (!fromCity.equals(other.getFromCity()))
            return false;
        if (toCity == null) {
            if (other.toCity != null)
                return false;
        } else if (!toCity.equals(other.getToCity()))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.getDate()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((driver == null) ? 0 : driver.hashCode());
        result = prime * result + ((fromCity == null) ? 0 : fromCity.hashCode());
        result = prime * result + ((toCity == null) ? 0 : toCity.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }


}
