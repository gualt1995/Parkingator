package android.parkingator;

public class Piece implements IPiece {
    private int id;
    private int size;
    private Direction dir;
    private Position pos;

    public Piece(int id, int size, Direction dir, int ncol, int nlig) {
        this.id = id;
        this.size = size;
        this.dir = dir;
        pos = new Position(ncol, nlig);
    }

    public int getId() {
        return id;
    }

    public Direction getOrientation() {
        return dir;
    }

    public int getSize() {
        return size;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
    
