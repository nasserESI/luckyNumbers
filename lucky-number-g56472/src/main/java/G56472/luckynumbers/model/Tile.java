package G56472.luckynumbers.model;

/**
 * this class is set to builds an object Tile
 *
 * @author Nasse
 */
public class Tile {
    //value is an integer which describe the number of the tile.
    private int value;
    //faceUp is a boolean which is used as a state : visible or hidden
    private boolean faceUp;
    /**
     * constructor of the tile class builds a tile object based only on a
     * Integer value.
     * @param value is the number which is written on a tile.
     */
    public Tile(int value) {
        this.value = value;
        this.faceUp = false;
    }
    /**
     * this constructor is set only for the purpose of testing.
     */
    public Tile() {
        this.faceUp =true;
    }
    /**
     *
     * @return show the value of the tile without giving the opportunity to edit
     * it.
     */
    public int getValue() {
        return value;
    }
    /**
     * this method shows if the tile is visible or not.
     * @return ture if the face is visible or false if it is hidden
     */
    public boolean isFaceUp() {
        return faceUp;
    }
    /**
     * this methode turn tile tile state from hidden to visible. and if it is already done,
     * it won't change anything.
     */
    public void flipFaceUp(){
             this.faceUp=true;
    }
}
