package cz.muni.fi.pa165.blablacar.service;

import cz.muni.fi.pa165.blablacar.persistence.dao.DriveDao;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of DriveService interface
 *
 * @author Samuel Macko
 */
@Service
public class DriveServiceImpl implements DriveService {

    @Inject
    private DriveDao driveDao;
    

    @Override
    public Drive addDrive(Drive d) throws IllegalArgumentException {
        if (d == null) throw new IllegalArgumentException("d is null");
        driveDao.addDrive(d);
        return d;
    }

    @Override
    public void removeDrive(Drive d) throws IllegalArgumentException {
        if (d == null) throw new IllegalArgumentException("d is null");
        driveDao.removeDrive(d);
    }

    @Override
    public void updateDrive(Drive d) throws IllegalArgumentException {
        if (d == null) throw new IllegalArgumentException("d is null");
        driveDao.updateDrive(d);
    }

    @Override
    public void addCustomer(Drive d, User u) throws IllegalArgumentException {
        if (d == null) throw new IllegalArgumentException("d is null");
        if (u == null) throw new IllegalArgumentException("u is null");
        if (d.getCustomers().contains(u)) throw new IllegalArgumentException("user is already present");
        d.addCustomer(u);
    }

    @Override
    public void removeCustomer(Drive d, User u) throws IllegalArgumentException {
        if (d == null) throw new IllegalArgumentException("d is null");
        if (u == null) throw new IllegalArgumentException("u is null");
        if (!d.getCustomers().contains(u)) throw new IllegalArgumentException("user is already present");
        d.removeCustomer(u);
    }

    @Override
    public void addComment(Drive d, Comment c) throws IllegalArgumentException {
        if (d == null) throw new IllegalArgumentException("d is null");
        if (c == null) throw new IllegalArgumentException("c is null");
        if (d.getComments().contains(c)) throw new IllegalArgumentException("comment is already present");
        d.addComment(c);
    }

    @Override
    public void removeComment(Drive d, Comment c) throws IllegalArgumentException {
        if (d == null) throw new IllegalArgumentException("d is null");
        if (c == null) throw new IllegalArgumentException("c is null");
        if (!d.getComments().contains(c)) throw new IllegalArgumentException("comment is already present");
        d.removeComment(c);
    }

    @Override
    public Drive findDriveById(Long id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("id is null");
        return driveDao.findDriveById(id);
    }

    @Override
    public List<Drive> findAllDrives() {
        return driveDao.findAll();
    }

}
