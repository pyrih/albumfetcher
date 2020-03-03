package nc.labs.pyrih.albumfetcher.controller;

import nc.labs.pyrih.albumfetcher.model.Album;
import nc.labs.pyrih.albumfetcher.service.AlbumService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @RequestMapping(path = "info", method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getInfo(@RequestParam(name = "artist", defaultValue = "Eminem") String artist,
                                     @RequestParam(name = "album", defaultValue = "Recovery") String album) {

        return ResponseEntity.ok(new Album(album, artist, "0000-01", null, null, null, null));
    }

    @RequestMapping(path = "doc", method = RequestMethod.GET)
    public ResponseEntity<?> getDoc(@RequestParam(name = "artist", defaultValue = "Eminem") String artist,
                                    @RequestParam(name = "album", defaultValue = "Recovery") String album) {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + artist + " " + album)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(null);
    }
}
