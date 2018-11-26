package cz.muni.fi.pa165.blablacar.api.dto.city;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class CityCreateDTO {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityCreateDTO that = (CityCreateDTO) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CityCreateDTO{" +
                "name='" + name + '\'' +
                '}';
    }

}
