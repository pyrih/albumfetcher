package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Service
public interface AlbumService {

    /**
     * Returns Optional of album asynchronously
     *
     * @param artist singer name
     * @param album  album name
     * @return Future<Optional < AbstractAlbum>>
     */
    Future<Optional<AbstractAlbum>> getAlbum(String artist, String album);

    /**
     * Returns list of albums from external api
     *
     * @param artist singer name
     * @param album  album name
     * @return list of albums
     */
    List<AbstractAlbum> getAlbumPage(String artist, String album);

    /**
     * Returns an input stream of document contains album information
     *
     * @param albumOptional Optional<AbstractAlbum>
     * @return an input stream
     * @throws IOException
     * @throws InvalidFormatException
     */
    InputStreamResource getAlbumDoc(Optional<AbstractAlbum> albumOptional) throws IOException, InvalidFormatException;
}
