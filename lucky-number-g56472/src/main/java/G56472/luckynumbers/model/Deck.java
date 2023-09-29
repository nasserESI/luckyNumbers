package G56472.luckynumbers.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author Nasse
 */
public class Deck {

    private ArrayList<Tile> deck;
    private ArrayList<Tile> visibleTile;

    public Deck(int playerCount) {
        /*int x = 1;*/
        deck = new ArrayList<Tile>();
        visibleTile = new ArrayList<Tile>();
        for (int j = 0; j < playerCount; j++) {
            for (int i = 1; i <= 20; i++) {
                deck.add(new Tile(i));
                /*x++;*/
            }
        }
        Collections.shuffle(deck);
        Collections.shuffle(deck);
    }
    /*donne le nombre de cartes face cachÃ©e dans la pioche*/
    public int faceDownCount() {
        return this.deck.size();
    }
    public Tile pickFaceDown() {
        Tile tile = this.deck.get(deck.size() - 1);
        this.deck.remove(this.deck.get(deck.size() - 1));
        return tile;
    }
    public int faceUpCount() {
        return this.visibleTile.size();
    }
    public List<Tile> getAllFaceUp() {
        return this.visibleTile;
    }
    /**
     * ask if a tile is hidden or not.
     *
     * @param tile : the tile which is question.
     * @return true if
     */
    public boolean hasFaceUp(Tile tile) {
        if (this.visibleTile.contains(tile)) {
            return true;
        } else {
            return false;
        }
    }
    /*
     *pick a chosen visible tile 
     */
    public void pickFaceUp(Tile tile) {
        this.visibleTile.remove(tile);
    }
    public ArrayList<Tile> getDeck() {
        return deck;
    }
    /*
    *put back the specified tile
     */
    public void putBack(Tile tile) {
        this.visibleTile.add(tile);
        int i = this.visibleTile.indexOf(tile);
        this.visibleTile.get(i).flipFaceUp();
    }
}
