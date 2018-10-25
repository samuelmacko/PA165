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
    public void addCity(City c) {
        em.persist(c);
    }

    @Override
    public void removeCity(City c) {
        em.remove(c);
    }

    @Override
    public void updateCity(City c) {
        em.merge(c);
    }

    @Override
    public City findCityById(Long id) {
        return em.find(City.class, id);
    }

    @Override
    public City findCityByName(String name) {
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
