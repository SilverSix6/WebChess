package org.silversix6.webchess.Game;

import org.silversix6.webchess.Game.Pieces.*;

public class BoardFactory {

    public static Board getGameBoard() {
        Board board = new Board();

        initializePieces(board);

        return board;
    }

    private static void initializePieces(Board board) {
        for (int i = 0; i < 8; i++) {
            board.pieces[1][i] = new Pawn(1, i, false);
            board.pieces[6][i] = new Pawn(6, i, true);
            board.pieceList.add(board.pieces[1][i]);
            board.pieceList.add(board.pieces[6][i]);
        }
        
        board.pieces[7][7] = new Rook(7, 7, true);
        board.pieces[7][6] = new Knight(6, 7, true);
        board.pieces[7][5] = new Bishop(5, 7, true);
        board.pieces[7][4] = new King(4, 7, true);
        board.pieces[7][3] = new Queen(3, 7, true);
        board.pieces[7][2] = new Bishop(2, 7, true);
        board.pieces[7][1] = new Knight(1, 7, true);
        board.pieces[7][0] = new Rook(0, 7, true);

        board.pieces[0][7] = new Rook(7, 0, false);
        board.pieces[0][6] = new Knight(6, 0, false);
        board.pieces[0][5] = new Bishop(5, 0, false);
        board.pieces[0][4] = new Queen(4, 0, false);
        board.pieces[0][3] = new King(3, 0, false);
        board.pieces[0][2] = new Bishop(2, 0, false);
        board.pieces[0][1] = new Knight(1, 0, false);
        board.pieces[0][0] = new Rook(0, 0, false);

        for (int i = 0; i < 8; i++) {
            board.pieceList.add(board.pieces[7][i]);
            board.pieceList.add(board.pieces[0][i]);
        }
    }
}
