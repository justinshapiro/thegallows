package csci3320.thegallows;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class StartScreen extends Activity {

    Button buttonRegularPlay = null;
    Button buttonFreeplay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Determine which layout file to use
        Configuration currentConfig = getResources().getConfiguration();
        if (currentConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_startscreen);
        } else if(currentConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_startscreen_horizontal);
        }

        // Get the Font Object
        final Typeface chalkTypeFace = Typeface.createFromAsset(getAssets(), "fonts/squeakychalksound.ttf");

        // Get the two buttons
        buttonRegularPlay = (Button) findViewById(R.id.regular_play_button);
        buttonFreeplay = (Button) findViewById(R.id.freeplay_button);

        // Set the button font
        buttonRegularPlay.setTypeface(chalkTypeFace);
        buttonFreeplay.setTypeface(chalkTypeFace);

        // Get an intent to launch the game
        final Intent launchGame = new Intent(this, Gameplay.class);

        // Set the Regular Play Button logic
        buttonRegularPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                buttonRegularPlay.setBackgroundColor(0x3C000000);
                launchGame.putExtra("GameType", "REGULAR");
                launchGame.putExtra("LEVEL", 1);
                launchGame.putExtra("LIFE", 3);
                launchGame.putExtra("HINTS", 3);
                startActivity(launchGame);
            }
        });

        // Set the Free Play Button logic
        buttonFreeplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                buttonFreeplay.setBackgroundColor(0x3C000000);
                launchGame.putExtra("GameType", "FREEPLAY");
                launchGame.putExtra("LEVEL", 0);
                launchGame.putExtra("LIFE", 0);
                launchGame.putExtra("HINTS", 1);
                startActivity(launchGame);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        buttonFreeplay.setBackgroundColor(0x00000000);
        buttonRegularPlay.setBackgroundColor(0x00000000);
    }

    // Handle orientation changes Manually here.
    @Override
    public void onConfigurationChanged(Configuration config){
        // Just reload this activity
        super.onConfigurationChanged(config);
        final Intent reload = new Intent(this, StartScreen.class);
        finish();
        startActivity(reload);
    }
}
