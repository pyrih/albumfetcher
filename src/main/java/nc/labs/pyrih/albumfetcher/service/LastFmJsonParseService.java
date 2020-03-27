package nc.labs.pyrih.albumfetcher.service;

import nc.labs.pyrih.albumfetcher.model.AbstractAlbum;
import nc.labs.pyrih.albumfetcher.model.AbstractTrack;
import nc.labs.pyrih.albumfetcher.model.LastFmAlbum;
import nc.labs.pyrih.albumfetcher.model.LastFmTrack;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class LastFmJsonParseService implements JsonParseService {
    public AbstractAlbum parse(String response) {
        JSONObject obj = new JSONObject(response);


        if (obj.has("error")) {
            return null;
        } else {
            String albumName = obj.getJSONObject("album").getString("name");
            String albumArtist = obj.getJSONObject("album").getString("artist");
            //String albumMbid = obj.getJSONObject("album").getString("mbid");

            //=========================
            String albumGenre = "def genre";
            JSONArray arrOfTags = obj.getJSONObject("album").getJSONObject("tags").getJSONArray("tag");
            System.out.println("arrOfTags.length() is: " + arrOfTags.length());

            if (!arrOfTags.isEmpty()) {
                System.out.println(arrOfTags.toList());
            }

            //=========================

            List<AbstractTrack> tracks = new ArrayList<>();

            JSONArray arr = obj.getJSONObject("album").getJSONObject("tracks").getJSONArray("track");
            for (int i = 0; i < arr.length(); i++) {
                String trackName = arr.getJSONObject(i).getString("name");
                int trackDuration = Integer.parseInt(arr.getJSONObject(i).getString("duration"));
                int trackRank = Integer.parseInt(arr.getJSONObject(i).getJSONObject("@attr").getString("rank"));

                tracks.add(new LastFmTrack(trackName, trackDuration, trackRank));
            }
            return new LastFmAlbum(albumName, albumArtist, albumGenre, null, tracks, null);
        }
    }
}
