package nc.labs.pyrih.albumfetcher.model;

import java.util.Objects;

public class LastFmTrack extends AbstractTrack {
    private int rank;

    public LastFmTrack() {
        super();
    }

    public LastFmTrack(String name, int duration, int rank) {
        super(name, duration);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastFmTrack)) return false;
        if (!super.equals(o)) return false;
        LastFmTrack that = (LastFmTrack) o;
        return rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rank);
    }

    @Override
    public String toString() {
        return "LastFmTrack{" +
                "rank=" + rank +
                "} " + super.toString();
    }
}
