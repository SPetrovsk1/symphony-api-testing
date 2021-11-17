package test;

import api.BaseTest;
import data.LoginData;
import data.SignUpData;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import symphony.api.models.responses.LoginResponseDTO;
import symphony.api.models.responses.SignUpResponseDTO;
import symphony.api.models.responses.User;
import symphony.api.rest.LoginRestService;
import symphony.api.rest.SignUpRestService;

public class FullFlowTest extends BaseTest {

    private static Logger log = LoggerFactory.getLogger(FullFlowTest.class);

    private LoginRestService loginRestService;
    private SignUpRestService signUpRestService;
    private User signUpUser;


    @BeforeClass
    public void init() {
        loginRestService = server.initialise(LoginRestService.class);
    }

    @Test(priority = 1, dataProvider = "signUpRandomDataProvider", dataProviderClass = SignUpData.class)
    public void signUpTest(JSONObject object) {
        ResponseEntity<SignUpResponseDTO> response = signUpRestService.signUp(object);
        SignUpResponseDTO responseDTO = response.getBody();
        Assert.assertEquals(responseDTO.getSuccess(), "Thanks for signing up.", "Sign Up failed");
    }

    @Test(priority = 2, dataProvider = "logInDataProvider", dataProviderClass = LoginData.class)
    public void logInTest(JSONObject object) {
        ResponseEntity<LoginResponseDTO> response = loginRestService.logIn(object);
        LoginResponseDTO responseDTO = response.getBody();
        Assert.assertEquals(responseDTO.getUser().getDateOfBirth(), "20/03/1995", "Error in birth date");
    }
}
