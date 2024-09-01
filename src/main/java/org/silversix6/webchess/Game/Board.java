package org.silversix6.webchess.Game;

import org.silversix6.webchess.Game.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public Piece[][] pieces;
    public List<Piece> pieceList;

    public Board() {
        pieces = new Piece[8][8];
        pieceList = new ArrayList<Piece>();
    }

    public void move(Move move) {
        // Swap piece position
        Piece piece = pieces[move.prevX][move.prevY];
        pieces[move.newX][move.newY] = piece;
        pieces[move.prevX][move.prevY] = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int x = 0; x < 8; x++) {
            sb.append("[");
            for (int y = 0; y < 8; y++) {
                if (pieces[x][y] != null) {
                    sb.append(pieces[x][y].toString());
                } else {
                    sb.append("null");
                }
                sb.append(", ");
            }
            sb.append("]\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
