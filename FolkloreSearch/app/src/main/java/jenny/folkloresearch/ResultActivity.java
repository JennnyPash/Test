package jenny.folkloresearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;


public class ResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        Intent intent = this.getIntent();

        try {
            JSONArray customFields = new JSONArray(intent.getStringExtra(Constants.SEARCH_RES));
            if (customFields.length() > 0) {
                JSONObject songJson = customFields.getJSONObject(0);

                Song song = new Song();
                song.name = songJson.getString("title");
                song.artist = songJson.getString("artist");
                song.region = songJson.getString("region");

                Uri imageUrl = Uri.parse("android.resource://jenny.folkloresearch/drawable/" + song.region);

                ((TextView) findViewById(R.id.song_name)).setText(song.getName());
                ((TextView)findViewById(R.id.song_artist)).setText(song.getArtist());
                ((ImageView)findViewById(R.id.song_region)).setImageURI(imageUrl);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class Song {
        private String region;
        private String artist;
        private String name;

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
