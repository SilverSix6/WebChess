package org.silversix6.webchess.Game.Pieces;

import org.silversix6.webchess.Game.Board;
import org.silversix6.webchess.Game.Pair;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(int posX, int posY, boolean isWhite) {
        super(posX, posY, isWhite);
    }

    @Override
    public List<Pair> getMoveSet(Board board) {
        List<Pair> moveSet = new ArrayList<Pair>();

        dfsInDirection(board, posX, posY, new Pair(1,1), moveSet);
        dfsInDirection(board, posX, posY, new Pair(-1,-1), moveSet);
        dfsInDirection(board, posX, posY, new Pair(-1,1), moveSet);
        dfsInDirection(board, posX, posY, new Pair(1,-1), moveSet);

        return moveSet;
    }

    @Override
    public String toString() {
        return "Bishop";
    }
}
