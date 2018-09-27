package android.parkingator;

import android.app.Application;

/**
 * Created by 3407189 on 24/02/16.
 */
public class GameApplication extends Application {
    private Model theGame;
    public int i;
    public int nbmove = 0;

    public Model getGame() {
        return theGame;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        theGame = new Model(i, this);
        nbmove = 0;
    }

    public void setPartie(int part) {
        this.i = part;
        theGame = new Model(i, this);
    }

}