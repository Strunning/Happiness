package ee.st.running.happiness.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by StRunning on 2/22/2016.
 */
     public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private static int NUM_PAGES = 5; // check this out
        private Bundle arguments;
        public ScreenSlidePagerAdapter(FragmentManager fm, Bundle arguments) {
            super(fm);
             this.arguments = arguments;

            String [] arrayProcessed = arguments.getStringArray("arg_string_array");
            if(arrayProcessed!=null) {
                NUM_PAGES = arrayProcessed.length+1;
            }
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.newInstance(position, arguments);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }