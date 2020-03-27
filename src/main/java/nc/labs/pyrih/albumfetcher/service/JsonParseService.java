package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.springframework.stereotype.Component;

@Component
public interface JsonParseService {
    AbstractAlbum parse(String jsonString);
}
