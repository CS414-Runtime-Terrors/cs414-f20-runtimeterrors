package com.csc14.runtimeterrors.game.BoardAssets;

import java.util.*;

public enum BoardUtilities {
    INSTANCE;

    public final List<Boolean> FIRST_COLUMN = initColumn(0);
    public final List<Boolean> TWELFTH_COLUMN = initColumn(11);
    public final List<Boolean> FIRST_ROW = initRow(0);
    public final List<Boolean> SECOND_ROW = initRow(12);
    public final List<Boolean> THIRD_ROW = initRow(24);
    public final List<Boolean> FOURTH_ROW = initRow(36);
    public final List<Boolean> FIFTH_ROW = initRow(48);
    public final List<Boolean> SIXTH_ROW = initRow(60);
    public final List<Boolean> SEVENTH_ROW = initRow(72);
    public final List<Boolean> EIGHTH_ROW = initRow(84);
    public final List<Boolean> NINTH_ROW = initRow(96);
    public final List<Boolean> TENTH_ROW = initRow(108);
    public final List<Boolean> ELEVENTH_ROW = initRow(120);
    public final List<Boolean> TWELFTH_ROW = initRow(132);
    public final List<String> ALGEBRAIC_NOTATION = initializeAlgebraicNotation();
    public static final int START_TILE_INDEX = 0;
    public static final int NUM_TILES_PER_ROW = 12;
    public static final int NUM_TILES = 144;

    private static List<Boolean> initColumn(int columnNumber) {
        final Boolean[] column = new Boolean[NUM_TILES];
        Arrays.fill(column, false);
        do {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        } while(columnNumber < NUM_TILES);
        return Collections.unmodifiableList(Arrays.asList((column)));
    }

    private static List<Boolean> initRow(int rowNumber) {
        final Boolean[] row = new Boolean[NUM_TILES];
        Arrays.fill(row, false);
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while(rowNumber % NUM_TILES_PER_ROW != 0);
        return Collections.unmodifiableList(Arrays.asList(row));
    }

    private Map<String, Integer> initializePositionToCoordinateMap() {
        final Map<String, Integer> positionToCoordinate = new HashMap<>();
        for (int i = START_TILE_INDEX; i < NUM_TILES; i++) {
            positionToCoordinate.put(ALGEBRAIC_NOTATION.get(i), i);
        }
        return Collections.unmodifiableMap(positionToCoordinate);
    }

    private static List<String> initializeAlgebraicNotation() {
        return Collections.unmodifiableList(Arrays.asList(
                "w4", "", "", "", "", "", "", "", "", "", "", "w3",
                "", "a10", "b10", "c10", "d10", "e10", "f10", "g10", "h10", "i10", "j10", "",
                "", "a9", "b9", "c9", "d9", "e9", "f9", "g9", "h9", "i9", "j9", "",
                "", "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8", "h8", "i8", "j8", "",
                "", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7", "i7", "j7", "",
                "", "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6", "i6", "j6", "",
                "", "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5", "i5", "j5", "",
                "", "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4", "i4", "j4", "",
                "", "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3", "i3", "j3", "",
                "", "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2", "i2", "j2", "",
                "", "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1", "i1", "j1", "",
                "w1", "", "", "", "", "", "", "", "", "", "", "w2"));
    }
}
