package symphony.api.rest;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import symphony.api.ApiDefinition;
import symphony.api.models.responses.ReadCommentsResponseDTO;

import static api.util.CommonHelpers.executeCurrentMethodLog;

public class GetCommentsRestService extends ApiDefinition {

    private static final String COMMENTS_URL = "posts/{id}/comments/";

    private HttpHeaders requestHeaders = new HttpHeaders();

    public ResponseEntity<ReadCommentsResponseDTO> getComments() {
        executeCurrentMethodLog();
        HttpEntity<?> httpEntity = getHttpEntity();
        ResponseEntity<ReadCommentsResponseDTO> responseEntity;
        try {
            responseEntity = restTemplate.exchange(getBaseURL() + COMMENTS_URL, HttpMethod.GET, httpEntity, ReadCommentsResponseDTO.class);
            return responseEntity;
        } catch (HttpClientErrorException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            log.info("An error Occurred:" + e.getMessage());
        }
        return null;
    }

    protected HttpEntity<?> getHttpEntity() {
        executeCurrentMethodLog();
        httpRequestHeaders.setContentType(MediaType.APPLICATION_JSON);
        log.info("HttpEntity successfully prepared!");
        return new HttpEntity<Object>(httpRequestHeaders);
    }
}
