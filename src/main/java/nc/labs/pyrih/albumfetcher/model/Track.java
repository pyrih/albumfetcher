package nc.labs.pyrih.albumfetcher.model;

import java.io.Serializable;
import java.util.StringJoiner;

public class Track implements Serializable {
    private int rank;
    private String name;
    private int duration;

    public Track() {
    }

    public Track(int rank, String name, int duration) {
        this.rank = rank;
        this.name = name;
        this.duration = duration;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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
        if (!(o instanceof Track)) return false;

        Track track = (Track) o;

        if (rank != track.rank) return false;
        if (duration != track.duration) return false;
        return name.equals(track.name);
    }

    @Override
    public int hashCode() {
        int result = rank;
        result = 31 * result + name.hashCode();
        result = 31 * result + duration;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Track.class.getSimpleName() + "[", "]")
                .add("rank=" + rank)
                .add("name='" + name + "'")
                .add("duration=" + duration)
                .toString();
    }
}
