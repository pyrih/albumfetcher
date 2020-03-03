package nc.labs.pyrih.albumfetcher.model;

import java.io.Serializable;
import java.net.URL;
import java.util.StringJoiner;

public class Tag implements Serializable {
    private String name;
    private URL url;

    public Tag() {
    }

    public Tag(String name, URL url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (!name.equals(tag.name)) return false;
        return url.equals(tag.url);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Tag.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("url=" + url)
                .toString();
    }
}
