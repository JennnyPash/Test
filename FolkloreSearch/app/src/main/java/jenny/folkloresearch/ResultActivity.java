package jenny.folkloresearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.EverliveAppSettings;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueCondition;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueConditionOperator;
import com.telerik.everlive.sdk.core.result.RequestResult;
import com.telerik.everlive.sdk.core.result.RequestResultCallbackAction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResultActivity extends ActionBarActivity {
    private String regionName;
    private Intent regionIntent;

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
        String apiKey = "cyOj2Wf8o7ELfw6j";

        EverliveAppSettings appSettings = new EverliveAppSettings();
        appSettings.setApiKey(apiKey);
        appSettings.setUseHttps(false); //set to false to use HTTP

        EverliveApp app = new EverliveApp(appSettings);

        this.regionIntent = new Intent(this, RegionActivity.class);

        app.workWith().data(Region.class).get().where(new ValueCondition("Name", this.regionName, ValueConditionOperator.EqualTo))
                .executeAsync(new RequestResultCallbackAction<ArrayList<Region>>() {
                    @Override
                    public void invoke(RequestResult<ArrayList<Region>> requestResult) {
                        if (requestResult.getSuccess()) {
                            Region region = requestResult.getValue().get(0);
                            regionIntent.putExtra(Constants.REGION, region);
                            startActivity(regionIntent);
                        } else {
                            // handle the error here
                        }
                    }
                });
    }
}
