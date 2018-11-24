package cz.muni.fi.pa165.blablacar.api.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Drive DTO
 *
 * @author Samuel Macko
 */
public class DriveDTO {

    private Long id;
    private UserDTO driver;
    private Set<UserDTO> customers;
    private int capacity;
    private CityDTO fromCity;
    private CityDTO toCity;
    private Set<CommentDTO> comments;
    private BigDecimal price;
    private Date date;

    public Long getId() {
        return id;
    }

    public UserDTO getDriver() {
        return driver;
    }

    public Set<UserDTO> getCustomers() {
        return customers;
    }

    public int getCapacity() {
        return capacity;
    }

    public CityDTO getFromCity() {
        return fromCity;
    }

    public CityDTO getToCity() {
        return toCity;
    }

    public Set<CommentDTO> getComments() {
        return comments;
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

    public void setDriver(UserDTO driver) {
        this.driver = driver;
    }

    public void setCustomers(Set<UserDTO> customers) {
        this.customers = customers;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setFromCity(CityDTO fromCity) {
        this.fromCity = fromCity;
    }

    public void setToCity(CityDTO toCity) {
        this.toCity = toCity;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addCustomer(UserDTO u) {
        this.customers.add(u);
    }

    public void removeCustomer(UserDTO u) {
        this.customers.remove(u);
    }

    public void addComment(CommentDTO c) {
        this.comments.add(c);
    }

    public void removeComment(CommentDTO c) {
        this.comments.remove(c);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DriveDTO))
            return false;
        DriveDTO other = (DriveDTO) obj;
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
