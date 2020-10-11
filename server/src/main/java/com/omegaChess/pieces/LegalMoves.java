package com.omegaChess.pieces;

import java.util.ArrayList;

public class LegalMoves {
    protected ArrayList<String> listOfMoves;
    protected boolean isEnPessant;

    public LegalMoves(ArrayList<String> moves, boolean enPessant) {
        this.listOfMoves = moves;
        this.isEnPessant = enPessant;
    }

    //only getters since they shouldn't change
    public ArrayList<String> getListOfMoves() {
        return this.listOfMoves;
    }

    public boolean isEnPessant() {
        return this.isEnPessant;
    }
}
