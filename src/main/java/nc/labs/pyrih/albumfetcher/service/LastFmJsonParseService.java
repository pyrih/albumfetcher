package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import nc.labs.pyrih.albumfetcher.model.AbstractTrack;
import nc.labs.pyrih.albumfetcher.model.LastFmAlbum;
import nc.labs.pyrih.albumfetcher.model.LastFmTrack;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class LastFmJsonParseService implements JsonParseService {
    private static final String FAILURE_RESPONSE = "error";
    private static final int LARGE_IMAGE_SIZE = 3;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private URL poster;
    private List<AbstractTrack> tracks;

    public Optional<AbstractAlbum> parse(String response) {
        JSONObject object = new JSONObject(response);

        if (object.has(FAILURE_RESPONSE)) {
            return Optional.empty();
        } else {
            JSONObject root = object.getJSONObject("album");
            String name = root.getString("name");
            String artist = root.getString("artist");
            String genre = "No content";
            poster = getPoster(root);
            tracks = getTracks(root);
            LOGGER.info("### parsed");
            return Optional.of(new LastFmAlbum(name, artist, genre, poster, tracks));
        }
    }

    private URL getPoster(JSONObject root) {
        String imageJsonValue = root.getJSONArray("image")
                .getJSONObject(LARGE_IMAGE_SIZE)
                .getString("#text");
        if (!imageJsonValue.isEmpty()) {
            try {
                poster = new URL(imageJsonValue);
                return poster;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private List<AbstractTrack> getTracks(JSONObject root) {
        JSONArray array = root.getJSONObject("tracks").getJSONArray("track");
        if (!array.isEmpty()) {
            tracks = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                String track = array.getJSONObject(i).getString("name");
                int duration = Integer.parseInt(array.getJSONObject(i).getString("duration"));
                tracks.add(new LastFmTrack(track, duration));
            }
        }
        return tracks;
    }
}
