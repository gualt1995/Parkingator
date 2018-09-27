package android.parkingator;

public interface IPosition {

    int getCol();

    int getLig();

    Position addCol(int d);

    Position addLig(int d);

}
