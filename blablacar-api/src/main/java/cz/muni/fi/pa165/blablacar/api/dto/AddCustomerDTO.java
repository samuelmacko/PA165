package cz.muni.fi.pa165.blablacar.api.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AddCustomerDTO {

    @NotNull
    private Long driveId;

    @NotNull
    private Long customerId;

    public Long getDriveId() {
        return driveId;
    }

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddCustomerDTO)) return false;
        AddCustomerDTO that = (AddCustomerDTO) o;
        return Objects.equals(getDriveId(), that.getDriveId()) &&
                Objects.equals(getCustomerId(), that.getCustomerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDriveId(), getCustomerId());
    }

    @Override
    public String toString() {
        return "AddCustomerDTO{" +
                "driveId=" + driveId +
                ", customerId=" + customerId +
                '}';
    }
}
