package test;

import api.BaseTest;
import data.LoginData;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import symphony.api.models.responses.LoginResponseDTO;
import symphony.api.rest.LoginRestService;

public class LogInTests extends BaseTest {


    private static Logger log = LoggerFactory.getLogger(LogInTests.class);

    private LoginRestService loginRestService;
    private static final String NOT_FOUND_MESSAGE = "404 Not Found";

    @BeforeClass
    public void init() {
        loginRestService = server.initialise(LoginRestService.class);
    }

    @Test(dataProvider = "logInDataProvider", dataProviderClass = LoginData.class)
    public void logInTest(JSONObject object) {
        ResponseEntity<LoginResponseDTO> response = loginRestService.logIn(object);
        LoginResponseDTO responseDTO = response.getBody();
        Assert.assertEquals(responseDTO.getUser().getDateOfBirth(), "20/03/1995", "Error in birth date");
    }

    @Test(dataProvider = "logInInvalidUserDataProvider", dataProviderClass = LoginData.class)
    public void logInInvalidUserTest(JSONObject object) {
        String response = loginRestService.logInInvalidData(object);
        Assert.assertEquals(response, NOT_FOUND_MESSAGE, "User was able to log in with invalid credentials");
    }
}
