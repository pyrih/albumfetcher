package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Service
public interface AlbumService {
    //@Cacheable(value = "albumInfo")
    Future<Optional<AbstractAlbum>> getAlbum(String artist, String album);

    List<AbstractAlbum> getAlbumPageSize(String artist, String album);

    InputStreamResource getAlbumDoc(Optional<AbstractAlbum> result) throws IOException, InvalidFormatException;

    Page<AbstractAlbum> getAlbumPage(String artist, String album, Pageable page);
}
