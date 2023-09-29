package G56472.luckynumbers.model;

import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nasse
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /* =====================
         Tests for start()
       ===================== */

 /* --- test related to the state --- */
 /*    @Test
    public void start_when_game_not_started_ok() {
        game.start(4);
    }*/
    @Test
    public void start_when_game_over_ok() {
        fullPlay();
        game.start(2);
    }

    @Test
    public void start_when_game_in_progress_ISE() {
        game.start(4);
        assertThrows(IllegalStateException.class,
                () -> game.start(1));
    }

    @Test
    public void start_state_changed_to_PICK_TILE() {
        game.start(3);
        assertEquals(State.PICK_TILE, game.getState());
    }

    /* --- tests related to the parameter --- */
    @Test
    public void start_playerCount_too_small_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(1));
    }

    @Test
    public void start_playerCount_minimum_accepted() {
        game.start(2);
    }

    @Test
    public void start_playerCount_maximum_accepted() {
        game.start(4);
    }

    @Test
    public void start_playerCount_too_big_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(5));
    }

    /* -- tests related to fields initialization --- */
    @Test
    public void start_playerCount_initialized() {
        game.start(4);
        assertEquals(4, game.getPlayerCount());
    }

    @Test
    public void start_current_player_is_player_0() {
        game.start(4);
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    /* === Ã€ vous de complÃ©ter... === */
    @Test
    public void isInside_when_pos_too_high_supposed_Being_Error() {
        Position pos = new Position(-1, 1);
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalArgumentException.class,
                () -> game.canTileBePut(pos), " out of edge ");
    }

    @Test
    public void canTileBePut_when_pos_too_Low_supposed_Being_error() {
        Position pos = new Position(4, 1);
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalArgumentException.class,
                () -> game.canTileBePut(pos), " out of edge ");
    }

    @Test
    public void canTileBePut_when_pos_out_of_Edge_right_supposed_Being_error() {
        Position pos = new Position(2, 4);
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalArgumentException.class,
                () -> game.canTileBePut(pos), " out of edge ");
    }

    @Test
    public void canTileBePutTile_when_pos_out_of_Edge_left_supposed_Being_error() {
        Position pos = new Position(2, -1);
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalArgumentException.class,
                () -> game.canTileBePut(pos), " out of edge ");
    }

    @Test
    public void canTileBePutTile_when_pos_ok() {
        Position pos = new Position(2, 2);
        game.start(2);
        game.pickTile(5);
        assertTrue(game.canTileBePut(pos));
    }

    @Test
    public void getTile_when_index_player_out_Of_Bound_supposed_Being_error() {
        Position pos = new Position(8, 1);
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalArgumentException.class,
                () -> game.getTile(5, pos), "player or position doesn't "
                + "seemt to be present");
    }

    private void getTiletest(Position pos, int playerNumber) {
        game.start(4);
        game.pickTile(5);
        game.getTile(playerNumber, pos);
        game.putTile(pos);
        game.pickTile(6);
    }

    @Test
    public void getTile_when_position_of_the_tile_further_beyond_the_board() {
        Position pos = new Position(1, 10000);
        assertThrows(IllegalArgumentException.class,
                () -> getTiletest(pos, 3), "player or position doesn't "
                + "seemt to be prÃ©sent");
    }

    @Test
    public void getTile_when_notstarted_supposed_being_error() {
        Position pos = new Position(1, 1);
        assertEquals(State.NOT_STARTED, game.getState());
        assertThrows(IllegalStateException.class,
                () -> game.getTile(1, pos),
                " we don't have any cards to look at ....");
    }

    @Test
    public void PickFaceDownTile_when_notstarted_supposed_being_error() {
        Game game = new Game();
        assertEquals(State.NOT_STARTED, game.getState());
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile(), "it's not the moment to get a tile");
    }

    @Test
    public void nextPlayerTest_when_notstarted_supposed_being_error() {
        assertEquals(State.NOT_STARTED, game.getState());
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer(),
                "it's not time to let someone else playing");
    }

    @Test
    public void getPlayerCountTest_when_notstarted_supposed_being_error() {
        assertEquals(State.NOT_STARTED, game.getState());
        assertThrows(IllegalStateException.class,
                () -> game.getPlayerCount(), " the game hasn't started yet...");
    }

    @Test
    public void getCurrentPlayerNumber_moment_is_notstarted_supposed_error() {
        assertEquals(State.NOT_STARTED, game.getState());
        assertThrows(IllegalStateException.class,
                () -> game.getCurrentPlayerNumber(),
                "the game hasn't began yet or has already finished");
    }

    @Test
    public void canTileBePut_when_notstarted_supposed_being_error() {
        Position pos = new Position(1, 1);
        assertEquals(State.NOT_STARTED, game.getState());
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(pos), "there are no cards in there");
    }

    @Test
    public void getWinners_when_notstarted_supposed_being_error() {
        assertEquals(State.NOT_STARTED, game.getState());
        assertThrows(IllegalStateException.class,
                () -> game.getWinners(), "the game is'nt finished yet");
    }

    @Test
    public void getTile_when_PICK_TILE_supposed_being_ok() {
        Position pos = new Position(1, 1);
        game.start(2);
        game.getTile(1, pos);
    }

    @Test
    public void PickFaceDownTile_when_PICK_TILE_supposed_being_ok() {
        game.start(2);
        assertDoesNotThrow(() -> game.pickFaceDownTile());
    }

    @Test
    public void nextPlayerTest_when_PICK_TILE_supposed_being_error() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer(),
                "it's not time to let someone else playing");
    }

    @Test
    public void getPlayerCountTest_when_PICK_TILE_supposed_being_ok() {
        game.start(2);
        assertDoesNotThrow(() -> game.getPlayerCount());
    }

    @Test
    public void getCurrentPlayerNumber_when_PICK_TILE_supposed_being_ok() {
        game.start(2);
        assertDoesNotThrow(() -> game.getCurrentPlayerNumber());
    }

    @Test
    public void canTileBePut_when_PICK_TILE_supposed_being_error() {
        Position pos = new Position(1, 1);
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(pos), "not the moment to put a tile");
    }

    @Test
    public void getWinner_when_PICK_TILE_supposed_Being_error() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.getWinners(), "the game is'nt finished yet");
    }

    @Test
    public void getTile_when_PLACE_TILEsupposed_Being_Ok() {
        game.start(2);
        game.pickTile(5);
        assertDoesNotThrow(() -> game.getPlayerCount());
    }

    @Test
    public void PickFaceDownTile_when_PLACE_OR_DROP_TILE_supposed_Being_error() {
        game.start(2);
        game.pickFaceDownTile();
        assertTrue(State.PLACE_OR_DROP_TILE == game.getState());
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile(), "it's not the moment to get a tile" + game.getState());
    }

    @Test
    public void nextPlayerTest_when_PLACE_TILE_supposed_Being_error() {
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer(),
                "it's not time to let someone else playing");
    }

    @Test
    public void getPlayerCountTest_when_PLACE_TILE_supposed_Being_ok() {
        game.start(2);
        game.pickTile(5);
        assertDoesNotThrow(() -> game.getPlayerCount());
    }

    @Test
    public void getCurrentPlayerNumber_when_PLACE_TILE_supposed_being_ok() {
        game.start(2);
        game.pickTile(5);
        assertDoesNotThrow(() -> game.getCurrentPlayerNumber());
    }

    @Test
    public void canTileBePut_when_pickFaceDownTile_supposed_being_ok() {
        game.start(2);
        game.pickFaceDownTile();
        assertEquals(State.PLACE_OR_DROP_TILE, game.getState());
    }

    @Test
    public void getWinner_when_PLACE_TILE_supposed_being_error() {
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalStateException.class,
                () -> game.getWinners(), "the game is'nt finished yet");
    }

    @Test
    public void getTile_when_TURN_END_supposed_being_ok() {
        Position pos = new Position(1, 1);
        game.start(2);
        game.pickTile(5);
        assertDoesNotThrow(() -> game.getTile(1, pos));
    }

    @Test
    public void PickFaceDownTile_when_TURN_END_supposed_being_error() {
        Position pos = new Position(1, 1);
        game.start(2);
        game.pickTile(5);
        game.putTile(pos);
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile(), "it's not the moment to get a tile");
    }

    @Test
    public void nextPlayerTest_when_TURN_END_supposed_being_false() {
        Position pos = new Position(1, 1);
        game.start(2);
        game.pickTile(5);
        game.putTile(pos);
        game.nextPlayer();
        assertFalse(State.TURN_END == game.getState());
    }

    @Test
    public void getPlayerCountTest_when_TURD_END_supposed_being_true() {
        Position pos = new Position(1, 1);
        game.start(2);
        game.pickTile(5);
        game.putTile(pos);
        game.getPlayerCount();
        assertTrue(State.TURN_END == game.getState());
    }

    @Test
    public void getCurrentPlayerNumber_when_TURN_END_supposed_being_true() {
        Position pos = new Position(1, 1);
        game.start(2);
        game.pickTile(5);
        game.putTile(pos);
        game.getCurrentPlayerNumber();
        assertTrue(State.TURN_END == game.getState());
    }

    @Test
    public void canTileBePut_when_TURN_END_supposed_being_error() {
        Position pos = new Position(1, 1);
        game.start(2);
        game.pickFaceDownTile();
        game.putTileTest(pos);
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(pos), "there are no cards in there");
    }

    @Test
    public void getWinners_when_TURN_END_supposed_being_error() {
        Position pos = new Position(1, 1);
        game.start(2);
        game.pickFaceDownTile();
        game.putTileTest(pos);
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.getWinners(), "the game is'nt finished yet");
    }

    @Test
    public void getTile_when_game_over_supposed_being_ok() {
        Position pos = new Position(1, 1);
        fullPlay();
        assertDoesNotThrow(() -> game.getTile(1, pos));
    }

    @Test
    public void PickFaceDownTile_when_GAME_OVER_supposed_being_error() {
        fullPlay();
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile(), "it's not the moment to get a tile");
    }

    @Test
    public void nextPlayerTest_when_GAME_OVER_supposed_being_error() {
        fullPlay();
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer(),
                "it's not time to let someone else playing");
    }

    @Test
    public void getPlayerCountTest_when_GAME_OVER_supposed_being_ok() {
        fullPlay();
        assertDoesNotThrow(() -> game.getPlayerCount());
    }

    @Test
    public void getCurrentPlayerNumber_when_GAME_OVER__supposed_being_error() {
        fullPlay();
        assertThrows(IllegalStateException.class,
                () -> game.getCurrentPlayerNumber(),
                "the game hasn't began yet or has already finished");
    }

    @Test
    public void canTileBePut_GAME_OVER_supposed_being_error() {
        Position pos = new Position(1, 1);
        fullPlay();
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(pos), "there are no cards in there");
    }

    @Test
    public void getWinners_when_GAME_OVER_supposed_being_ok() {
        fullPlay();
        game.getWinners();
    }

    /* Play a game till the end */
    private void fullPlay() {
        game.start(2);
        int value = 1;
        int line = 0;
        int col = 0;
        for (int turn = 1; turn < game.getBoardSize() * game.getBoardSize() - 1; turn++) {
            for (int player = 0; player < game.getPlayerCount(); player++) {
                assertTrue(player == game.getCurrentPlayerNumber());
                game.pickTile(value);
                game.putTileTest(new Position(line, col));
                game.nextPlayer();
            }
            value++;
            col++;
            if (col == game.getBoardSize()) {
                col = 0;
                line++;
            }
        }
        game.pickTile(20);
        game.putTileTest(new Position(line, col));
    }

    @Test
    public void getBoardSize_size_supposed_be_ok() {
        game.start(2);
        int size = game.getBoardSize();
        assertTrue(4 == size);
    }

    @Test
    public void getBoardSize_size_supposed_be_false() {
        game.start(2);
        int size = game.getBoardSize();
        assertFalse(5 == size);
    }

    @Test
    public void pickFaceDownTile_tile_supposed_be_ok() {
        game.start(2);
        Tile tile = game.pickFaceDownTile();
        assertEquals(game.getPickedTile().getValue(), tile.getValue());

    }

    @Test
    public void pickFaceDownTile_supposed_be_false() {
        game.start(2);
        Tile tile = game.pickFaceDownTile();
        assertFalse(game.getPickedTile().getValue() != tile.getValue());
    }

    @Test
    public void Put_supposed_being_ok() {
        game.start(2);
        game.pickTile(5);
        Position pos = new Position(0, 0);
        game.putTile(pos);
        assertEquals(5, game.getTile(0, pos).getValue());
    }

    @Test
    public void Put_supposed_being_false() {
        game.start(2);
        game.pickTile(5);
        Position pos = new Position(0, 0);
        game.putTile(pos);
        assertFalse(4 == game.getTile(0, pos).getValue());
    }

    @Test
    public void getCurrentPlayerNumber_supposed_being_ok() {
        fullPlay();
    }

    @Test
    public void isInside_supposed_being_ok() {
        game.start(2);
        assertTrue(game.isInside(new Position(0, 3)));
        assertTrue(game.isInside(new Position(0, 0)));
        assertTrue(game.isInside(new Position(3, 0)));
        assertTrue(game.isInside(new Position(3, 3)));
    }

    @Test
    public void pickDownDrop_normalCase() {
        game.start(2);
        game.pickFaceDownTile();
        assertEquals(0, game.faceUpTileCount());
        game.dropTile();
        assertEquals(1, game.faceUpTileCount());
    }

    @Test
    public void pickDown_when_TURN_END_supposed_Being_False() {
        game.start(2);
        game.pickFaceDownTile();
        assertEquals(0, game.faceUpTileCount());
        game.dropTile();
        assertEquals(1, game.faceUpTileCount());
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile(), "it's not the moment to get a tile");
    }

    @Test
    public void DropTile_when_PlaceOrDrop_supposed_being_ok() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        assertEquals(1, game.faceUpTileCount());
    }

    @Test
    public void boardGameArena_insideStart_32_tiles_at_the_beginning_if_2_players() {
        game.start(2);
        assertTrue(31 == game.faceDownTileCount());
    }

    @Test
    public void boardGameArena_insideStart_48_tiles_at_the_beginning_if_3_players() {
        game.start(3);
        assertTrue(47 == game.faceDownTileCount());
    }

    @Test
    public void boardGameArena_insideStart_64_tiles_at_the_beginning_if_4_players() {
        game.start(4);
        assertTrue(63 == game.faceDownTileCount());
    }

    @Test
    public void boardGameArena_diagonale_HasNt_empty_case() {
        game.start(2);
        for (int x = 0; x < game.getPlayerCount(); x++) {
            for (int i = 0; i < game.getBoardSize(); i++) {
                Tile tile = game.getTile(x, new Position(i, i));
                assertTrue(tile != null);
            }
        }
    }

    @Test
    public void boardGameArena_when_NOT_STARTED_supposed_Being_ok() {
        fullPlay();
        game.start(2);
    }

    @Test
    public void boardGameArena_byItself_when_PICK_TILE_supposed_Being_Error() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.start(2), "you tried to reset the game");
    }

    @Test
    public void boardGameArena_byItself_when_PLACE_TILE_supposed_Being_Error() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        game.pickFaceUpTile(game.getAllfaceUpTiles().get(0));
        assertThrows(IllegalStateException.class,
                () -> game.start(2), "you tried to reset the game");
    }

    @Test
    public void boardGameArena_byItself_when_PLACE_OR_DROP_TILE_supposed_Being_Error() {
        game.start(2);
        game.pickFaceDownTile();
        assertThrows(IllegalStateException.class,
                () -> game.start(2), "you tried to reset the game");
    }

    @Test
    public void boardGameArena_byItself_when_TURN_END_supposed_Being_Error() {
        game.start(2);
        game.pickFaceDownTile();
        game.putTileTest(new Position(1, 1));
        assertThrows(IllegalStateException.class,
                () -> game.start(2), "you tried to reset the game");
    }

    @Test
    public void boardGameArena_insideStart_when_GAME_OVER_supposed_ok() {
        game.start(2);
        assertTrue(sorted_or_not() == true);
    }

    private boolean sorted_or_not() {
        for (int i = 0; i < game.getPlayerCount(); i++) {
            for (int j = 0; j < game.getBoardSize() - 1; j++) {
                if (game.getTile(i, new Position(j, j)).getValue() > game.getTile(i, new Position(j + 1, j + 1)).getValue()) {
                    return false;
                }
            }
        }
        return true;
    }
    @Test
    public void DropTile_when_NOT_STARTED_supposed_being_error(){
    assertTrue(State.NOT_STARTED == game.getState());
    assertThrows(IllegalStateException.class,
                () -> game.dropTile(), "you don't have any tile to drop ...");
    }
    @Test
    public void DropTile_when_pickTile_supposed_being_error(){
        game.start(2);
    assertThrows(IllegalStateException.class,
                () -> game.dropTile(), "you don't have any tile to drop ...");
    }
    @Test
    public void DropTile_when_PLACE_TILE_supposed_being_error(){
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        game.pickFaceUpTile(game.getAllfaceUpTiles().get(0));
        assertThrows(IllegalStateException.class,
                () -> game.dropTile(), "you don't have any tile to drop ...");
    }
    @Test
    public void DropTile_when_PLACE_OR_DROP_TILE_supposed_being_error(){
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
    }
    @Test
    public void DropTile_when_TURN_END_supposed_being_error(){
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.dropTile(), "you don't have any tile to drop ...");
    }
    @Test
    public void DropTile_when_GAME_OVER_supposed_being_error(){
        fullPlay();
        assertThrows(IllegalStateException.class,
                () -> game.dropTile(), "you don't have any tile to drop ...");
    }
    @Test
    public void getPickedTile_when_NOT_STARTED_supposed_being_error(){
        assertTrue(State.NOT_STARTED == game.getState());
        assertThrows(IllegalStateException.class,
                () -> game.getPickedTile(), "wrong moment to get a tile");
    }
    @Test
    public void getPickedTile_when_PICK_TILE_supposed_being_error(){
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.getPickedTile(), "wrong moment to get a tile");
    }
    @Test
    public void getPickedTile_when_PLACE_TILE_supposed_being_error(){
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        game.pickFaceUpTile(game.getAllfaceUpTiles().get(0));
        game.getPickedTile();
    }
    @Test
    public void getPickedTile_when_PLACE_OR_DROP_TILE_supposed_being_error(){
        game.start(2);
        game.pickFaceDownTile();
        game.getPickedTile();
    }
    @Test
    public void getPickedTile_when_TURN_END_supposed_being_error(){
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.getPickedTile(), "wrong moment to get a tile");
    }
    @Test
    public void getPickedTile_when_GAME_OVER_supposed_being_error(){
        fullPlay();
        assertThrows(IllegalStateException.class,
                () -> game.getPickedTile(), "wrong moment to get a tile");
        
    }
    @Test
    public void start_when_PICK_TILE_supposedBeing_Error() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.start(2), "you tried to reset the game");
    }
    @Test
    public void start_when_PLACE_OR_DROP_TILE_supposedBeing_Error() {
        game.start(2);
        game.pickFaceDownTile();
        assertThrows(IllegalStateException.class,
                () -> game.start(2), "you tried to reset the game");
    }
    @Test
    public void start_when_TURN_END_supposedBeing_Error() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.start(2), "you tried to reset the game");
    }
    @Test
    public void start_when_GAME_OVER_supposedBeing_ok() {
        fullPlay();
        game.start(2);
    }
}