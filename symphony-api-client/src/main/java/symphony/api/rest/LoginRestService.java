package symphony.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import symphony.api.ApiDefinition;
import symphony.api.models.requests.LoginDTO;
import symphony.api.models.responses.LoginResponseDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static api.util.CommonHelpers.executeCurrentMethodLog;

public class LoginRestService extends ApiDefinition {

    private static Logger log = LoggerFactory.getLogger(SignUpRestService.class);

    private static final String SIGN_UP_URL = "/auth/login/";

    private HttpHeaders requestHeaders = new HttpHeaders();

    public ResponseEntity<LoginResponseDTO> logIn(JSONObject object) {
        executeCurrentMethodLog();
        HttpEntity<?> httpEntity = prepareHttpEntity(object);
        ResponseEntity<LoginResponseDTO> responseEntity;
        try {
            responseEntity = restTemplate.exchange(getBaseURL() + SIGN_UP_URL, HttpMethod.POST, httpEntity, LoginResponseDTO.class);
            return responseEntity;
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(" any other kind of exc.");
        }
        return null;
    }

    public String logInInvalidData(JSONObject object) {
        executeCurrentMethodLog();
        HttpEntity<?> httpEntity = prepareHttpEntity(object);
        String responseMessage;
        try {
            restTemplate.exchange(getBaseURL() + SIGN_UP_URL, HttpMethod.POST, httpEntity, LoginResponseDTO.class);
        } catch (HttpClientErrorException e) {
            responseMessage = e.getMessage();
            return responseMessage;
        } catch (Exception e) {
            log.error(" any other kind of exc.");
        }
        return null;
    }

    private HttpEntity<?> prepareHttpEntity(JSONObject object) {
        LoginDTO loginDTO = parseJSONObjectToLoginDTO(object);
        List<MediaType> acceptList = new ArrayList<>();
        acceptList.add(MediaType.APPLICATION_JSON);
        log.info("Http Entity is : " + loginDTO.toString());
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(acceptList);
        return new HttpEntity<LoginDTO>(loginDTO, requestHeaders);
    }

    private LoginDTO parseJSONObjectToLoginDTO(JSONObject object) {
        executeCurrentMethodLog();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LoginDTO toReturn = objectMapper.readValue(object.toString(), LoginDTO.class);
            log.info("Built model is : " + toReturn.toString());
            return toReturn;
        } catch (IOException ex) {
            log.error("An error has occured" + ex.getMessage());
            return null;
        }
    }
}
