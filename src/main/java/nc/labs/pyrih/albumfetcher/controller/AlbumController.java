package nc.labs.pyrih.albumfetcher.controller;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import nc.labs.pyrih.albumfetcher.service.AlbumService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api")
public class AlbumController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final AlbumService albumService;

    /**
     * Constructs object and injects AlbumService bean
     *
     * @param albumService AlbumService bean
     */
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * Returns information about a singer and album name in json or xml format
     *
     * @param artist music singer name
     * @param album  album name
     * @return ResponseEntity<?>
     */
    @Cacheable(value = "albumInfo")
    @RequestMapping(path = "info", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getInfo(@RequestParam(name = "artist", defaultValue = "Eminem") String artist,
                                     @RequestParam(name = "album", defaultValue = "Recovery") String album) {
        LOGGER.info(String.format("Request:: value 1: %s value 2: %s", artist, album));
        Optional<AbstractAlbum> result = Optional.empty();
        try {
            result = albumService.getAlbum(artist, album).get();
            LOGGER.info(String.format("/info request result is: %s", result));
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Cant get album object from service", e);
        }
        LOGGER.info(String.format("Album:: %s - %s fetched", artist, album));
        return ResponseEntity.of(result);
    }

    /**
     * Returns page contains information about a singer and album name in json or xml format
     *
     * @param artist music singer name
     * @param album  album name
     * @param page   page number
     * @param size   number of elements on page
     * @return ResponseEntity<Page < AbstractAlbum>>
     */
    @RequestMapping(path = "info/page", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<Page<AbstractAlbum>> getPage(@RequestParam(name = "artist", defaultValue = "Eminem") String artist,
                                                       @RequestParam(name = "album", defaultValue = "Recovery") String album,
                                                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                       @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        List<AbstractAlbum> albums = albumService.getAlbumPage(artist, album);
        Pageable pageable = PageRequest.of(page - 1, size);
        int start = (page - 1) * size;
        int end = start + size;
        Page<AbstractAlbum> pages;
        if (end <= albums.size()) {
            pages = new PageImpl<>(albums.subList(start, end), pageable, albums.size());
            return new ResponseEntity<>(pages, HttpStatus.OK);
        } else {
            if (start < albums.size()) {
                int toIndex = albums.size();
                pages = new PageImpl<>(albums.subList(start, toIndex), pageable, albums.size());
                LOGGER.info(String.format("/info/page request result is: pages - %s", pages));
                return new ResponseEntity<>(pages, HttpStatus.OK);
            }
        }
        LOGGER.error("Response entity does not have a value");
        return ResponseEntity.noContent().build();

    }

    /**
     * Returns *.docx format document contains information about a singer and album name,
     * track list table and album cover in *.png format
     *
     * @param artist music singer name
     * @param album  album name
     * @return ResponseEntity<?>
     * @throws IOException
     * @throws InvalidFormatException
     */
    @RequestMapping(path = "doc", method = RequestMethod.GET)
    public ResponseEntity<?> getDoc(@RequestParam(name = "artist", defaultValue = "Eminem") String artist,
                                    @RequestParam(name = "album", defaultValue = "Recovery") String album) throws IOException, InvalidFormatException {
        Optional<AbstractAlbum> albumOptional = Optional.empty();
        try {
            albumOptional = albumService.getAlbum(artist, album).get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Cant get album object from service", e);
        }
        InputStreamResource result = albumService.getAlbumDoc(albumOptional);
        if (result != null) {
            String headerValue = String.format("attachment;filename=%s_%s_%s.docx", artist, album,
                    new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(result);
        } else return ResponseEntity.noContent().build();
    }
}
