package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface JsonParseService {
    Optional<AbstractAlbum> parse(String jsonString);
}
