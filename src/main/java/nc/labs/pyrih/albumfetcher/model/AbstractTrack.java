package nc.labs.pyrih.albumfetcher.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class AbstractTrack implements Serializable {
    private String name;
    private int duration;

    public AbstractTrack() {
    }

    public AbstractTrack(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractTrack)) return false;
        AbstractTrack that = (AbstractTrack) o;
        return duration == that.duration &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AbstractTrack.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("duration=" + duration)
                .toString();
    }
}
