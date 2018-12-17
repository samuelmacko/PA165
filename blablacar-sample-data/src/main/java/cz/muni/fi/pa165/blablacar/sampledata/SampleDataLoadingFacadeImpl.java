package cz.muni.fi.pa165.blablacar.sampledata;

import cz.muni.fi.pa165.blablacar.persistence.entity.City;
import cz.muni.fi.pa165.blablacar.persistence.entity.Comment;
import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
import cz.muni.fi.pa165.blablacar.persistence.entity.User;
import cz.muni.fi.pa165.blablacar.service.CityService;
import cz.muni.fi.pa165.blablacar.service.CommentService;
import cz.muni.fi.pa165.blablacar.service.DriveService;
import cz.muni.fi.pa165.blablacar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    private final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    private CommentService commentService;
    private UserService userService;
    private CityService cityService;
    private DriveService driveService;


    @Inject
    public SampleDataLoadingFacadeImpl(CommentService commentService, UserService userService, CityService cityService, DriveService driveService) {
        this.commentService = commentService;
        this.userService = userService;
        this.cityService = cityService;
        this.driveService = driveService;
    }


    @Override
    public void loadData() {
        //sample users
        User frodo = createFrodo();
        User sam = createSam();
        User aragorn = createAragorn();
        User gandalf = createGandalf();

        //sample cities
        City shire = createShire();
        City rohan = createRohan();
        City gondor = createGondor();
        City mordor = createMordor();

        //sampleDrives
        Drive driveToMordor = createDrive(shire, mordor, sam, frodo);
        Drive driveToRohan = createDrive(shire, rohan, aragorn, frodo, sam, gandalf);
        Drive driveToGondor = createDrive(rohan, gondor, gandalf, aragorn);
        driveToGondor.setDate(getXmasDate());

        driveService.addDrive(driveToGondor);
        driveService.addDrive(driveToRohan);
        driveService.addDrive(driveToMordor);

        //sample comments
        Comment frodoComment = createCommentToDriveFromUser(driveToMordor, frodo, "Worth it", new Date());
        Comment gandalfComment = createCommentToDriveFromUser(driveToRohan, gandalf, "Surprise happens", new Date());
        Comment arragorComment = createCommentToDriveFromUser(driveToGondor, aragorn, "Finally!!!", getXmasDate());

        commentService.createComment(frodoComment);
        commentService.createComment(gandalfComment);
        commentService.createComment(arragorComment);

        log.debug("Create sample data");
    }

    private User createFrodo() {
        User frodo = new User();
        frodo.setFirstName("Frodo");
        frodo.setLastName("Baggins");
        frodo.setLogin("hobbit1");
        userService.registerUser(frodo,"hobbit");
        return frodo;
    }

    private User createGandalf() {
        User gandalf = new User();
        gandalf.setFirstName("Gandalf");
        gandalf.setLastName("White");
        gandalf.setLogin("wizzard1");
        gandalf.setIsSuperUser(true);

        userService.registerUser(gandalf, "wizzard");
        return gandalf;
    }

    private User createAragorn() {
        User aragorn = new User();
        aragorn.setFirstName("Aragorn");
        aragorn.setLastName("Undomiel");
        aragorn.setLogin("walker");
        aragorn.setPassword("walker");

        userService.registerUser(aragorn, "walker");
        return aragorn;
    }

    private User createSam() {
        User sam = new User();
        sam.setFirstName("Samwise");
        sam.setLastName("Gamgee");
        sam.setLogin("hobbit2");
        sam.setLogin("hobbit");

        userService.registerUser(sam, "hobbit");
        return sam;
    }


    private City createGondor() {
        City gondor = new City();
        gondor.setName("Gondor");
        cityService.createCity(gondor);
        return gondor;
    }

    private City createMordor() {
        City mordor = new City();
        mordor.setName("Mordor");
        cityService.createCity(mordor);
        return mordor;
    }

    private City createRohan() {
        City rohan = new City();
        rohan.setName("Rohan");
        cityService.createCity(rohan);
        return rohan;
    }

    private City createShire() {
        City shire = new City();
        shire.setName("Shire");
        cityService.createCity(shire);
        return shire;
    }

    private Date getXmasDate() {
        Calendar xmas = Calendar.getInstance();
        xmas.set(2018, Calendar.DECEMBER, 24);
        return xmas.getTime();
    }

    private Drive createDrive(City cityFrom, City cityTo, User driver, User... customers) {
        Drive drive = new Drive();

        drive.setFromCity(cityFrom);
        drive.setToCity(cityTo);

        drive.setDriver(driver);
        driver.addToBeingDriver(drive);

        for (User customer : customers) {
            drive.addCustomer(customer);
            customer.addToBeingCustomer(drive);
        }

        drive.setCapacity(3);
        drive.setPrice(new BigDecimal("199.99"));
        drive.setDate(new Date());

        return drive;
    }

    private Comment createCommentToDriveFromUser(Drive toDrive, User fromUser, String content, Date createDate) {
        Comment comment = new Comment();
        comment.setAuthor(fromUser);
        comment.setDrive(toDrive);
        comment.setContent(content);
        comment.setCreatedDate(createDate);
        return comment;
    }
}
