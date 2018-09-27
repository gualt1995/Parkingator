package android.parkingator;

public interface IModel {

    public Integer getIdByPos(Position pos);

    public Direction getOrientation(int id);

    public Integer getLig(int id);

    public Integer getCol(int id);

    public boolean endOfGame();

    public void moveForward(int id);

    public void moveBackward(int id);

}
