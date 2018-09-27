package android.parkingator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 3407189 on 02/03/16.
 */

public class LevelselectActivity extends AppCompatActivity {

    GameApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelmenu);

    }

    public void startGame(View button) {
        Intent ChooseIntent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();

        app = (GameApplication) this.getApplication();
        app.nbmove = 0;

        switch (button.getId()) {
            case R.id.button1:
                bundle.putInt("ActualXMLLevel", 0);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(0);
                break;
            case R.id.button2:
                bundle.putInt("ActualXMLLevel", 1);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(1);
                break;
            case R.id.button3:
                bundle.putInt("ActualXMLLevel", 2);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(2);
                break;
            case R.id.button4:
                bundle.putInt("ActualXMLLevel", 3);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(3);
                break;
            case R.id.button5:
                bundle.putInt("ActualXMLLevel", 4);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(4);
                break;
            case R.id.button6:
                bundle.putInt("ActualXMLLevel", 5);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(5);
                break;
            case R.id.button7:
                bundle.putInt("ActualXMLLevel", 6);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(6);
                break;
            case R.id.button8:
                bundle.putInt("ActualXMLLevel", 7);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(7);
                break;
            case R.id.button9:
                bundle.putInt("ActualXMLLevel", 8);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(8);
                break;
            case R.id.button10:
                bundle.putInt("ActualXMLLevel", 9);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(9);
                break;
            case R.id.button11:
                bundle.putInt("ActualXMLLevel", 10);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(10);
                break;
            case R.id.button12:
                bundle.putInt("ActualXMLLevel", 11);
                ChooseIntent.putExtras(bundle);
                startActivity(ChooseIntent);
                app.setPartie(11);
                break;
            default:
                break;
        }
    }

    public void onClickExit(View view) {
        finish();
    }
}
