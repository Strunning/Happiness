package ee.st.running.happiness.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by StRunning on 2/22/2016.
 */
public class ScreenSlidePageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_STRING_ARRAY = "arg_string_array";
    static final String ARGUMENT_STRING_ACTIVITY = "arg_string_activity";
    private int pageNumber;
    private String activity;
    private String [] strings;

    static ScreenSlidePageFragment newInstance (int page, Bundle arguments) {
        ScreenSlidePageFragment screenSlidePageFragment = new ScreenSlidePageFragment();
        Bundle newArgs = new Bundle(); // oh, you definitely gonna need this
        newArgs.putInt(ARGUMENT_PAGE_NUMBER, page);
        newArgs.putStringArray(ARGUMENT_STRING_ARRAY, arguments.getStringArray(ARGUMENT_STRING_ARRAY));
        newArgs.putString(ARGUMENT_STRING_ACTIVITY, arguments.getString(ARGUMENT_STRING_ACTIVITY));
        screenSlidePageFragment.setArguments(newArgs);
        return screenSlidePageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strings = getArguments().getStringArray(ARGUMENT_STRING_ARRAY);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        activity = getArguments().getString(ARGUMENT_STRING_ACTIVITY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, null);
        ImageView imageViewMain = (ImageView) rootView.findViewById(R.id.imageViewMain);
        TextView imageViewText = (TextView) rootView.findViewById(R.id.myImageViewText);

        if(pageNumber<strings.length && pageNumber>=0) {
            imageViewText.setText(strings[pageNumber]);
            imageViewMain.setImageDrawable(getRandomDrawableImage());
        } else if(pageNumber==strings.length) {
//
            Intent intent;
                    try{
                    intent = new Intent(getContext(), Class.forName(getRedirectionString(activity)));
                    intent.putExtra(ARGUMENT_STRING_ACTIVITY,activity);
                    startActivity(intent);
                    } catch (Exception e) {
                    imageViewText.setText("Unfortunately, the app has stopped. Please restart it");
                        e.printStackTrace();
                    }

        }
        return rootView;
    }

    private String getRedirectionString (String activity){
        int id = getResources().getIdentifier(activity+"_redirection","string", getActivity().getPackageName());
        return getResources().getString(id);
    }

    private Drawable getRandomDrawableImage () {
        Random random = new Random();
        // min = 1, max = 20
        int randomNumber = random.nextInt((20 - 1) + 1) + 1;
        int id = getResources().getIdentifier("d"+String.valueOf(randomNumber),"drawable",getActivity().getPackageName());
        return getResources().getDrawable(id);
    }
}