package symphony.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import symphony.api.ApiDefinition;
import symphony.api.models.requests.CreatePostDTO;
import symphony.api.models.responses.CreatePostResponseDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static api.util.CommonHelpers.executeCurrentMethodLog;

public class PostRestService extends ApiDefinition {

    private static Logger log = LoggerFactory.getLogger(SignUpRestService.class);

    private static final String SIGN_UP_URL = "/posts/";

    private HttpHeaders requestHeaders = new HttpHeaders();

    public ResponseEntity<CreatePostResponseDTO> createPost(JSONObject object, String token) {
        executeCurrentMethodLog();
        HttpEntity<?> httpEntity = prepareHttpEntity(object);
        ResponseEntity<CreatePostResponseDTO> responseEntity;
        try {
            responseEntity = restTemplate.exchange(getBaseURL() + SIGN_UP_URL, HttpMethod.POST, httpEntity, CreatePostResponseDTO.class);
            return responseEntity;
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("An error has occurred:" + e.getMessage());
        }
        return null;
    }

    private HttpEntity<?> prepareHttpEntity(JSONObject object) {
        CreatePostDTO postDTO = parseJSONObjectToCreatePostDTO(object);
        List<MediaType> acceptList = new ArrayList<>();
        acceptList.add(MediaType.APPLICATION_JSON);
        log.info("Http Entity is : " + postDTO.toString());
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(acceptList);
        return new HttpEntity<CreatePostDTO>(postDTO, requestHeaders);
    }

    private CreatePostDTO parseJSONObjectToCreatePostDTO(JSONObject object) {
        executeCurrentMethodLog();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CreatePostDTO toReturn = objectMapper.readValue(object.toString(), CreatePostDTO.class);
            log.info("Built model is : " + toReturn.toString());
            return toReturn;
        } catch (IOException ex) {
            log.error("An error has occurred: " + ex.getMessage());
            return null;
        }
    }
}
