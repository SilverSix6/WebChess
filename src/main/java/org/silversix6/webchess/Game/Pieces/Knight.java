package org.silversix6.webchess.Game.Pieces;

import org.silversix6.webchess.Game.Board;
import org.silversix6.webchess.Game.Pair;

import java.util.List;

public class Knight extends Piece {
    public Knight(int posX, int posY, boolean isWhite) {
        super(posX, posY, isWhite);
    }

    @Override
    public List<Pair> getMoveSet(Board board) {
        return List.of();
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
