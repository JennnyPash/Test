package jenny.folkloresearch;

import android.content.Context;
import android.content.Intent;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.EverliveAppSettings;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueCondition;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueConditionOperator;
import com.telerik.everlive.sdk.core.result.RequestResult;
import com.telerik.everlive.sdk.core.result.RequestResultCallbackAction;

import java.util.ArrayList;

public class ActivityIntent {
    private String regionName;
    private Context context;
    private Intent intent;

    public ActivityIntent(String regionName, Context context) {
        this.regionName = regionName;
        this.context = context;
        this.intent = new Intent(context, RegionActivity.class);
    }

    public void StartActivity() {
        EverliveAppSettings appSettings = new EverliveAppSettings();
        appSettings.setApiKey(Constants.BACKEND_API_KEY);
        appSettings.setUseHttps(false); //set to false to use HTTP

        EverliveApp app = new EverliveApp(appSettings);

        app.workWith().data(Region.class).get().where(new ValueCondition("Name", this.regionName, ValueConditionOperator.EqualTo))
                .executeAsync(new RequestResultCallbackAction<ArrayList<Region>>() {
                    @Override
                    public void invoke(RequestResult<ArrayList<Region>> requestResult) {
                        if (requestResult.getSuccess()) {
                            Region region = requestResult.getValue().get(0);
                            intent.putExtra(Constants.REGION, region);
                            context.startActivity(intent);
                        } else {
                            // handle the error here
                        }
                    }
                });
    }
}
