package G56472.luckynumbers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Nasse
 */
public class TileTest {
    private Tile tile;
    private Tile tile1;
    @BeforeEach
    public void setUp() {
        tile = new Tile(5);
        tile1 = new Tile();
    }
    /**
     * test  of "flipFaceUp" when the attribut FaceUp is begin on false
     * tile is set on "false".
     */
    @Test
    public void filpFaceUpWhenFalseisTrue() {    
        tile1.flipFaceUp();
        boolean result = tile1.isFaceUp();
        boolean expected_result = true;
        assertEquals(result,expected_result);
    }
    /**
     * test  of "flipFaceUp" when the attribut FaceUp is begin on false
     * tile1 is set on "true".
     */
    @Test
    public void filpFaceUpWhenTrueIsTrue() {
        tile1.flipFaceUp();
        boolean result = tile1.isFaceUp();
        boolean expected_result = true;
        assertEquals(result,expected_result);
    }  
}
