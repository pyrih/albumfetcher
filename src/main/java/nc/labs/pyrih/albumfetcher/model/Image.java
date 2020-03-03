package nc.labs.pyrih.albumfetcher.model;

import java.io.Serializable;
import java.net.URL;
import java.util.StringJoiner;

public class Image implements Serializable {
    private String size;
    private URL url;

    public Image() {
    }

    public Image(String size, URL url) {
        this.size = size;
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
        if (!(o instanceof Image)) return false;

        Image image = (Image) o;

        if (!size.equals(image.size)) return false;
        return url.equals(image.url);
    }

    @Override
    public int hashCode() {
        int result = size.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Image.class.getSimpleName() + "[", "]")
                .add("size='" + size + "'")
                .add("url=" + url)
                .toString();
    }
}
