package cz.muni.fi.pa165.blablacar.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

public class DriveFormDTO {

    @NotNull
    private Long id;

    @NotNull
    @Min(value = 1, message = "capacity must be at leas one")
    private int capacity;

    @NotNull
    private Long fromCityId;

    @NotNull
    private Long toCityId;

    @NotNull
    @Min(value = 1, message = "drive must have a positive cost")
    private BigDecimal price;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="DD-MM-YYYY")
    private Date date;

    public Long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public Long getFromCityId() {
        return fromCityId;
    }

    public Long getToCityId() {
        return toCityId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setFromCityId(Long fromCityId) {
        this.fromCityId = fromCityId;
    }

    public void setToCityId(Long toCityId) {
        this.toCityId = toCityId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DriveFormDTO)) return false;
        DriveFormDTO that = (DriveFormDTO) o;
        return getCapacity() == that.getCapacity() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getFromCityId(), that.getFromCityId()) &&
                Objects.equals(getToCityId(), that.getToCityId()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCapacity(), getFromCityId(), getToCityId(), getPrice(), getDate());
    }

    @Override
    public String toString() {
        return "DriveFormDTO{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", fromCityId=" + fromCityId +
                ", toCityId=" + toCityId +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}