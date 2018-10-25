package cz.muni.fi.pa165.blablacar.persistence.dao;

import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of DriveDao interface
 * @author Samuel Macko
 */
@Repository
@Transactional
public class DriveDaoImpl implements DriveDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addDrive(Drive d) throws IllegalArgumentException {
        if (d == null) {
            throw new IllegalArgumentException("drive is null");
        }
        em.persist(d);
    }

    @Override
    public void removeDrive(Drive d) throws IllegalArgumentException {
        if (d == null) {
            throw new IllegalArgumentException("drive is null");
        }
        em.remove(d);
    }

    @Override
    public void updateDrive(Drive d) throws IllegalArgumentException {
        if (d == null) {
            throw new IllegalArgumentException("drive is null");
        }
        em.merge(d);
    }

    @Override
    public Drive findDriveById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return em.find(Drive.class, id);
    }

    @Override
    public List<Drive> findAll() {
        return em.createQuery("select d from Drive d", Drive.class).getResultList();
    }
}
