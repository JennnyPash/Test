package jenny.folkloresearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultActivity extends ActionBarActivity {
    private String regionName;

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
                song.setName(songJson.getString("title"));
                song.setArtist(songJson.getString("artist"));
                song.setRegion(songJson.getString("region"));

                Uri imageUrl = Uri.parse("android.resource://jenny.folkloresearch/drawable/" + song.getRegion());

                ((TextView) findViewById(R.id.song_name)).setText(song.getName());
                ((TextView)findViewById(R.id.song_artist)).setText(song.getArtist());
                ((ImageView)findViewById(R.id.song_region)).setImageURI(imageUrl);

                this.regionName = song.getRegion();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void openRegion(View view) {
        ActivityIntent activityIntent = new ActivityIntent(this.regionName, this);
        activityIntent.StartActivity();
    }
}
