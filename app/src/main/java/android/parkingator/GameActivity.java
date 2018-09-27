package android.parkingator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    GameApplication app;
    Intent intentExtras;
    int actualLevel;
    int nextLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        intentExtras = getIntent();
        app = (GameApplication) this.getApplication();
        actualLevel = intentExtras.getIntExtra("ActualXMLLevel", 0);
        nextLevel = actualLevel + 1;
        onWin();
    }

    public void onWin() {
        if (app.getGame().endOfGame()) {
            AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
            myAlert.setMessage("Well Played :D\n\nYou finished the level in "+ app.nbmove +" moves.")
                    .setIcon(R.drawable.diamond)
                    .setTitle("Level " + nextLevel + " Accomplished")
                    .setNeutralButton("Select Level", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("Ok..", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            if (actualLevel != 11) {

                myAlert.setPositiveButton("Next Level", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        app.nbmove = 0;
                        app.setPartie(nextLevel);
                        nextLevel++;

                    }
                })
                        .create();

                myAlert.show();

            } else {
                myAlert.create()
                        .show();
            }
        }
    }

    public void onClickExit(View view) {
        finish();
    }
}
