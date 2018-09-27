package android.parkingator;

import android.content.res.AssetManager;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.*;

import java.io.InputStream;

public class Model implements IModel {
    private Grid grid;
    private Piece piece[] = new Piece[15];

    public Model() {
        grid = new Grid();
        piece[0] = new Piece(0, 2, Direction.HORIZONTAL, 1, 2);
        piece[1] = new Piece(1, 2, Direction.HORIZONTAL, 0, 0);
        piece[2] = new Piece(2, 3, Direction.VERTICAL, 0, 1);
        piece[3] = new Piece(3, 2, Direction.VERTICAL, 0, 4);
        piece[4] = new Piece(4, 3, Direction.HORIZONTAL, 2, 5);
        piece[5] = new Piece(5, 3, Direction.VERTICAL, 3, 1);
        piece[6] = new Piece(6, 2, Direction.HORIZONTAL, 4, 4);
        piece[7] = new Piece(7, 3, Direction.VERTICAL, 5, 0);
        grid.set(piece[0].getPos(), 0);
        grid.set(piece[0].getPos().addCol(1), 0);
        grid.set(piece[1].getPos(), 1);
        grid.set(piece[1].getPos().addCol(1), 1);
        grid.set(piece[2].getPos(), 2);
        grid.set(piece[2].getPos().addLig(1), 2);
        grid.set(piece[2].getPos().addLig(2), 2);
        grid.set(piece[3].getPos(), 3);
        grid.set(piece[3].getPos().addLig(1), 3);
        grid.set(piece[4].getPos(), 4);
        grid.set(piece[4].getPos().addCol(1), 4);
        grid.set(piece[4].getPos().addCol(2), 4);
        grid.set(piece[5].getPos(), 5);
        grid.set(piece[5].getPos().addLig(1), 5);
        grid.set(piece[5].getPos().addLig(2), 5);
        grid.set(piece[6].getPos(), 6);
        grid.set(piece[6].getPos().addCol(1), 6);
        grid.set(piece[7].getPos(), 7);
        grid.set(piece[7].getPos().addLig(1), 7);
        grid.set(piece[7].getPos().addLig(2), 7);

    }


    /*public Document readXMLFile(String fname) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(fname);
        } catch (Exception ex) {
            return null;

        }
    }*/

    public Document readXMLFile(InputStream fname) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(fname);
        } catch (Exception ex) {
            return null;

        }
    }

    public Piece getTabPiece(int i){
        return piece[i];
    }


    public Model(int i, GameApplication app) {
        grid = new Grid();
        String fname = "rushpuzzle.xml";
        AssetManager ass = app.getAssets();
        try {
            Document document = readXMLFile(ass.open(fname));
            Element racine = document.getDocumentElement();
            NodeList racineNoeuds = racine.getChildNodes();
            Node noeud = racineNoeuds.item(i);
            NodeList pieceNoeuds = noeud.getChildNodes();
            for (int j = 0; j < pieceNoeuds.getLength(); j++) {
                String LEN = pieceNoeuds.item(j).getAttributes().getNamedItem("len").getNodeValue();
                String DIR = pieceNoeuds.item(j).getAttributes().getNamedItem("dir").getNodeValue();
                String COL = pieceNoeuds.item(j).getAttributes().getNamedItem("col").getNodeValue();
                String LIG = pieceNoeuds.item(j).getAttributes().getNamedItem("lig").getNodeValue();
                if (DIR.equals("H")) {
                    piece[j] = new Piece(j, Integer.parseInt(LEN), Direction.HORIZONTAL, Integer.parseInt(COL), Integer.parseInt(LIG));
                    if (Integer.parseInt(LEN) == 2) {
                        grid.set(piece[j].getPos(), j);
                        grid.set(piece[j].getPos().addCol(1), j);
                    } else {
                        grid.set(piece[j].getPos(), j);
                        grid.set(piece[j].getPos().addCol(1), j);
                        grid.set(piece[j].getPos().addCol(2), j);
                    }
                } else {
                    piece[j] = new Piece(j, Integer.parseInt(LEN), Direction.VERTICAL, Integer.parseInt(COL), Integer.parseInt(LIG));
                    if (Integer.parseInt(LEN) == 2) {
                        grid.set(piece[j].getPos(), j);
                        grid.set(piece[j].getPos().addLig(1), j);
                    } else {
                        grid.set(piece[j].getPos(), j);
                        grid.set(piece[j].getPos().addLig(1), j);
                        grid.set(piece[j].getPos().addLig(2), j);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public Integer getIdByPos(Position pos) {
        if (!grid.isEmpty(pos)) {
            return grid.get(pos);
        } else {
            return null;
        }
    }

    public Direction getOrientation(int id) {
        return piece[id].getOrientation();
    }

    public Integer getLig(int id) {
        return piece[id].getPos().getLig();
    }

    public Integer getCol(int id) {
        return piece[id].getPos().getCol();
    }

    public int getLen(int id) {
        return piece[id].getSize();
    }

    public boolean endOfGame(){
        if (piece[0].getPos().getCol() == 4) {
            return true;
        } else {
            return false;
        }
    }

    public void moveForward(int id) {

        if (getOrientation(id) == Direction.HORIZONTAL) {
            if (getLen(id) == 2) {
                if (piece[id].getPos().getCol() != 4) {
                    if (grid.isEmpty(piece[id].getPos().addCol(2))) {
                        grid.unset(piece[id].getPos());
                        grid.set(piece[id].getPos().addCol(2), id);
                        piece[id].setPos((piece[id].getPos().addCol(1)));
                    }
                }
            } else {
                if (piece[id].getPos().getCol() != 3) {
                    if (grid.isEmpty(piece[id].getPos().addCol(3))) {
                        grid.unset(piece[id].getPos());
                        grid.set(piece[id].getPos().addCol(3), id);
                        piece[id].setPos((piece[id].getPos().addCol(1)));
                    }
                }
            }
        } else {
            if (getLen(id) == 2) {
                if (piece[id].getPos().getLig() != 4) {
                    if (grid.isEmpty(piece[id].getPos().addLig(2))) {
                        grid.unset(piece[id].getPos());
                        grid.set(piece[id].getPos().addLig(2), id);
                        piece[id].setPos((piece[id].getPos().addLig(1)));
                    }
                }
            } else {
                if (piece[id].getPos().getLig() != 3) {
                    if (grid.isEmpty(piece[id].getPos().addLig(3))) {
                        grid.unset(piece[id].getPos());
                        grid.set(piece[id].getPos().addLig(3), id);
                        piece[id].setPos((piece[id].getPos().addLig(1)));
                    }
                }
            }
        }
    }

    public void moveBackward(int id) {

        if (getOrientation(id) == Direction.HORIZONTAL) {
            if (piece[id].getPos().getCol() != 0) {
                if (grid.isEmpty(piece[id].getPos().addCol(-1))) {
                    grid.unset(piece[id].getPos().addCol(getLen(id) - 1));
                    grid.set(piece[id].getPos().addCol(-1), id);
                    piece[id].setPos((piece[id].getPos().remCol(1)));
                }
            }
        } else {
            if (piece[id].getPos().getLig() != 0) {
                if (grid.isEmpty(piece[id].getPos().addLig(-1))) {
                    grid.unset(piece[id].getPos().addLig(getLen(id) - 1));
                    grid.set(piece[id].getPos().addLig(-1), id);
                    piece[id].setPos((piece[id].getPos().remLig(1)));
                }
            }
        }
    }

}
