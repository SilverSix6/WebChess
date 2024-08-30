package org.silversix6.webchess.Game.Pieces;

import org.silversix6.webchess.Game.Board;

public class PieceManager {

    public boolean movePiece(Board board, Piece piece, int x, int y) {
        if (validMove(board, piece, x, y)) {
            board.pieces[piece.getPosX()][piece.getPosY()] = null;
            board.pieces[x][y] = piece;
            piece.setPosX(x);
            piece.setPosY(y);
            return true;
        } else {
            return false; // todo
        }
    }

    public boolean validMove(Board board, Piece piece, int posX, int posY) {

        return false; // todo
    }

}
