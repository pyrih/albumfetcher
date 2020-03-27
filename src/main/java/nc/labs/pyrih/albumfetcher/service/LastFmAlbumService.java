package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@Primary
public class LastFmAlbumService implements AlbumService {

    public static final String API_ROOT = "http://ws.audioscrobbler.com/2.0/";
    private static final String API_KEY = "f910f3e1f5f3610894be40136c0df9ae";
    private String url = null;
    private RestTemplate restTemplate;
    private DocumentService documentService;
    private JsonToAlbumConverter jsonToAlbumConverter;

    public LastFmAlbumService(RestTemplate restTemplate, DocumentService documentService, JsonToAlbumConverter jsonToAlbumConverter) {
        this.restTemplate = restTemplate;
        this.documentService = documentService;
        this.jsonToAlbumConverter = jsonToAlbumConverter;
    }

    @Override
    public AbstractAlbum getAlbum(String artist, String album) {
        url = API_ROOT +
                "?method=album.getinfo&api_key=" + API_KEY + "&artist=" + artist + "&album=" + album + "&format=json";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        AbstractAlbum result = null;
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful: " + response.getBody());
            // result = LastFmJsonParseService.parse(response.getBody());
            result = jsonToAlbumConverter.convert(response.getBody());
        } else {
            System.out.println("Request Failed: " + response.getStatusCode());
            return null;
        }

        return result;
    }

    @Override
    public Page<AbstractAlbum> getAlbumPage(String artist, String album, Pageable page) {
        return null;
    }

    @Override
    public InputStreamResource getAlbumDoc(AbstractAlbum result) throws IOException {
        byte[] resource = documentService.getDocStream(null, result);
        return new InputStreamResource(new ByteArrayInputStream(resource));
    }
}
