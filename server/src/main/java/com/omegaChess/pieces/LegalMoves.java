package com.omegaChess.pieces;

import java.util.ArrayList;

public class LegalMoves {
    protected ArrayList<String> listOfMoves;
    protected boolean isEnPessant;
    protected boolean isCastle;

    public LegalMoves(ArrayList<String> moves, boolean enPessant, boolean castle) {
        this.listOfMoves = moves;
        this.isEnPessant = enPessant;
        this.isCastle = castle;
    }

    //only getters since they shouldn't change
    public ArrayList<String> getListOfMoves() {
        return this.listOfMoves;
    }

    public boolean isEnPessant() {
        return this.isEnPessant;
    }
    public boolean isCastle() { return this.isCastle; }
}
