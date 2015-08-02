package jenny.folkloresearch;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acrcloud.rec.sdk.ACRCloudClient;
import com.acrcloud.rec.sdk.ACRCloudConfig;
import com.acrcloud.rec.sdk.IACRCloudListener;

import java.util.Locale;


public class MainActivity extends ActionBarActivity implements IACRCloudListener {
    private ACRCloudClient mClient;
    private ACRCloudConfig mConfig;

    private TextView mVolume, mResult, tv_time;

    private boolean mProcessing = false;
    private boolean initState = false;

    private String path = "";

    private ObjectAnimator animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale();

        //setContentView(R.layout.activity_main);

        //findViewById(R.id.search_button_main).setOnClickListener(new View.OnClickListener() {

        //    @Override
        //    public void onClick(View arg0) {
        //        start();
        //    }
        //});

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab searchTab = actionBar.newTab()
                                     .setText(R.string.search)
                                     .setTabListener(new TabListener<ExampleSearchFragment>(this, "search", ExampleSearchFragment.class));

        ActionBar.Tab regionsTab = actionBar.newTab()
                                      .setText(R.string.folklore_regions)
                                      .setTabListener(new TabListener<ExampleRegionsFragment>(this, "regions", ExampleRegionsFragment.class));
        actionBar.addTab(searchTab);
        actionBar.addTab(regionsTab);
    }

    public void onStartSearchButtonClick() {

    }

    public void onSearchButtonClick(View view) {
        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);
        //start();
    }

    public void start() {
        if(!this.initState) {
            this.mConfig = new ACRCloudConfig();
            //ЕдЦГјаМэ¶ФПу
            this.mConfig.acrcloudListener = this;
            this.mConfig.context = this;
            this.mConfig.host = "ap-southeast-1.api.acrcloud.com";
            this.mConfig.accessKey = "14945730a11c4c93e32141b9bec03a7e";
            this.mConfig.accessSecret = "98bAhbn6SY3PdnapvhMmjjHA88WrmOeeKR8DMWDl";
            this.mConfig.requestTimeout = 5000;

            this.mClient = new ACRCloudClient();
            this.mConfig.reqMode = ACRCloudConfig.ACRCloudRecMode.REC_MODE_REMOTE;
            this.initState = this.mClient.initWithConfig(this.mConfig);
            if (!this.initState) {
                Toast.makeText(this, "init error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        boolean x = this.mClient.startRecognize();

//        if (!mProcessing) {
//            mProcessing = true;
//            if (!this.mClient.startRecognize()) {
//                mProcessing = false;
//                mResult.setText("start error!");
//            }
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResult(String result) {
        this.animation.end();
        //String oldRes = (String) mResult.getText();
        //mResult.setText(oldRes + "\n" + result);
        //((TextView)findViewById(R.id.result_text)).setText(result);
        //mProcessing = false;

        if (this.mClient != null) {
            this.mClient.stop();
            mProcessing = false;
        }
    }

    @Override
    public void onVolumeChanged(double volume) {
        //mVolume.setText("volume: " + volume);
    }

    private void setLocale() {
        Configuration config = getBaseContext().getResources().getConfiguration();

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFS_NAME, 1);
        String savedLang = sharedPreferences.getString(Constants.LANG_PREF, config.locale.getLanguage());

        if (!savedLang.equals(config.locale.getLanguage())) {
            changeLocale(savedLang);
        }
    }

    private void changeLocale(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;

        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}
