package com.csc14.runtimeterrors.game.BoardAssets;

import java.util.*;

public enum BoardUtilities {
    INSTANCE;

    public final List<Boolean> FIRST_COLUMN = initColumn(0);
    public final List<Boolean> TWELFTH_COLUMN = initColumn(11);
    public final List<Boolean> TWELFTH_ROW = initRow(0);
    public final List<Boolean> ELEVENTH_ROW = initRow(12);
    public final List<Boolean> TENTH_ROW = initRow(24);
    public final List<Boolean> NINTH_ROW = initRow(36);
    public final List<Boolean> EIGHTH_ROW = initRow(48);
    public final List<Boolean> SEVENTH_ROW = initRow(60);
    public final List<Boolean> SIXTH_ROW = initRow(72);
    public final List<Boolean> FIFTH_ROW = initRow(84);
    public final List<Boolean> FOURTH_ROW = initRow(96);
    public final List<Boolean> THIRD_ROW = initRow(108);
    public final List<Boolean> SECOND_ROW = initRow(120);
    public final List<Boolean> FIRST_ROW = initRow(132);

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
}
