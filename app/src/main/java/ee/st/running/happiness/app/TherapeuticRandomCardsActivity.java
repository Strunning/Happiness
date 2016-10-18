package ee.st.running.happiness.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
/**
 * Created by StRunning on 2/25/2016.
 */
public class TherapeuticRandomCardsActivity extends Activity implements View.OnClickListener {

    private InterstitialAd mInterstitialAd;
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapeutic_cards);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        text = (TextView) findViewById(R.id.myImageViewCardText);
        text.setOnClickListener(this);
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                new CountDownTimer(30000, 1000) {   // 30000
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        requestNewInterstitial();
                    }
                 }.start();
            }

            @Override
            public void onAdLoaded() {
                showInterstitial();
            }
        });
    }


    @Override
    public void onClick(View view) {
//      showInterstitial();
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
//            Log.e("ADS", "Ahjhs");
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                  .addTestDevice("4100e9ef94a79000")
                  .build();

        mInterstitialAd.loadAd(adRequest);
    }


}