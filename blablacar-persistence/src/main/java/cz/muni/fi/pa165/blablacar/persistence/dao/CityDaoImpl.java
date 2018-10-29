package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CityDaoImpl implements CityDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addCity(City c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("City is null");
        }
        em.persist(c);
    }

    @Override
    public void removeCity(City c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("City is null");
        }
        em.remove(c);
    }

    @Override
    public void updateCity(City c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("City is null");
        }
        em.merge(c);
    }

    @Override
    public City findCityById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return em.find(City.class, id);
    }

    @Override
    public City findCityByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("City name is null");
        }
        try {
            return em
                    .createQuery("select c from City c where c.name = :name", City.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<City> findAll() {
        return em
                .createQuery("select c from City c", City.class)
                .getResultList();
    }
}
