package G56472.luckynumbers.model;

/**
 * this class is the one which build a game board and its behaviour
 *
 * @author Nasser
 *
 */
public class Board {

    private Tile[][] tiles;
    private static final int size = 4;

    /**
     * this is the constructor. its purpose is to set Ã  game board sized 4x4;
     */
    public Board() {
        tiles = new Tile[size][size];
    }

    /**
     * this method is used in order to put a tile in a given position in the two
     * dimension array
     *
     * @param tile the tile is the value which should be put somewhere in the
     * game board and which is given to the user
     * @param pos the position
     */
    public void put(Tile tile, Position pos) {
        tiles[pos.getRow()][pos.getColumn()] = tile;
    }

    /**
     * this method has been splited in two privates ones in order to keep it
     * simple and readable it contain:before_and_after() and over_and_below()
     *
     * @param tile is the tile that the program get from the user.
     * @param pos is the position which has been chosen by the user as well
     * @return the method is supposed to say if the tile fit in a position in
     * the board.
     */
    public boolean canBePut(Tile tile, Position pos) {
        //return before_and_after(tile, pos) && over_and_below(tile, pos);
        return ultimateCantBePut(tile, pos);
    }

    private boolean ultimateCantBePut(Tile tile, Position pos) {
        for (int i = 0; i < getSize(); i++) {
            if (i < pos.getColumn() || i < pos.getRow()) {
                if (getTile(new Position(pos.getRow(), i)) != null && getTile(new Position(i, pos.getColumn())) != null) {
                    if (!(getTile(new Position(pos.getRow(), i)).getValue() < tile.getValue())) {
                        return false;
                    }
                    if (!(getTile(new Position(i, pos.getColumn())).getValue() < tile.getValue())) {
                        return false;
                    }
                }
            } else if (i >= (pos.getColumn() + 1) || i >= (pos.getRow() + 1)) {
                if (getTile(new Position(i, pos.getColumn())) != null && getTile(new Position(pos.getRow(), i)) != null) {
                    if (!(tile.getValue() < getTile(new Position(i, pos.getColumn())).getValue())) {
                        return false;
                    }
                    if (!(tile.getValue() < getTile(new Position(pos.getRow(), i)).getValue())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * this private method is set to check if all the elements is sorted by
     * growth vertically and horizontally
     *
     * @param tile is the object tile which has an entier value
     * @param pos is the position of the tile
     * @return true
     */
    private boolean before_and_after(Tile tile, Position pos) {
        /*this loop check the growth of the elements before the supposed position*/
        for (int j = 0; j < pos.getColumn(); j++) {
            if (getTile(new Position(pos.getRow(), j)) != null) {
                if (!(getTile(new Position(pos.getRow(), j)).getValue() < tile.getValue())) {
                    return false;
                }
            }
        }
        /*this loop check the growth of the row after the supposed position*/
        for (int j = pos.getColumn() + 1; j < getSize(); j++) {
            if (getTile(new Position(pos.getRow(), j)) != null) {
                if (!(tile.getValue() < getTile(new Position(pos.getRow(), j)).getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * those parameter below come from the method "canBePut"
     *
     * @param tile this is the tile that we try to know if it fit in the board.
     * @param pos this is the chosen position.
     * @return true if the column is growing from the top to the bottom without
     * equalities and decrasing values
     */
    private boolean over_and_below(Tile tile, Position pos) {
        /*this loop check the growth of the elements above the supposed position*/
        for (int j = 0; j < pos.getRow(); j++) {
            if (getTile(new Position(j, pos.getColumn())) != null) {
                if (!(getTile(new Position(j, pos.getColumn())).getValue() < tile.getValue())) {
                    return false;
                }
            }
        }
        /*this loop check the growth of the elements below the supposed position*/
        for (int j = pos.getRow() + 1; j < getSize(); j++) {
            if (getTile(new Position(j, pos.getColumn())) != null) {
                if (!(tile.getValue() < getTile(new Position(j, pos.getColumn())).getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param pos pos is the position in the game board.
     * @return this method indicate if the position fit in the game board or if
     * we are out of the edges of the board
     */
    public boolean isInside(Position pos) {
        return !(pos.getRow() < 0 || pos.getRow() > getSize() - 1
                || pos.getColumn() < 0 || pos.getColumn() > getSize() - 1);
    }

    /**
     * this method gives the size of the game board initialized as an attribut
     * of the object Board
     *
     * @return an integer which represent the length of the length and the width
     * of the game board.
     */
    public int getSize() {
        return this.tiles.length;
    }

    /**
     * this method says if the game board is full or not. it returns true if it
     * is the case or false in the other case
     *
     * @return return true if the game board is full or false for the opposite
     * case.
     */
    public boolean isFull() {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                if (tiles[x][y] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this method is set to get a chosen tile.
     *
     * @param pos pos means position which is the one selected so that we can
     * find wanted tile
     * @return the method return the selected tile.
     */
    public Tile getTile(Position pos) {
        return tiles[pos.getRow()][pos.getColumn()];
    }
}
