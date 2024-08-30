package org.silversix6.webchess.Game.Pieces;

import org.silversix6.webchess.Game.Board;
import org.silversix6.webchess.Game.Pair;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    boolean firstMove;

    public Pawn(int posX, int posY, boolean isWhite) {
        super(posX, posY, isWhite);
        firstMove = true;
    }

    @Override
    public List<Pair> getMoveSet(Board board) {
        List<Pair> moves = new ArrayList<>();
        int direction = isWhite ? 1 : -1;

        // move forward
        if (board.pieces[posX][posY+direction] == null) {
            moves.add(new Pair(posX, posY+direction));
        }

        // Move two tiles
        if (firstMove && board.pieces[posX][posY+2*direction] == null) {
            moves.add(new Pair(posX, posY+2*direction));
        }

        // Capture piece
        if (board.pieces[posX + direction][posY + direction].isWhite != isWhite) {
            moves.add(new Pair(posX + direction, posY + direction));
        }

        return moves;
    }

    @Override
    public String toString() {
        return "Pawn";
    }
}
