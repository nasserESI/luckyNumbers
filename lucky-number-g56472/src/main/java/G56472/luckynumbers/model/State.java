package G56472.luckynumbers.model;

import java.util.logging.Logger;

/**
 * this enumeration is set to follow each step of the game 
 *
 * @author Nasse
 */
public enum State {
     /**
      * NOT_STARTED : when the game hasn't started yet */
    NOT_STARTED,
    /**
     * PICK_TILE : when a player has to take a tile*/
    PICK_TILE,
    /**
     * PLACE_TILE : when a player must put his/her tile */
    PLACE_TILE,
    /**
     * PLACE_OR_DROP_TILE : when a player has to choose if he or she want to place or pick any tile*/
    PLACE_OR_DROP_TILE,
    /**
     * TURN_END : when it is not the player's turn anymore*/
    TURN_END,
    /**
     * GAME_OVER : when the is finished*/
    GAME_OVER;
    
}
