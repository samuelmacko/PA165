package cz.muni.fi.pa165.blablacar.api.dto.city;

import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CityDTO {

    private Long id;
    private String name;
    private Set<Long> fromDrives = new HashSet<>();
    private Set<Long> toDrives = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Long> getFromDrives() {
        return fromDrives;
    }

    public Set<Long> getToDrives() {
        return toDrives;
    }

    public void setFromDrives(Set<Long> fromDrives) {
        this.fromDrives = fromDrives;
    }

    public void addToFromDrives(Long fromDrive) {
        this.fromDrives.add(fromDrive);
    }

    public void setToDrives(Set<Long> toDrives) {
        this.toDrives = toDrives;
    }

    public void addToToDrives(Long toDrive) {
        this.toDrives.add(toDrive);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof CityDTO))
            return false;
        CityDTO other = (CityDTO) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

}
