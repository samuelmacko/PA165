package cz.muni.fi.pa165.blablacar.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * City Entity
 * @author Bruno Mizik
 */

@Entity
@Table(name="CITIES")
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true)
    private String name;

    @OneToMany(mappedBy = "fromCity")
    private Set<Drive> beginningOfDrives = new HashSet<>();

    @OneToMany(mappedBy = "toCity")
    private Set<Drive> endOfDrives = new HashSet<>();

    public Set<Drive> getBeginningOfDrives() {
        return beginningOfDrives;
    }

    public Set<Drive> getEndOfDrives() {
        return endOfDrives;
    }

    public void setBeginningOfDrives(Set<Drive> beginningOfDrives) {
        this.beginningOfDrives = beginningOfDrives;
    }

    public void setEndOfDrives(Set<Drive> endOfDrives) {
        this.endOfDrives = endOfDrives;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof City))
            return false;
        City other = (City) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;

        return true;
    }
}
