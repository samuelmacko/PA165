//package cz.muni.fi.pa165.blablacar.rest;
//
//import cz.muni.fi.pa165.blablacar.RootWebContext;
//import cz.muni.fi.pa165.blablacar.api.dto.DriveDTO;
//import cz.muni.fi.pa165.blablacar.api.dto.UserDTO;
//import cz.muni.fi.pa165.blablacar.api.dto.city.CityDTO;
//import cz.muni.fi.pa165.blablacar.api.facade.DriveFacade;
//import cz.muni.fi.pa165.blablacar.persistence.entity.City;
//import cz.muni.fi.pa165.blablacar.persistence.entity.Drive;
//import cz.muni.fi.pa165.blablacar.rest.controllers.DrivesController;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
//import javax.inject.Inject;
//import javax.jws.soap.SOAPBinding;
//import java.math.BigDecimal;
//import java.util.*;
//
//@WebAppConfiguration
//@ContextConfiguration(classes = {RootWebContext.class})
//public class DrivesControllerTest extends AbstractTestNGSpringContextTests {
//
//    @Mock
//    private DriveFacade driveFacade;
//
////    @Mock
////    private UserFacade userFacade;
//
//    @Inject
//    @InjectMocks
//    private DrivesController drivesController;
//
//    private MockMvc mockMvc;
//
//    @BeforeClass
//    void setup() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = standaloneSetup(drivesController)
//                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
//    }
//
//    @Test
//    void getAllDrivesTest() throws Exception {
//        doReturn(Collections.unmodifiableList(this.createDrives())).when(driveFacade).findAllDrives();
//
//        mockMvc.perform(get("/drives"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$.[?(@.id==34)].capacity").value(4))
//                .andExpect(jsonPath("$.[?(@.id==34)].price").value(new BigDecimal("100.00")));
//    }
//
//    @Test
//    void getDriveTest() throws Exception {
//        List<DriveDTO> drives = this.createDrives();
//        doReturn(drives.get(0)).when(driveFacade).findDriveById(drives.get(0).getId());
//        doReturn(drives.get(1)).when(driveFacade).findDriveById(drives.get(1).getId());
//
//        mockMvc.perform(get("/drives/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$.id").value("34L"))
//                .andExpect(jsonPath("$.capacity").value(4))
//                .andExpect(jsonPath("$.price").value(new BigDecimal("100.00")));
//
////        mockMvc.perform(get("/drives/2"))
////                .andExpect(status().isOk())
////                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
////                .andExpect(jsonPath("$.id").value("43L"))
////                .andExpect(jsonPath("$.capacity").value(2))
////                .andExpect(jsonPath("$.price").value(new BigDecimal("200.00")));
//    }
//
//    @Test
//    void deleteDriveTest() throws Exception {
//        doNothing().when(driveFacade).removeDrive(anyLong());
//
//        mockMvc.perform(delete("/drives/1"))
//                .andExpect(status().isOk());
//
//        verify(driveFacade).removeDrive(1L);
//    }
//
//    @Test
//    void createDriveTest() throws Exception {
//        UserDTO driver = new UserDTO();
//        driver.setId(1L);
//
//        UserDTO customer = new UserDTO();
//        customer.setId(2L);
//
//        DriveDTO drive = new DriveDTO();
//        drive.setDriver(driver);
//        drive.addCustomer(customer);
//        drive.setId(12L);
//        drive.setCapacity(1);
//        drive.setPrice(new BigDecimal("199.99"));
//        Calendar date = Calendar.getInstance();
//        date.set(2018, Calendar.DECEMBER, 16, 17, 36, 28);
//        drive.setDate(date.getTime());
//
//        doReturn(drive.getId()).when(driveFacade).createDrive(any());
//        doReturn(drive).when(driveFacade).findDriveById(drive.getId());
//
//        mockMvc.perform(post("/drives/create")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content("{" +
//                        "\"id\": 1," +
//                        "\"fromCityId\": 1," +
//                        "\"toCityId\": 2," +
//                        "\"price\": 199.99," +
//                        "\"capacity\": 1" +
//                        "}"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("id").value(12L))
//                .andExpect(jsonPath("driver.id").value(1L))
//                .andExpect(jsonPath("customers[0].id").value(2L))
//                .andExpect(jsonPath("price").value(199.99))
//                .andExpect(jsonPath("capacity").value(1));
//    }
//
//    private List<DriveDTO> createDrives() {
//        CityDTO shire = new CityDTO();
//        shire.setId(3L);
//        shire.setName("Shire");
//
//        CityDTO mordor = new CityDTO();
//        mordor.setId(4L);
//        mordor.setName("Mordor");
//
//        UserDTO driver = this.createUser();
//
//        DriveDTO shireToMordor = new DriveDTO();
//        shireToMordor.setDriver(driver);
//        shireToMordor.setId(34L);
//        shireToMordor.setCapacity(4);
//        shireToMordor.setPrice(new BigDecimal("100.00"));
//        shireToMordor.setFromCity(shire);
//        shire.addToFromDrives(shireToMordor.getId());
//        shireToMordor.setToCity(mordor);
//        mordor.addToToDrives(shireToMordor.getId());
//        shireToMordor.setDate(new Date());
//
//        DriveDTO mordorToShire = new DriveDTO();
//        mordorToShire.setDriver(driver);
//        mordorToShire.setId(43L);
//        mordorToShire.setCapacity(2);
//        mordorToShire.setPrice(new BigDecimal("200.00"));
//        mordorToShire.setFromCity(mordor);
//        mordor.addToFromDrives(mordorToShire.getId());
//        mordorToShire.setToCity(shire);
//        shire.addToFromDrives(mordorToShire.getId());
//        mordorToShire.setDate(new Date());
//
//        return Arrays.asList(shireToMordor, mordorToShire);
//    }
//
//    private UserDTO createUser() {
//        UserDTO user = new UserDTO();
//        user.setId(1L);
//        user.setFirstName("Frodo");
//        user.setLastName("Baggins");
//        user.setLogin("hobbit1");
//        user.setPassword("123456");
//
//        return user;
//    }
//
//    @Test
//    void addCustomerTest() throws Exception {
//        UserDTO user = createUser();
//
//        DriveDTO drive = new DriveDTO();
//        drive.setId(2L);
//        drive.addCustomer(user);
//
//        doNothing().when(driveFacade).addCustomer(any());
//        doReturn(drive).when(driveFacade).findDriveById(drive.getId());
//
//        mockMvc.perform(post("/drives/add-customer")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content("{" +
//                        "\"id\": 2," +
//                        "\"customerId\": 1" +
//                        "}"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("id").value(2L))
//                .andExpect(jsonPath("customers[0].id").value(1L));
//    }
//
//    @Test
//    void removeCustomerTest() throws Exception {
//        DriveDTO drive = new DriveDTO();
//        drive.setId(2L);
//
//        doNothing().when(driveFacade).removeCustomer(any());
//        doReturn(drive).when(driveFacade).findDriveById(drive.getId());
//
//        mockMvc.perform(post("/rides/remove-customer")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content("{" +
//                        "\"id\": 2," +
//                        "\"customerId\": 1" +
//                        "}"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("id").value(2L))
//                .andExpect(jsonPath("customers.length()").value(0));
//    }
//
//    @Test
//    void editCapacityTest() throws Exception {
//        DriveDTO drive = new DriveDTO();
//        drive.setId(2L);
//        drive.setCapacity(4);
//
//        doNothing().when(driveFacade).removeCustomer(any());
//        doReturn(drive).when(driveFacade).findDriveById(drive.getId());
//
//        mockMvc.perform(post("/drives/edit-capacity")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content("{" +
//                        "\"id\": 5," +
//                        "\"capacity\": 2" +
//                        "}"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("id").value(2L))
//                .andExpect(jsonPath("capacity").value(2));
//    }
//
//}
