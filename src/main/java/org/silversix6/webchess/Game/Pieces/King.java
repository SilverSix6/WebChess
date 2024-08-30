package org.silversix6.webchess.Game.Pieces;

import org.silversix6.webchess.Game.Board;
import org.silversix6.webchess.Game.Pair;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(int posX, int posY, boolean isWhite) {
        super(posX, posY, isWhite);
    }

    @Override
    public List<Pair> getMoveSet(Board board) {
        List<Pair> moveSet = new ArrayList<Pair>();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0 || board.pieces[posX + x][posY + y].isWhite == isWhite) {
                    continue;
                }
                
                moveSet.add(new Pair(posX + x, posY + y));
            }
        }

        return moveSet;
    }

    @Override
    public String toString() {
        return "King";
    }
}
