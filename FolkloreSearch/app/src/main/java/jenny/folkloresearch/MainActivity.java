package jenny.folkloresearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale();

        //setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //actionBar.setDisplayShowTitleEnabled(false);

        ActionBar.Tab searchTab = actionBar.newTab()
                                     .setText(R.string.search)
                                     .setTabListener(new TabListener<ExampleSearchFragment>(
                                             this, "search", ExampleSearchFragment.class));

        ActionBar.Tab regionsTab = actionBar.newTab()
                                      .setText(R.string.folklore_regions)
                                      .setTabListener(new TabListener<ExampleRegionsFragment>(this, "regions", ExampleRegionsFragment.class));
        actionBar.addTab(searchTab);
        actionBar.addTab(regionsTab);
        //actionBar.setDisplayHomeAsUpEnabled(true);
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
