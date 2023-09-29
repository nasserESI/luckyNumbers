package G56472.luckynumbers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * this class implements model in order to use polymorphism. it means having the
 * same methods
 *
 * @author Nasse
 */
public class Game implements Model {

    private int playerCount;/*This attribut records the number of player*/
    private int currentPlayerNumber;/*This attribut records the number of the 
    current player.It means the player which is playing right now*/
    private State state;/*This attribut is related to the enumeration which record the state of the game*/
    private Board[] boards;/*this array(not an arraylist, arrays=="tableau" in french)
    is related to the classe Board and makes and instance of the class in each index of the array. 
    so that each player has a game board and also makes the same action on their instance.*/
    private Tile pickedTile;//this attribut record the last picked tile. 
    private int size;
    private Deck deck;

    /**
     * the Game is set to give a started point. it means somewhere to
     * begin.then, in the next step : the player can proprelly begin
     */
    public Game() {
        this.state = State.NOT_STARTED;
    }

    /**
     * Initialize a game.
     * <ul>
     * <li>An empty board is created for each player</li>
     * <li>Player number 0 starts the game</li>
     * <li>State becomes PICK_TILE</li>
     * </ul>;gj
     *
     * @param playerCount the number of players
     * @throws IllegalArgumentException if the number of players is not between
     * 2 and 4 (both included).
     * @throws IllegalStateException if called when state is not NOT_STARTED nor
     * GAME_OVER.
     */
    @Override
    public void start(int playerCount) {
        if (this.state != State.GAME_OVER && this.state != State.NOT_STARTED) {
            throw new IllegalStateException("you tried to reset the game");
        }
        if (playerCount < 2 || playerCount > 4) {
            throw new IllegalArgumentException(
                    " number of player too high or too low");
        }
        this.playerCount = playerCount;
        this.currentPlayerNumber = 0;
        this.deck = new Deck(playerCount);
        this.boards = new Board[this.playerCount];
        for (int i = 0; i < boards.length; i++) {
            boards[i] = new Board();
        }
        boardGameArena(playerCount);
        this.state = State.PICK_TILE;
    }

    private ArrayList<Tile> initDiagonal(int playerCount) {
        ArrayList<Tile> diag = new ArrayList<>();
        for (int i = 0; i <= (playerCount * 4); i++) {
            diag.add(this.deck.pickFaceDown());
        }
        return diag;
    }

    private void boardGameArena(int playerCount) {
        if (this.state != State.GAME_OVER && this.state != State.NOT_STARTED) {
            throw new IllegalStateException("you tried to reset the game");
        }
        ArrayList<Tile> diagonale = initDiagonal(playerCount);
        sortingDiagonal(diagonale);
        int x = 0;
        for (int joueur = 0; joueur < playerCount; joueur++) {
            for (int i = 0; i <= this.boards[joueur].getSize() - 1; i++) {
                this.boards[joueur].put(diagonale.get(x), new Position(i, i));
                x++;
            }
        }
    }

    private void sortingDiagonal(ArrayList<Tile> starter_pack) {
        for (int x = 0; x < starter_pack.size(); x++) {
            for (int i = 0; i < starter_pack.size() - 1; i++) {
                if (starter_pack.get(i).getValue() > starter_pack.get(i + 1).getValue()) {
                    Collections.swap(starter_pack, i, i + 1);
                }
            }
        }
    }

    /**
     * Give the size of the boards. We suppose that all boards are squares and
     * of the same size. So this is both number of lines and number of columns.
     * With the official rules, this should be 4 but this must not be assumed
     * and this methode must be used instead of hardcoding that value elsewhere
     * in the code.
     *
     * @return the size of the board.
     */
    @Override
    public int getBoardSize() {
        return boards[this.currentPlayerNumber].getSize();
    }

