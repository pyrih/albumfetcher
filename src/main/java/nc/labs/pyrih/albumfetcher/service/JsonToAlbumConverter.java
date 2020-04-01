package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JsonToAlbumConverter implements Converter<String, Optional<AbstractAlbum>> {

    @Autowired
    private JsonParseService jsonParseService;

    public JsonToAlbumConverter() {
    }

    @Override
    public Optional<AbstractAlbum> convert(String source) {
        return jsonParseService.parse(source);
    }
}
