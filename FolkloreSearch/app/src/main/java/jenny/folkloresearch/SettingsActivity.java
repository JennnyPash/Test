package jenny.folkloresearch;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Locale;

public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initLanguageSpinner();
    }

    public void saveSettings(View view) {
        Spinner languageSpinner = (Spinner)findViewById(R.id.language_spinner);
        Language language = (Language)languageSpinner.getSelectedItem();

        Boolean settingsSaved = saveLocale(language.code);

        if (settingsSaved) {
            showSettingsWillTakeEffectAfterRestart();
        }
    }

    private void showSettingsWillTakeEffectAfterRestart() {
        final TextSwitcher textSwitcher = (TextSwitcher) findViewById(R.id.text_switcher);

        Animation fade_in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation fade_out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        textSwitcher.setInAnimation(fade_in);
        textSwitcher.setOutAnimation(fade_out);

        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(SettingsActivity.this);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                return textView;
            }
        });
        textSwitcher.setText(getResources().getString(R.string.changes_will_take_effect_after_restart));
    }

    private void initLanguageSpinner() {
        Locale locale = getBaseContext().getResources().getConfiguration().locale;

        Language[] languagesArray = new Language[] {
                new Language(getResources().getString(R.string.bg), "bg"),
                new Language(getResources().getString(R.string.en), "en")
        };

        Spinner languageSpinner = (Spinner)findViewById(R.id.language_spinner);
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Language>(this, android.R.layout.simple_spinner_dropdown_item, languagesArray);
        languageSpinner.setAdapter(spinnerAdapter);

        for(int i=0; i < languagesArray.length; i++) {
            if (languagesArray[i].code.equals(locale.getLanguage())) {
                languageSpinner.setSelection(i);
            }
        }
    }

    private boolean saveLocale(String language) {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFS_NAME, 1);
        String currentLanguage = sharedPreferences.getString(Constants.LANG_PREF, "");

        if (!language.equals(currentLanguage)) {
            sharedPreferences.edit().putString(Constants.LANG_PREF, language).commit();
            return true;
        }
        return false;
    }

    private class Language {
        private String label;
        private String code;

        public Language(String label, String code) {
            this.label = label;
            this.code = code;
        }

        public String getLabel() {
            return this.label;
        }

        public String getCode() {
            return this.code;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }
}
