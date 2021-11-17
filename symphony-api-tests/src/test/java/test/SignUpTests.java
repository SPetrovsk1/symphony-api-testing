package test;

import api.BaseTest;
import data.SignUpData;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import symphony.api.models.responses.SignUpResponseDTO;
import symphony.api.rest.SignUpRestService;

public class SignUpTests extends BaseTest {

    private static Logger log = LoggerFactory.getLogger(SignUpTests.class);

    private SignUpRestService signUpRestService;
    private static final String BAD_REQUEST_MESSAGE = "400 Bad Request";

    @BeforeClass
    public void init() {
        signUpRestService = server.initialise(SignUpRestService.class);
    }

    @Test(dataProvider = "signUpRandomDataProvider", dataProviderClass = SignUpData.class)
    public void signUpTest(JSONObject object) {
        ResponseEntity<SignUpResponseDTO> response = signUpRestService.signUp(object);
        SignUpResponseDTO responseDTO = response.getBody();
        Assert.assertEquals(responseDTO.getSuccess(), "Thanks for signing up.", "Sign Up failed");
    }

    @Test(dataProvider = "signUpDataProvider", dataProviderClass = SignUpData.class)
    public void signUpInvalidUserTest(JSONObject object) {
        String response = signUpRestService.signUpInvalidData(object);
        Assert.assertEquals(response, BAD_REQUEST_MESSAGE, "Sign Up succeeded with an already existing username");
    }

    @Test(dataProvider = "signUpInvalidPasswordDataProvider", dataProviderClass = SignUpData.class)
    public void signUpInvalidPasswordTest(JSONObject object) {
        String response = signUpRestService.signUpInvalidData(object);
        Assert.assertEquals(response, BAD_REQUEST_MESSAGE, "Sign Up succeeded with a password that does not match the criteria");
    }
}