    /**
     * The current player pick a tile. In this version of the game, a random one
     * is created. State becomes PLACE_TILE.
     *
     * @return the picked tile
     * @throws IllegalStateException if called when state is not PICK_TILE
     */
    /* @Override
    public Tile pickTile() {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException(
                    "it's not the moment to get a tile");
        }
        this.pickedTile = new Tile((int) (Math.random() * 20));
        this.state = State.PLACE_TILE;
        return this.pickedTile;
    }*/
    /**
     * The current player pick a tile. In this version of the game, a random one
     * is created. State becomes PLACE_TILE.
     *
     * @return the picked tile
     * @throws IllegalStateException if called when state is not PICK_TILE
     */
    Tile pickTile(int valeur) {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException(
                    "it's not the moment to get a tile");
        }
        this.pickedTile = new Tile(valeur);
        this.state = State.PLACE_TILE;
        return this.pickedTile;
    }

    @Override
    public Tile pickFaceDownTile() {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException(
                    "it's not the moment to get a tile");
        }
        this.pickedTile = this.deck.pickFaceDown();
        this.state = State.PLACE_OR_DROP_TILE;
        return this.pickedTile;
    }

    @Override
    public void pickFaceUpTile(Tile tile) {
        if (state != State.PICK_TILE) {
            throw new IllegalStateException(
                    "it's not the moment to get a tile");
        }
        this.pickedTile = tile;
        this.deck.pickFaceUp(tile);
        this.state = State.PLACE_TILE;
    }

    /**
     * Put a tile at the given position. Put the previously picked tile of the
     * current player at the given position on its board. State becomes
     * TURN_END.
     *
     * @param pos where to put the tile.
     * @throws IllegalArgumentException if the tile can't be put on that
     * position (position outside of the board or position not allowed by the
     * rules)
     * @throws IllegalStateException if called when state is not PUT_TILE
     */
    @Override
    public void putTile(Position pos) {
        if (state != State.PLACE_TILE && state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("wrong moment to put a tile ....");
        }
        if (!canTileBePut(pos)) {
            throw new IllegalArgumentException("it might not be possible to "
                    + "put a tile");
        }
        if (this.boards[this.currentPlayerNumber].getTile(pos) != null) {
            this.deck.putBack(this.boards[this.currentPlayerNumber].getTile(pos));
        }
        this.boards[this.currentPlayerNumber].put(pickedTile, pos);
        if (this.boards[this.currentPlayerNumber].isFull() || (this.faceDownTileCount() == 0 && this.faceUpTileCount() == 0)) {
            this.state = State.GAME_OVER;
        } else {
            this.state = State.TURN_END;
        }
    }

    /**
     * the same methode as putTile(Position pos),but only for testing purpose
     * this methode doesn't check if the tile can be put
     *
     * @param pos position.
     */
    public void putTileTest(Position pos) {

        if (state != State.PLACE_TILE && state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("wrong moment to put a tile ....");
        }

        /*if (!canTileBePut(pos)) {
            throw new IllegalArgumentException("it might not be possible to "
                    + "put a tile");
        }*/
        if (this.boards[this.currentPlayerNumber].getTile(pos) != null) {
            this.deck.putBack(this.boards[this.currentPlayerNumber].getTile(pos));
        }
        this.boards[this.currentPlayerNumber].put(pickedTile, pos);
        if (this.boards[this.currentPlayerNumber].isFull() || this.faceDownTileCount() == 0) {
            this.state = State.GAME_OVER;
        } else {
            this.state = State.TURN_END;
        }
    }

    /**
     * Change current player. The next player becomes the current one. The order
     * is : 0, 1, 2, 3 and again 0, 1, ... State becomes PICK_TILE
     *
     * @throws IllegalStateException if called when state is not TURN_END
     */
    @Override
    public void nextPlayer() {
        if (getState() != State.TURN_END) {
            throw new IllegalStateException(
                    "it's not time to let someone else playing");
        }
        if (((this.currentPlayerNumber + 1) % this.playerCount) == 0) {
            this.currentPlayerNumber = 0;
        } else {
            this.currentPlayerNumber++;
        }
        state = State.PICK_TILE;
    }

    /**
     * Give the total number of players in this game.
     *
     * @return the total number of players in this game.
     * @throws IllegalArgumentException if state is NOT_STARTED
     */
    @Override
    public int getPlayerCount() {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException(
                    " the game hasn't started yet...");
        }
        return this.playerCount;
    }

    @Override
    public State getState() {
        return this.state;
    }

    /**
     * Give the number of the current player. Players are numeroted from 0 to
     * (count-1).
     *
     * @return the number of the current player.
     * @throws IllegalArgumentException if state is NOT_STARTED or GAME_OVER
     */
    @Override
    public int getCurrentPlayerNumber() {
        if (state == State.NOT_STARTED || state == State.GAME_OVER) {
            throw new IllegalStateException(
                    "the game hasn't began yet or has already finished");
        }
        return this.currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (this.state != State.PLACE_TILE && this.state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("wrong moment to get a tile");
        }
        return this.pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        return boards[this.currentPlayerNumber].isInside(pos);
    }

    /**
     * Check if a tile can be put at the given position. Check if the current
     * player is allowed to put its previously picked tile at the given position
     * on the board of the current player.
     *
     * @param pos the position to check
     * @return true if the picked tile can be put at that position.
     * @throws IllegalArgumentException if the position is outside the board.
     * @throws IllegalStateException if state is not PLACE_TILE.
     */
    @Override
    public boolean canTileBePut(Position pos) {
        if (state != State.PLACE_TILE && this.state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("not the moment to put a tile");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException(" out of edge ");
        }

        return boards[this.currentPlayerNumber].canBePut(getPickedTile(), pos);
    }

    /**
     * Give a tile at a given position of the board of a given player.
     *
     * @param playerNumber the player number
     * @param pos a position on the board
     * @return the tile at that position for that player or <code>null</code> if
     * there is no tile there.
     * @throws IllegalArgumentException if the position is outside the board or
     * if the playerNUmber is ouside of range.
     * @throws IllegalStateException if game state is NOT_STARTED
     */
    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException(
                    " we don't have any cards to look at ....");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("player or position doesn't "
                    + "seemt to be présent");
        }
        return boards[playerNumber].getTile(pos);
    }

    /**
     * Give the winner.
     *
     * @return the number of the winner.
     * @throws IllegalStateException if game state is not GAME_OVER
     */
    @Override
    public List<Integer> getWinners() {
        if (this.state != State.GAME_OVER) {
            throw new IllegalStateException("the game is'nt finished yet");
        }
        ArrayList<Integer> classement = new ArrayList<Integer>();
        if (this.boards[this.currentPlayerNumber].isFull() == true) {
            classement.add(this.currentPlayerNumber);
            return classement;
        } else {
            ArrayList participant = new ArrayList<Integer>();
            for (int x = 0; x < this.getPlayerCount(); x++) {
                int compteur = 0;
                for (int j = 0; j < this.boards[x].getSize(); j++) {
                    for (int k = 0; k < this.boards[x].getSize(); k++) {
                        if (this.boards[x].getTile(new Position(j, k)) != null) {
                            compteur++;
                        }
                    }
                }
                participant.add(compteur);
            }
            ArrayList gagnant = new ArrayList<Integer>();
            int max = (int) Collections.max(participant);
            for (int i = 0; i < participant.size(); i++) {
                if (max == (int) participant.get(i)) {
                    gagnant.add(i);
                }
            }
            return gagnant;
        }
    }

    @Override
    public void dropTile() {
        if (this.state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("you don't have any tile to drop ...");
        }
        this.deck.putBack(this.pickedTile);
        if (this.faceDownTileCount() == 0) {
            this.state = State.GAME_OVER;
        } else {
            this.state = State.TURN_END;
        }
    }

    @Override
    public int faceDownTileCount() {
        return this.deck.faceDownCount();
    }

    @Override
    public int faceUpTileCount() {
        return this.deck.faceUpCount();
    }

    @Override
    public List<Tile> getAllfaceUpTiles() {
        if (this.deck.getAllFaceUp() != null) {
            return Collections.unmodifiableList(this.deck.getAllFaceUp());
        } else {
            return null;
        }
    }
}
