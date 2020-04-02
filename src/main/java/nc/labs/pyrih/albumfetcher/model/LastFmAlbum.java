package nc.labs.pyrih.albumfetcher.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URL;
import java.util.List;

@XmlType(name = "album")
@XmlRootElement
public class LastFmAlbum extends AbstractAlbum {

    public LastFmAlbum() {
    }

    public LastFmAlbum(String name, String artist, String genre, URL poster, List<AbstractTrack> tracks) {
        super(name, artist, genre, poster, tracks);
    }
}
