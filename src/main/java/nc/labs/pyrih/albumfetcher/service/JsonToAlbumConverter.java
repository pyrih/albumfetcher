package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JsonToAlbumConverter implements Converter<String, Optional<AbstractAlbum>> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JsonParseService jsonParseService;

    public JsonToAlbumConverter() {
    }

    /**
     * Converts String-response from external api into Optional<AbstractAlbum>
     *
     * @param source takes String to converter
     * @return Optional<AbstractAlbum>
     */
    @Override
    public Optional<AbstractAlbum> convert(String source) {
        LOGGER.debug((String.format("Value of source string equals: %s", source)));
        return jsonParseService.parse(source);
    }
}
