package android.parkingator;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 3407189 on 24/02/16.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameViewThread th;
    GameApplication app;
    int ch, cw;
    Integer pieceselect = null;
    int tempx, tempy;
    int xdebut, ydebut;
    int deltaX, deltaY;
    int min, max;
    float largeurpiece;

    Context context = getContext();

    Bitmap car0;
    Bitmap H2;
    Bitmap H3;
    Bitmap V2;
    Bitmap V3;
    Bitmap Background;
    Bitmap car0move;
    //Bitmap car0win;
    Bitmap H2move;
    Bitmap H3move;
    Bitmap V2move;
    Bitmap V3move;

    Rect dst = new Rect();
    Rect dmt = new Rect();
    Rect bck = new Rect();


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        app = (GameApplication) (context.getApplicationContext());
        try {
            AssetManager assetManager = context.getAssets();
            InputStream voiture0 = assetManager.open("car0.png");
            car0 = BitmapFactory.decodeStream(voiture0);
            InputStream voitureH2 = assetManager.open("carH2.png");
            H2 = BitmapFactory.decodeStream(voitureH2);
            InputStream voitureH3 = assetManager.open("optimus.png");
            H3 = BitmapFactory.decodeStream(voitureH3);
            InputStream voitureV2 = assetManager.open("carV2.png");
            V2 = BitmapFactory.decodeStream(voitureV2);
            InputStream voitureV3 = assetManager.open("carV3.png");
            V3 = BitmapFactory.decodeStream(voitureV3);
            InputStream parking = assetManager.open("background.png");
            Background = BitmapFactory.decodeStream(parking);
            InputStream voiture0move = assetManager.open("car0move.png");
            car0move = BitmapFactory.decodeStream(voiture0move);
            InputStream voitureH2move = assetManager.open("carH2move.png");
            H2move = BitmapFactory.decodeStream(voitureH2move);
            InputStream voitureH3move = assetManager.open("optimusmove.png");
            H3move = BitmapFactory.decodeStream(voitureH3move);
            InputStream voitureV2move = assetManager.open("carV2move.png");
            V2move = BitmapFactory.decodeStream(voitureV2move);
            InputStream voitureV3move = assetManager.open("carV3move.png");
            V3move = BitmapFactory.decodeStream(voitureV3move);


            voiture0.close();
            voiture0move.close();
            voitureH2.close();
            voitureH2move.close();
            voitureH3.close();
            voitureH3move.close();
            voitureV2.close();
            voitureH2move.close();
            voitureV3.close();
            voitureV3move.close();

            parking.close();

        } catch (IOException e) {
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int size;
        if (widthMode == MeasureSpec.EXACTLY && widthSize > 0) {
            size = widthSize;
        } else if (heightMode == MeasureSpec.EXACTLY && heightSize > 0) {
            size = heightSize;
        } else {
            size = widthSize < heightSize ? widthSize : heightSize;
        }

        int finalMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        super.onMeasure(finalMeasureSpec, finalMeasureSpec);
    }

    @Override
    public void surfaceCreated(SurfaceHolder h) {
        th = new GameViewThread(getHolder(), this);
        th.setRunning(true);
        th.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder sh, int f, int w, int h) {
        ch = h;
        cw = w;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder h) {
        boolean retry = true;
        th.setRunning(false);
        while (retry) {
            try {
                th.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }


    @Override
    public void draw(Canvas c) {
        super.draw(c);
        bck.set(0, 0, c.getHeight(), c.getWidth());
        c.drawBitmap(Background, null, bck, null);
        largeurpiece = c.getHeight() / 6;

        Piece tmpPiece;
        int i = 0;
        int left;
        int top;
        int right;
        int bottom;

        //Dessin des Pieces
        while (app.getGame().getTabPiece(i) != null) {
            if ((Integer) (app.getGame().getTabPiece(i).getId()) != pieceselect) {
                tmpPiece = app.getGame().getTabPiece(i);
                left = (int) (largeurpiece) * tmpPiece.getPos().getCol();
                top = (int) (largeurpiece) * tmpPiece.getPos().getLig();

                if (tmpPiece.getOrientation() == Direction.HORIZONTAL) {
                    right = (int) (largeurpiece) * tmpPiece.getSize() + (int) (largeurpiece) * tmpPiece.getPos().getCol();
                    bottom = (int) (largeurpiece) + (int) (largeurpiece) * tmpPiece.getPos().getLig();

                    if (tmpPiece.getId() == 0) {
                        dst.set(left, top, right, bottom);
                        c.drawBitmap(car0, null, dst, null);
                    } else {
                        if (tmpPiece.getSize() == 2) {
                            dst.set(left, top, right, bottom);
                            c.drawBitmap(H2, null, dst, null);
                        } else {
                            dst.set(left, top, right, bottom);
                            c.drawBitmap(H3, null, dst, null);
                        }
                    }
                } else {
                    right = (int) (largeurpiece) + (int) (largeurpiece) * tmpPiece.getPos().getCol();
                    bottom = (int) (largeurpiece) * tmpPiece.getSize() + (int) (largeurpiece) * tmpPiece.getPos().getLig();

                    if (tmpPiece.getSize() == 2) {
                        dst.set(left, top, right, bottom);
                        c.drawBitmap(V2, null, dst, null);
                    } else {
                        dst.set(left, top, right, bottom);
                        c.drawBitmap(V3, null, dst, null);
                    }
                }
            }
            i++;
        }

        //Dessin des Pieces en Mouvement
        if (pieceselect != null) {
            int col = app.getGame().getCol(pieceselect);
            int lig = app.getGame().getLig(pieceselect);
            int len = app.getGame().getLen(pieceselect);
            Direction dir = app.getGame().getOrientation(pieceselect);

            int largeurint = (int) largeurpiece;

            if (dir == Direction.HORIZONTAL) {
                if (len == 2) {
                    if (((col * largeurint) - deltaX) < min * largeurint) {
                        dmt.set(min * largeurint, lig * largeurint, (min + 2) * largeurint, (lig + 1) * largeurint);
                        if (pieceselect == 0) {
                            c.drawBitmap(car0move, null, dmt, null);
                        } else {
                            c.drawBitmap(H2move, null, dmt, null);
                        }

                    } else if ((((col + 2) * largeurpiece) - deltaX) > max * largeurpiece) {
                        dmt.set((max - 2) * largeurint, lig * largeurint, ((max) * largeurint), (lig + 1) * largeurint);

                        if (pieceselect == 0) {
                            c.drawBitmap(car0move, null, dmt, null);
                        } else {
                            c.drawBitmap(H2move, null, dmt, null);
                        }

                    } else {
                        dmt.set((col * largeurint) - deltaX, lig * largeurint, ((col + 2) * largeurint) - deltaX, (lig + 1) * largeurint);
                        if (pieceselect == 0) {
                            c.drawBitmap(car0move, null, dmt, null);
                        } else {
                            c.drawBitmap(H2move, null, dmt, null);
                        }
                    }

                } else {
                    if (((col * largeurpiece) - deltaX) < min * largeurpiece) {
                        dmt.set(min * largeurint, lig * largeurint, (min + 3) * largeurint, (lig + 1) * largeurint);
                        c.drawBitmap(H3move, null, dmt, null);
                    } else if ((((col + 3) * largeurpiece) - deltaX) > max * largeurpiece) {
                        dmt.set((max - 3) * largeurint, lig * largeurint, ((max) * largeurint), (lig + 1) * largeurint);
                        c.drawBitmap(H3move, null, dmt, null);
                    } else {
                        dmt.set((col * largeurint) - deltaX, lig * largeurint, ((col + 3) * largeurint) - deltaX, (lig + 1) * largeurint);
                        c.drawBitmap(H3move, null, dmt, null);

                    }
                }

            } else {

                if (len == 2) {
                    if (((lig * largeurpiece) - deltaY) < min * largeurpiece) {
                        dmt.set(col * largeurint, min * largeurint, (col + 1) * largeurint, (min + 2) * largeurint);
                        c.drawBitmap(V2move, null, dmt, null);
                    } else if ((((lig + 2) * largeurpiece) - deltaY) > max * largeurpiece) {
                        dmt.set(col * largeurint, ((max - 2) * largeurint), (col + 1) * largeurint, max * largeurint);
                        c.drawBitmap(V2move, null, dmt, null);
                    } else {
                        dmt.set(col * largeurint, (lig * largeurint) - deltaY, (col + 1) * largeurint, ((lig + 2) * largeurint) - deltaY);
                        c.drawBitmap(V2move, null, dmt, null);
                    }

                } else {

                    if (((lig * largeurpiece) - deltaY) < min * largeurpiece) {
                        dmt.set(col * largeurint, min * largeurint, (col + 1) * largeurint, (min + 3) * largeurint);
                        c.drawBitmap(V3move, null, dmt, null);
                    } else if ((((lig + 3) * largeurpiece) - deltaY) > max * largeurpiece) {
                        dmt.set(col * largeurint, ((max - 3) * largeurint), (col + 1) * largeurint, max * largeurint);
                        c.drawBitmap(V3move, null, dmt, null);
                    } else {
                        dmt.set(col * largeurint, (lig * largeurint) - deltaY, (col + 1) * largeurint, ((lig + 3) * largeurint) - deltaY);
                        c.drawBitmap(V3move, null, dmt, null);
                    }

                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pos_x = cw / 6;
        int pos_y = ch / 6;
        int x = (int) event.getX();
        int y = (int) event.getY();
        int i = x / pos_x;
        int j = y / pos_y;
        Position pos = new Position(i, j);

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                if (app.getGame().getIdByPos(pos) != null) {
                    int id = app.getGame().getIdByPos(pos);
                    xdebut = (int) event.getX();
                    ydebut = (int) event.getY();
                    pieceselect = id;
                    Direction dir = app.getGame().getOrientation(pieceselect);
                    int len = app.getGame().getLen(pieceselect);
                    min = 0;
                    max = 6;
                    if (dir == Direction.HORIZONTAL) {
                        for (int t = 0; t < 6; t++) {
                            Position tmpPos = new Position(t, app.getGame().getLig(pieceselect));
                            if (app.getGame().getIdByPos(tmpPos) != null) {
                                if (app.getGame().getIdByPos(tmpPos) == pieceselect) {
                                    break;
                                }
                                min = tmpPos.getCol() + 1;

                            }
                        }
                        if (app.getGame().getCol(pieceselect) + len == 6) {
                            max = 6;
                        } else {
                            for (int t = app.getGame().getCol(pieceselect) + len; t < 6; t++) {
                                Position tmpPos = new Position(t, app.getGame().getLig(pieceselect));
                                if (app.getGame().getIdByPos(tmpPos) != null) {
                                    max = tmpPos.getCol();
                                    break;
                                }
                            }
                        }
                    } else {
                        for (int t = 0; t < 6; t++) {
                            Position tmpPos = new Position(app.getGame().getCol(pieceselect), t);
                            if (app.getGame().getIdByPos(tmpPos) != null) {
                                if (app.getGame().getIdByPos(tmpPos) == pieceselect) {
                                    break;
                                }
                                min = tmpPos.getLig() + 1;

                            }
                        }
                        if (app.getGame().getLig(pieceselect) + len == 6) {
                            max = 6;
                        } else {
                            for (int t = app.getGame().getLig(pieceselect) + len; t < 6; t++) {
                                Position tmpPos = new Position(app.getGame().getCol(pieceselect), t);
                                if (app.getGame().getIdByPos(tmpPos) != null) {
                                    max = tmpPos.getLig();
                                    break;
                                }
                            }
                        }
                    }
                }
                return true;
            }

            case MotionEvent.ACTION_MOVE: {
                tempx = (int) event.getX();
                tempy = (int) event.getY();
                deltaX = (xdebut - tempx);
                deltaY = (ydebut - tempy);

                return true;
            }

            case MotionEvent.ACTION_UP: {

                int deltafin = 0;
                if (pieceselect != null) {
                    if (app.getGame().getOrientation(pieceselect) == Direction.HORIZONTAL) {
                        deltafin = (int) (deltaX / largeurpiece);
                        if (deltafin > 0) {
                            app.nbmove++;
                            for (int u = 0; u < deltafin; u++) {
                                app.getGame().moveBackward(pieceselect);
                            }
                        } else if (deltafin < 0) {
                            app.nbmove++;
                            for (int u = deltafin; u < 0; u++) {
                                app.getGame().moveForward(pieceselect);
                            }
                        }
                    } else {
                        deltafin = (int) (deltaY / largeurpiece);
                        if (deltafin > 0) {
                            app.nbmove++;
                            for (int u = 0; u < deltafin; u++) {
                                app.getGame().moveBackward(pieceselect);
                            }
                        } else if (deltafin < 0) {
                            app.nbmove++;
                            for (int u = deltafin; u < 0; u++) {
                                app.getGame().moveForward(pieceselect);
                            }
                        }
                    }
                }


                ((GameActivity) context).onWin();

                tempx = 0;
                tempy = 0;
                deltaX = 0;
                deltaY = 0;
                pieceselect = null;
                return true;
            }

            default:
                return true;
        }
    }
}