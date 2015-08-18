package jenny.folkloresearch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        Intent regionIntent = this.getIntent();
        Region region = (Region)regionIntent.getSerializableExtra(Constants.REGION);

        TextView tv = (TextView)findViewById(R.id.region_location);
        tv.setText(region.getLocation());
    }
}