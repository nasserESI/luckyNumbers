package G56472.luckynumbers.model;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import G56472.luckynumbers.model.Deck;
import java.util.ArrayList;

public class DeckTest {

    private Deck paquet;

    @BeforeEach
    public void setUp() {
        paquet = new Deck(2);
    }

    @Test
    public void faceDownCount_when_full() {
        assertTrue(40 == paquet.faceDownCount());
    }

    @Test
    public void pickFaceDown_when_full() {
        paquet.pickFaceDown();
        assertEquals(39, paquet.faceDownCount());
    }

    @Test
    public void faceUpCount_when_start() {
        assertTrue(paquet.faceUpCount() == 0);
    }

    @Test
    public void getAllFaceUp() {
        assertFalse(paquet.hasFaceUp(paquet.pickFaceDown()));
    }
}
