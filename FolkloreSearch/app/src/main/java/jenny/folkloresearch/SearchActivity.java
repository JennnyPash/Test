package jenny.folkloresearch;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.acrcloud.rec.sdk.ACRCloudClient;
import com.acrcloud.rec.sdk.ACRCloudConfig;
import com.acrcloud.rec.sdk.IACRCloudListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchActivity extends ActionBarActivity implements IACRCloudListener {
    private ACRCloudClient mClient;
    private ACRCloudConfig mConfig;
    private ObjectAnimator animation;

    private boolean mProcessing = false;
    private boolean initState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.initAnimation();
        this.startSearch();
    }

    @Override
    public void onResult(String result) {
        this.animation.end();
        this.mProcessing = false;

        if (this.mClient != null) {
            this.mClient.stop();
            this.mProcessing = false;
        }

        try {
            JSONObject obj = new JSONObject(result);
            JSONObject statusObj = obj.getJSONObject("status");

            switch (statusObj.getInt("code")) {
                case 0:
                    //navigate to new activity and show result there:
                    JSONArray jsonArray = obj.getJSONObject("metadata").getJSONArray("custom_files");
                    Intent successIntent = new Intent(this, ResultActivity.class);
                    successIntent.putExtra(Constants.SEARCH_RES, jsonArray.toString());
                    startActivity(successIntent);
                    break;
                default:
                    Toast.makeText(this,getResources().getString(R.string.no_result),Toast.LENGTH_LONG).show();
                    break;
            }
        } catch (Throwable t) {
            Toast.makeText(this, "Error parsing result", Toast.LENGTH_LONG);
            return;
        }
    }

    @Override
    public void onVolumeChanged(double volume) {
        //empty not needed but requred to be implemented by IACRCloudListener
    }

    public void onSearchClick(View view) {
        startSearch();
    }

    private void startRecognition() {
        if(!this.initState) {
            this.mConfig = new ACRCloudConfig();
            //���ü�������
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

        if (!mProcessing) {
            mProcessing = true;
            if (!this.mClient.startRecognize()) {
                mProcessing = false;
            }
        }
    }

    private void startSearch() {
        if (!mProcessing) {
            this.animation.start();
            this.startRecognition();
        }
    }

    private void initAnimation() {
        ImageView ib = (ImageView)findViewById(R.id.search_image);

        this.animation = ObjectAnimator.ofFloat(ib, "rotationY", 0.0f, 360f);
        this.animation.setDuration(3600);
        this.animation.setRepeatCount(ObjectAnimator.INFINITE);
        this.animation.setInterpolator(new AccelerateDecelerateInterpolator());
    }
}
