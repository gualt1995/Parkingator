package android.parkingator;

public interface IGrid {

    public boolean isEmpty(Position pos);

    public Integer get(Position pos);

    public void set(Position pos, int id);

    public void unset(Position pos);

}
