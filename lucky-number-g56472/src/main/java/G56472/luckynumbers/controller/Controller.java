package G56472.luckynumbers.controller;
import G56472.luckynumbers.model.*;
import G56472.luckynumbers.view.View;
/**
 * the controller class is set in order to be able to control the game
 *
 * @author Nasse
 */
public class Controller {
    /*attribut set with its panel of methods for the display
    and the puts*/
    private View view;
    /* attribut set with its panel of methods for the rules
    and the autonomy of the game by changing the state in the methods*/
    private Model model;
    /**
     * controlling the game mean that we need to check and to act
     *
     * @param model : model is set for the actions
     * @param view : view is set for the output displayed on the screen
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    /**
     * This methode play the whole game as much as we need in order to get a
     * winner. the game stops when a winner has been found.
     */
    public void play() {
        view.displayWelcome();
        boolean wanToPlay=true;
        while (wanToPlay) {
            switch (model.getState()) {
                case NOT_STARTED:
                    int playcount = view.askPlayerCount();
                    model.start(playcount);
                    break;
                case PICK_TILE:
                    if (model.faceUpTileCount() != 0) {
                        if (view.askUpOrDown() == 1) {
                            model.pickFaceDownTile();
                        } else {
                            Tile tile = view.whichVisibleTile();
                            model.pickFaceUpTile(tile);
                        }
                    } else {
                        model.pickFaceDownTile();
                    }
                    break;        
                case PLACE_TILE:
                    view.displayGame();
                    model.getPickedTile();
                    Position pos = view.askPosition();
                    model.putTile(pos);
                    break;
                case PLACE_OR_DROP_TILE:
                    view.displayGame();
                    if(view.askPutOrDrop()==1){
                        model.putTile(view.askPosition());
                    }else{
                        model.dropTile();
                    }
                    break;
                case TURN_END:
                    model.nextPlayer();
                    break;
                case GAME_OVER:
                    view.displayWinner();
                    playcount = view.askPlayerCount();
                    model.start(playcount);
                    break;
            }
        }
    }

}
