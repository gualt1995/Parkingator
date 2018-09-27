package android.parkingator;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


/**
 * Created by 3407189 on 02/03/16.
 */
public class MainMenuActivity extends AppCompatActivity {
    SoundPool mySound;
    int demarrageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        mySound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        demarrageId = mySound.load(this, R.raw.demarrage, 1);
    }

    public void levelSelect(View button) {
        Intent levelSelect = new Intent(this, LevelselectActivity.class);
        startActivity(levelSelect);
        mySound.play(demarrageId, 1, 1, 1, 0, 1);
    }

    public void credits(View view) {
        Intent credits = new Intent(this, Credits.class);
        startActivity(credits);
    }

    public void onClickExit(View view) {
        finish();
    }
}

