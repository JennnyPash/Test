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

        TextView recName = (TextView)findViewById(R.id.name_region);
        recName.setText(region.getRegion());

        TextView recLoc = (TextView)findViewById(R.id.region_location);
        recLoc.setText(region.getLocation());

        TextView recDance = (TextView)findViewById(R.id.region_dances);
        recDance.setText(region.getDances());

        TextView recMusic = (TextView)findViewById(R.id.region_music);
        recMusic.setText(region.getMusic());

        TextView recCrafts = (TextView)findViewById(R.id.region_crafts);
        recCrafts.setText(region.getCrafts());

        TextView recHabits = (TextView)findViewById(R.id.region_habits);
        recHabits.setText(region.getHabits());

        TextView recClothes = (TextView)findViewById(R.id.region_clothes);
        recClothes.setText(region.getClothes());

    }
}