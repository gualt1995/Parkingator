package android.parkingator;

public class Position implements IPosition {
    private int ncol;
    private int nlig;

    public Position(int ncol, int nlig) {
        this.ncol = ncol;
        this.nlig = nlig;
    }

    public int getCol() {
        return ncol;
    }

    public int getLig() {
        return nlig;
    }

    public void setPos(int i, int j) {
        this.ncol = i;
        this.nlig = j;
    }

    public Position addCol(int d) {
        return new Position(this.ncol + d, this.nlig);
    }

    public Position remCol(int d) {
        return new Position(this.ncol - d, this.nlig);
    }

    public Position addLig(int d) {
        return new Position(this.ncol, this.nlig + d);
    }

    public Position remLig(int d) {
        return new Position(this.ncol, this.nlig - d);
    }
}