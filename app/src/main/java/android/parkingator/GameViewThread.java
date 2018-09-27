package android.parkingator;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by 3407189 on 24/02/16.
 */
public class GameViewThread extends Thread {
    SurfaceHolder holder;
    GameView view;
    boolean running = false;

    public GameViewThread(SurfaceHolder holder, GameView view) {
        this.holder = holder;
        this.view = view;
    }

    public void setRunning(boolean b) {
        this.running = b;
    }


    @Override
    public void run() {
        Canvas c;
        while (this.running) {
            c = holder.lockCanvas();
            if (c != null) {
                this.view.draw(c);
                this.holder.unlockCanvasAndPost(c);
            }
        }
    }
}
