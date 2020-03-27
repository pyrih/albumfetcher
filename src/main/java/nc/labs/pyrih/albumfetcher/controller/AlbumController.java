package nc.labs.pyrih.albumfetcher.controller;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import nc.labs.pyrih.albumfetcher.service.AlbumService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("api")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Cacheable(value = "albums", key = "#album")
    @RequestMapping(path = "info", method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getInfo(@RequestParam(name = "artist", defaultValue = "Eminem") String artist,
                                     @RequestParam(name = "album", defaultValue = "Recovery") String album) {

        AbstractAlbum result = albumService.getAlbum(artist, album);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "info/page", method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Pageable> getPage(@RequestParam(name = "artist", defaultValue = "Eminem") String artist,
                                            @RequestParam(name = "album", defaultValue = "Recovery") String album,
                                            @RequestParam(name = "page", defaultValue = "1") @NotNull Pageable page) {
        Page<AbstractAlbum> albums = albumService.getAlbumPage(artist, album, page);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(path = "doc", method = RequestMethod.GET)
    public ResponseEntity<?> getDoc(@RequestParam(name = "artist", defaultValue = "Eminem") String artist,
                                    @RequestParam(name = "album", defaultValue = "Recovery") String album) throws IOException {

        AbstractAlbum result = albumService.getAlbum(artist, album);
        InputStreamResource streamResource = albumService.getAlbumDoc(result);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + artist + " " + album)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(streamResource);
    }
}
