package android.parkingator;


public interface IPiece {

    public int getId();

    public Direction getOrientation();

    public int getSize();

    public Position getPos();

    public void setPos(Position pos);

}
