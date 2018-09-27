package android.parkingator;

public class Grid implements IGrid {
    private Integer grid[][] = new Integer[7][7];

    public Grid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grid[i][j] = null;
            }
        }
    }

    public boolean isEmpty(Position pos) {
        if (grid[pos.getCol()][pos.getLig()] == null) {
            return true;
        } else {
            return false;
        }
    }

    public Integer get(Position pos) {
        return grid[pos.getCol()][pos.getLig()];
    }

    public void set(Position pos, int id) {
        grid[pos.getCol()][pos.getLig()] = id;
    }

    public void unset(Position pos) {
        grid[pos.getCol()][pos.getLig()] = null;
    }
}

    
    
