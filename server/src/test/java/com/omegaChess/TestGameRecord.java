package com.omegaChess;

import com.omegaChess.server.GameRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameRecord {

    @Test
    void testConstructor() {
        GameRecord game = new GameRecord("pers1", "pers2", 24, false);
        assertNotNull(game);
    }

    @Test
    void testGettersAndSetters() {
        String expectedWinner = "pers3";
        String expectedLoser = "pers4";
        int expectedMoves = 50;
        ArrayList<String> expectedPlayers = new ArrayList<>(Arrays.asList("pers3", "pers4"));


        GameRecord game = new GameRecord("pers1", "pers2", 24, false);

        game.setWinner(expectedWinner);
        assertEquals(expectedWinner, game.getWinner());
        game.setLoser(expectedLoser);
        assertEquals(expectedLoser, game.getLoser());
        game.setNumMoves(expectedMoves);
        assertEquals(expectedMoves, game.getNumMoves());
        game.setDraw(true);
        assertTrue(game.isDraw());
        game.setPlayers(expectedWinner, expectedLoser);
        assertEquals(expectedPlayers, game.getPlayers());
    }

}
