package cz.muni.fi.pa165.blablacar.service.facade;

import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
import cz.muni.fi.pa165.blablacar.api.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    @Override
    public Long createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO findUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO findUserByLogin(String login) {
        return null;
    }

    @Override
    public UserDTO findUserByFullName(String firstName, String lastName) {
        return null;
    }

    @Override
    public boolean editUser(UserDTO user) {
        return false;
    }

    @Override
    public boolean removeUser(UserDTO user) {
        return false;
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public boolean addDriveAsDriver(Long userId, Long driveId) {
        return false;
    }

    @Override
    public boolean addDriveAsPassenger(Long userId, Long driveId) {
        return false;
    }

    @Override
    public Collection<DriveDTO> getDriverDrives(Long id) {
        return null;
    }

    @Override
    public Collection<DriveDTO> getPassengerDrives(Long id) {
        return null;
    }
}
