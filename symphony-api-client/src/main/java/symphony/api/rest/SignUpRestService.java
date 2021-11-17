package symphony.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import symphony.api.ApiDefinition;
import symphony.api.models.requests.SignUpDTO;
import symphony.api.models.responses.SignUpResponseDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static api.util.CommonHelpers.executeCurrentMethodLog;

public class SignUpRestService extends ApiDefinition {

    private static Logger log = LoggerFactory.getLogger(SignUpRestService.class);

    private static final String SIGN_UP_URL = "/auth/signup/";

    private HttpHeaders requestHeaders = new HttpHeaders();

    public ResponseEntity<SignUpResponseDTO> signUp(JSONObject object) {
        executeCurrentMethodLog();
        HttpEntity<?> httpEntity = prepareHttpEntity(object);
        ResponseEntity<SignUpResponseDTO> responseEntity;
        try {
            responseEntity = restTemplate.exchange(getBaseURL() + SIGN_UP_URL, HttpMethod.POST, httpEntity, SignUpResponseDTO.class);
            return responseEntity;
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("An error has Occurred:" + e.getMessage());
        }
        return null;
    }

    public String signUpInvalidData(JSONObject object) {
        executeCurrentMethodLog();
        HttpEntity<?> httpEntity = prepareHttpEntity(object);
        String responseMessage;
        try {
            restTemplate.exchange(getBaseURL() + SIGN_UP_URL, HttpMethod.POST, httpEntity, String.class);
        } catch (HttpClientErrorException e) {
            responseMessage = e.getMessage();
            return responseMessage;
        } catch (Exception e) {
            log.error("An error has Occurred:" + e.getMessage());
        }
        return null;
    }

    private HttpEntity<?> prepareHttpEntity(JSONObject object) {
        SignUpDTO signUpDTO = parseJSONObjectToSignUpDTO(object);
        List<MediaType> acceptList = new ArrayList<>();
        acceptList.add(MediaType.APPLICATION_JSON);
        log.info("Http Entity is : " + signUpDTO.toString());
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(acceptList);
        return new HttpEntity<SignUpDTO>(signUpDTO, requestHeaders);
    }

    private SignUpDTO parseJSONObjectToSignUpDTO(JSONObject object) {
        executeCurrentMethodLog();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SignUpDTO toReturn = objectMapper.readValue(object.toString(), SignUpDTO.class);
            log.info("Builded model is : " + toReturn.toString());
            return toReturn;
        } catch (IOException ex) {
            log.error("An error has occured" + ex);
            return null;
        }
    }
}
