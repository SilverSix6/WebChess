package org.silversix6.webchess.Game.Pieces;

import org.silversix6.webchess.Game.Board;
import org.silversix6.webchess.Game.Pair;

import java.util.List;

public abstract class Piece {

    int posX;
    int posY;
    boolean isWhite;

    public Piece (int posX, int posY, boolean isWhite) {
        this.posX = posX;
        this.posY = posY;
        this.isWhite = isWhite;
    }

    abstract public List<Pair> getMoveSet(Board board);

    void dfsInDirection(Board board, int x, int y, Pair direction, List<Pair> pairs) {
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            return;
        }
        if (board.pieces[x][y] != null && board.pieces[x][y].isWhite == isWhite) {
            return;
        }
        if (board.pieces[x][y] != null && board.pieces[x][y].isWhite != isWhite) {
            pairs.add(new Pair(x, y));
            return;
        }

        pairs.add(new Pair(x, y));
        dfsInDirection(board, x + direction.x, y + direction.y, direction, pairs);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
}
