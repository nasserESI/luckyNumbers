package G56472.luckynumbers.view;
import G56472.luckynumbers.model.*;
/**
 * this interface is set for any class which want to get the ability to display
 * @author Nasse
 */
public interface View {
    /**
     * this methode has as a purpose to display a heartfelt Welcome !
     * disigned with : the number of the current player and his game board
     * the author of the Java code. and the version
     */
    void displayWelcome();
    /**
     * this methode has as a purpose to display the current state of the game.
     * it means knowing :
     *  - who plays ?
     *  - his/her game board.
     *  - and the picked Tile.
     */
    void displayGame();
    /**
     * this methode has as a purpose to display the number of the winner
     */
    void displayWinner();
    /**
     * this methode has as a purpose to ask how many players are going to play.
     * @return it returns the number.
     */
    int askPlayerCount();
    /**
     * this methode ask the user if he/she wants to get a visible 
     * tile or a random hidden one.
     * @return a number 1 means a hidden card, 2 means a visible one.
     */
    int askUpOrDown();
    /**
     * this methode has as a purpose to ask the index of the row and the other 
     * one for the column.then it returns it as an Position object and makes 
     * sure that that the position is right.
     * @return a position
     */
    Position askPosition();
    /**
     * this methode has as a purpose to display a failure messagethat we get in 
     * parameter
     * @param message this message comes from model package which appear when 
     * a failure occur in the game.
     */
    void displayError(String message);
    /**
     * display all the visible tile and let the users selects hios/her tile.
     * @return : the chosen tile between all the visible ones.     
     */
    Tile whichVisibleTile();
    /**
     * this method ask ifthe user want to drop or put his tile in the board
     * @return the choice.1 means we put the tile, 2 means that we leave the 
     * tile on the table
     */
    int askPutOrDrop();


}
