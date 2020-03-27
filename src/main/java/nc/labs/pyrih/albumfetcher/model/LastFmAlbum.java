package nc.labs.pyrih.albumfetcher.model;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class LastFmAlbum extends AbstractAlbum {
    private String mbid;

    public LastFmAlbum() {
        super();
    }

    public LastFmAlbum(String name, String artist, String genre, URL poster, List<AbstractTrack> tracks, String mbid) {
        super(name, artist, genre, poster, tracks);
        this.mbid = mbid;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastFmAlbum)) return false;
        if (!super.equals(o)) return false;
        LastFmAlbum that = (LastFmAlbum) o;
        return mbid.equals(that.mbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mbid);
    }

    @Override
    public String toString() {
        return "LastFmAlbum{" +
                "mbid='" + mbid + '\'' +
                "} " + super.toString();
    }
}
