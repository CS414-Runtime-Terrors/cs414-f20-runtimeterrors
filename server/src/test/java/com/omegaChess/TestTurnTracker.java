package com.omegaChess;

import com.omegaChess.server.TurnTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTurnTracker {
    private final String p1 = "I'm Player 1";
    private final String p2 = "I'm Player 2";

    @Test
    public void testConstructor() {
        TurnTracker turn = new TurnTracker(p1, p2);
        assertEquals(p1, turn.getCurrentTurn());

        turn = new TurnTracker(p2, p1);
        assertEquals(p2, turn.getCurrentTurn());
    }

    @Test
    public void testSwitchTurn() {
        TurnTracker turn = new TurnTracker(p1, p2);
        turn.switchTurn();
        assertEquals(p2, turn.getCurrentTurn());

        turn = new TurnTracker(p2, p1);
        turn.switchTurn();
        assertEquals(p1, turn.getCurrentTurn());
    }
}
