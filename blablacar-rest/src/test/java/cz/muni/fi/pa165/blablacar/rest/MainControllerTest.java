package cz.muni.fi.pa165.blablacar.rest;

import cz.muni.fi.pa165.blablacar.RootWebContext;
import cz.muni.fi.pa165.blablacar.rest.controllers.MainController;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class MainControllerTest extends AbstractTestNGSpringContextTests {

    private final MockMvc mockMvc = standaloneSetup(new MainController()).build();

    @Test
    public void mainControllerTest() throws Exception {

        mockMvc.perform(get("/"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("drives_uri").value(ApiUris.ROOT_URI_DRIVES));
    }
}
