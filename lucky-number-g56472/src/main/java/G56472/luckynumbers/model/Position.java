package G56472.luckynumbers.model;

/**
 *the class Position is set to built an object position which is essential 
 * for a movement purpose
 * @author Nasse
 */

public class Position {
    // row is the position from the top to the bottom
    private int row;
    //column is the position from left to right
    private int column;

    /**
     * 
     * those parameter are set in order to give the position of each tile with
     * is mandatory to know which position the user chose both are attribut of
     * the object which means what the object is based on. the position of a
     * tile;
     * @param row The row coordinate of the position." 
     * @param column means : "colonne"
     *
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * this method is set in order to get the value of the row without changing
     * this one.
     *
     * @return it shows the value of the position in the row.
     */
    public int getRow() {
        return row;
    }

    /**
     * this method is set in order to get the value of the column without
     * changing this one.
     *
     * @return it shows the value of the position in the colunm
     */
    public int getColumn() {
        return column;
    }
}
