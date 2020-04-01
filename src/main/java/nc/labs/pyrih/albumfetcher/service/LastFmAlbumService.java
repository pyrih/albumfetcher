package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Primary
public class LastFmAlbumService implements AlbumService {
    private static final String DOCX_TEMPLATE_PATH = "src/main/resources/template.docx";
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${apikey.lastfm}")
    private String API_KEY;
    private RestTemplate restTemplate;
    private DocumentService documentService;
    private JsonToAlbumConverter jsonToAlbumConverter;

    public LastFmAlbumService(RestTemplate restTemplate, DocumentService documentService, JsonToAlbumConverter jsonToAlbumConverter) {
        this.restTemplate = restTemplate;
        this.documentService = documentService;
        this.jsonToAlbumConverter = jsonToAlbumConverter;
    }

    @Async
    // @Cacheable(value = "albumInfo", key = "#artist + #album")
    @Override
    public Future<Optional<AbstractAlbum>> getAlbum(String artist, String album) {
        LOGGER.debug("Execute method asynchronously: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> response;
        Optional<AbstractAlbum> albumOptional;
        try {
            response = restTemplate.getForEntity(getUrlString(artist, album), String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                albumOptional = jsonToAlbumConverter
                        .convert(Objects.requireNonNull(response.getBody()));
                return new AsyncResult<>(albumOptional);
            } else {
                return new AsyncResult<>(Optional.empty());
            }
        } catch (RestClientException e) {
            return new AsyncResult<>(Optional.empty());
        }
    }

    @Override
    public List<AbstractAlbum> getAlbumPageSize(String artist, String album) {
        List<AbstractAlbum> albums = new ArrayList<>();
        Optional<AbstractAlbum> albumOptional = Optional.empty();
        try {
            albumOptional = getAlbum(artist, album).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        albumOptional.ifPresent(albums::add);
        return albums;
    }

    @Override
    public InputStreamResource getAlbumDoc(Optional<AbstractAlbum> albumOptional) throws IOException {
        byte[] resource = documentService.getDocStream(DOCX_TEMPLATE_PATH, albumOptional);
        return new InputStreamResource(new ByteArrayInputStream(resource));
    }

    @Override
    public Page<AbstractAlbum> getAlbumPage(String artist, String album, Pageable page) {
        return null;
    }

    private String getUrlString(String artist, String album) {
        return String
                .format("http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=%s&artist=%s&album=%s&format=json", API_KEY, artist, album);
    }
}
