package jenny.folkloresearch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;


public class ResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = this.getIntent();

        try {
            JSONObject songInfo = new JSONObject(intent.getStringExtra(Constants.SEARCH_RES));
            String artist = songInfo.getString("artist");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
