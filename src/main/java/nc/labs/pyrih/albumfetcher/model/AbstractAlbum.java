package nc.labs.pyrih.albumfetcher.model;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class AbstractAlbum implements Serializable {
    private String name;
    private String artist;
    private String genre;
    private URL poster;
    private List<AbstractTrack> tracks;

    public AbstractAlbum() {
    }

    public AbstractAlbum(String name, String artist, String genre, URL poster, List<AbstractTrack> tracks) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.poster = poster;
        this.tracks = tracks;
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
        return tracks;
    }

    public void setTracks(List<AbstractTrack> tracks) {
        this.tracks = tracks;
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
                tracks.equals(that.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, artist, genre, poster, tracks);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AbstractAlbum.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("artist='" + artist + "'")
                .add("genre='" + genre + "'")
                .add("poster=" + poster)
                .add("tracks=" + tracks)
                .toString();
    }
}
