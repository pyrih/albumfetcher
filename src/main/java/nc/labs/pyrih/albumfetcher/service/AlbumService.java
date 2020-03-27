package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AlbumService {
    AbstractAlbum getAlbum(String artist, String album);

    InputStreamResource getAlbumDoc(AbstractAlbum result) throws IOException;

    Page<AbstractAlbum> getAlbumPage(String artist, String album, Pageable page);
}
