package nc.labs.pyrih.albumfetcher.model;

import java.net.URL;
import java.util.List;

public class LastFmAlbum extends AbstractAlbum {

    public LastFmAlbum() {
    }

    public LastFmAlbum(String name, String artist, String genre, URL poster, List<AbstractTrack> tracks) {
        super(name, artist, genre, poster, tracks);
    }
}
