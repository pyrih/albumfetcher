package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JsonToAlbumConverter implements Converter<String, AbstractAlbum> {

    @Autowired
    private JsonParseService jsonParseService;

    public JsonToAlbumConverter() {
    }

    @Override
    public AbstractAlbum convert(String source) {
        return jsonParseService.parse(source);
    }
}
