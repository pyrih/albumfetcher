package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface JsonParseService {
    /**
     * Parses json string to AbstractAlbum wrapped Optional
     *
     * @param jsonString response body from external api
     * @return Optional<AbstractAlbum>
     */
    Optional<AbstractAlbum> parse(String jsonString);
}
