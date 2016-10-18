package ee.st.running.happiness.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private String state = "introduction";
//    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_screen_slide);
        mPager = (ViewPager) findViewById(R.id.pager);
        getCurrentState ();
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), prepareStringArrayBundle());
        mPager.setAdapter(mPagerAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private Bundle prepareStringArrayBundle(){
        Bundle arguments = new Bundle();
        arguments.putStringArray("arg_string_array", getCorrespondingStringArray(state));
        arguments.putString("arg_string_activity", state);

        return arguments;
    }

    private String [] getCorrespondingStringArray (String state){
        int id = getResources().getIdentifier(state+"_strings","array",this.getPackageName());
        return getResources().getStringArray(id);
    }

    private void getCurrentState (){
        if (!getIntent().equals(null)) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                state = extras.getString("arg_chosen_option");
            }
        }
    }

}
