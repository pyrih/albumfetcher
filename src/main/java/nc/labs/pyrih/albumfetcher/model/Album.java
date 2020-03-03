package nc.labs.pyrih.albumfetcher.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@XmlRootElement
public class Album implements Serializable {
    private String name;
    private String artist;
    private String mbid;
    private URL url;
    private Image image;
    private List<Track> tracks;
    private List<Tag> tags;

    public Album() {
    }

    public Album(String name, String artist, String mbid, URL url, Image image, List<Track> tracks, List<Tag> tags) {
        this.name = name;
        this.artist = artist;
        this.mbid = mbid;
        this.url = url;
        this.image = image;
        this.tracks = tracks;
        this.tags = tags;
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

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;

        Album album = (Album) o;

        if (!name.equals(album.name)) return false;
        if (!artist.equals(album.artist)) return false;
        if (!mbid.equals(album.mbid)) return false;
        if (!url.equals(album.url)) return false;
        if (!image.equals(album.image)) return false;
        if (!tracks.equals(album.tracks)) return false;
        return Objects.equals(tags, album.tags);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + mbid.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + tracks.hashCode();
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Album.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("artist='" + artist + "'")
                .add("mbid='" + mbid + "'")
                .add("url=" + url)
                .add("image=" + image)
                .add("tracks=" + tracks)
                .add("tags=" + tags)
                .toString();
    }
}
