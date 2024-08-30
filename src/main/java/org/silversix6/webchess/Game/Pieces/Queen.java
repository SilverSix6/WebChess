package org.silversix6.webchess.Game.Pieces;

import org.silversix6.webchess.Game.Board;
import org.silversix6.webchess.Game.Pair;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(int posX, int posY, boolean isWhite) {
        super(posX, posY, isWhite);
    }

    @Override
    public List<Pair> getMoveSet(Board board) {
        List<Pair> moveSet = new ArrayList<Pair>();

        // Horizontal and Vertical
        dfsInDirection(board, posX, posY, new Pair(0,1), moveSet);
        dfsInDirection(board, posX, posY, new Pair(0,-1), moveSet);
        dfsInDirection(board, posX, posY, new Pair(1,0), moveSet);
        dfsInDirection(board, posX, posY, new Pair(-1,0), moveSet);

        // Diagonal
        dfsInDirection(board, posX, posY, new Pair(1,1), moveSet);
        dfsInDirection(board, posX, posY, new Pair(-1,-1), moveSet);
        dfsInDirection(board, posX, posY, new Pair(-1,1), moveSet);
        dfsInDirection(board, posX, posY, new Pair(1,-1), moveSet);

        return moveSet;
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
