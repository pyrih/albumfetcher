package nc.labs.pyrih.albumfetcher.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@XmlType(name = "abstractAlbum")
@XmlRootElement
@XmlSeeAlso({LastFmAlbum.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractAlbum implements Serializable {
    private String name;
    private String artist;
    private String genre;
    private URL poster;
    @XmlElementWrapper(name = "tracks", nillable = true)
    private List<AbstractTrack> track;

    public AbstractAlbum() {
    }

    public AbstractAlbum(String name, String artist, String genre, URL poster, List<AbstractTrack> tracks) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.poster = poster;
        this.track = tracks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public URL getPoster() {
        return poster;
    }

    public void setPoster(URL poster) {
        this.poster = poster;
    }

    public List<AbstractTrack> getTracks() {
        return track;
    }

    public void setTracks(List<AbstractTrack> tracks) {
        this.track = tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAlbum)) return false;
        AbstractAlbum that = (AbstractAlbum) o;
        return name.equals(that.name) &&
                artist.equals(that.artist) &&
                genre.equals(that.genre) &&
                poster.equals(that.poster) &&
                track.equals(that.track);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, artist, genre, poster, track);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AbstractAlbum.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("artist='" + artist + "'")
                .add("genre='" + genre + "'")
                .add("poster=" + poster)
                .add("tracks=" + track)
                .toString();
    }
}
