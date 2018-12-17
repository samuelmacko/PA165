package cz.muni.fi.pa165.blablacar.api.dto;

        import javax.validation.constraints.Min;
        import javax.validation.constraints.NotNull;
        import java.math.BigDecimal;
        import java.util.Date;

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

}