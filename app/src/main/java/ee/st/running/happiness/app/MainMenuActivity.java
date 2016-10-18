package ee.st.running.happiness.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by StRunning on 2/22/2016.
 */
public class MainMenuActivity extends Activity implements View.OnClickListener {

    static final String ARGUMENT_CHOSEN_OPTION = "arg_chosen_option";
    private String state;
    private String [] textViewValues, redirectionValues;
    private TextView centerText, tvVariant1, tvVariant2, tvVariant3, tvVariant4, tvVariant5, tvVariant6;
    private ImageView mainImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_menu);
        getCurrentState();
        mainImageView = (ImageView) findViewById(R.id.imageViewMainMenu);
        mainImageView.setImageDrawable(getRandomDrawableImage());
        initializeButtons();
        prepareRedirectionValues(state);

    }

    private void initializeButtons () {
        tvVariant1 = (TextView) findViewById(R.id.tvVariant1);
        tvVariant2 = (TextView) findViewById(R.id.tvVariant2);
        tvVariant3 = (TextView) findViewById(R.id.tvVariant3);
        tvVariant4 = (TextView) findViewById(R.id.tvVariant4);
        tvVariant5 = (TextView) findViewById(R.id.tvVariant5);
        tvVariant6 = (TextView) findViewById(R.id.tvVariant6);
        centerText = (TextView) findViewById(R.id.tvCenterText);

        setButtonsTexts(state);

        // if text != " " --> onClickListener
        centerText.setOnClickListener(this);
        tvVariant1.setOnClickListener(this);
        tvVariant2.setOnClickListener(this);
        tvVariant3.setOnClickListener(this);
        tvVariant4.setOnClickListener(this);
        tvVariant5.setOnClickListener(this);
        tvVariant6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.tvVariant1:
                intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra(ARGUMENT_CHOSEN_OPTION, redirectionValues[0]);
                startActivity(intent);
                break;
             case R.id.tvVariant2:
                intent= new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra(ARGUMENT_CHOSEN_OPTION, redirectionValues[1]);
                startActivity(intent);
                break;
             case R.id.tvVariant3:
                intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra(ARGUMENT_CHOSEN_OPTION,redirectionValues[2]);
                startActivity(intent);
                break;
             case R.id.tvVariant4:
                intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra(ARGUMENT_CHOSEN_OPTION, redirectionValues[3]);
                startActivity(intent);
                break;
             case R.id.tvVariant5:
                intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra(ARGUMENT_CHOSEN_OPTION, redirectionValues[4]);
                startActivity(intent);
                break;
             case R.id.tvVariant6:
                intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra(ARGUMENT_CHOSEN_OPTION,redirectionValues[5]);
                startActivity(intent);
                break;
        }
    }

    private void setButtonsTexts(String state) {
        textViewValues = getResources().getStringArray(getResourceIdFromName(state));
        tvVariant1.setText(textViewValues[0]);
        tvVariant2.setText(textViewValues[1]);
        tvVariant3.setText(textViewValues[2]);
        tvVariant4.setText(textViewValues[3]);
        tvVariant5.setText(textViewValues[4]);
        tvVariant6.setText(textViewValues[5]);
        centerText.setText(textViewValues[6]);
    }

    private void prepareRedirectionValues (String state){
        int id = getResources().getIdentifier(state+"_redirect_array","array",this.getPackageName());
        redirectionValues = getResources().getStringArray(id);
    }

    private int getResourceIdFromName (String state) {
        return getResources().getIdentifier(state,"array",this.getPackageName());
    }


    private void getCurrentState (){
        if (!getIntent().equals(null)) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                state = extras.getString("arg_string_activity");
            }
        }
    }

     private Drawable getRandomDrawableImage () {
        Random random = new Random();
        // min = 1, max = 7
        int randomNumber = random.nextInt((20 - 1) + 1) + 1;
        int id = getResources().getIdentifier("d"+String.valueOf(randomNumber), "drawable", getPackageName());
        return getResources().getDrawable(id);
    }
}
