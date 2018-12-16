package cz.muni.fi.pa165.blablacar.api.dto;


public class ChangeDriverDTO {

    private Long driveId;
    private UserDTO driver;

    public void setDriveId(Long driveId) {
        this.driveId = driveId;
    }

    public void setDriver(UserDTO driver) {
        this.driver = driver;
    }

    public Long getDriveId() {
        return driveId;
    }

    public UserDTO getDriver() {
        return driver;
    }

}
