import {Piece} from "./Piece.js";

export class Board {
    
    constructor() {
        this.pieces = []
        this.div = document.getElementById('board')
        this.board_width = this.div.clientWidth
        this.tile_width = this.board_width / 8
        this.initializeBoard()
        this.initializeTiles()
    }

    initializeBoard() {
        this.piecePos = [];

        for (let i = 0; i < 8; i++) {
            this.piecePos[i] = [];
            for (let j = 0; j < 8; j++) {
                this.piecePos[i][j] = null; // Initialize each cell with null or any default value
            }
        }
    }

    initializeTiles() {
        let whiteColor = false;
        // Row
        for (let x = 0; x < 8; x++) {
            let row = document.createElement('div')
            row.classList.add('row')

            // Tiles
            for (let y = 0; y < 8; y++) {
                let col = document.createElement('div')
                col.classList.add('tile')

                // Alternate Colors
                if (whiteColor)
                    col.classList.add('white')
                else
                    col.classList.add('black')

                whiteColor = 1 - whiteColor
                row.append(col)
            }
            whiteColor = 1 - whiteColor
            this.div.append(row);
        }
    }

    loadPieces() {
        //
        //  Piece IDs:
        //  king    - 0
        //  queen   - 1
        //  knight  - 2
        //  bishop  - 3
        //  pawn    - 4
        //  rook    - 5
        //
        let frontLayout = [4, 4, 4, 4, 4, 4, 4, 4]
        let playerLayout = [5, 2, 3, 1, 0, 3, 2, 5]
        let opponentLayout = [5, 2, 3, 0, 1, 3, 2, 5]
        //
        //  Color IDs:
        //  white   - 0
        //  black   - 1
        //
        let color = 0;


        // Player Pieces
        this.addRow(color, playerLayout, 7)
        this.addRow(color, frontLayout, 6)

        color = 1 - color

        // Opponent
        this.addRow(color, frontLayout, 1)
        this.addRow(color, opponentLayout, 0)
    }

    addRow(color, layout, row) {
        let fileNames = ['King.svg', 'Queen.svg', 'Knight.svg', 'Bishop.svg', 'Pawn.svg', 'Rook.svg']
        let pieceColors = ['White_', 'Black_']

        let piecesDiv = document.getElementById('pieces')

        for (let i = 0; i < 8; i++) {
            let piece = new Piece(i, row, contextPath + '/ASSETS/Pieces/' + pieceColors[color] + fileNames[layout[i]])
            this.pieces.push(piece)
            this.piecePos[row][i] = piece
            piecesDiv.append(piece.toDiv())
        }

        this.update()
    }

    update() {
        let board_top = this.div.offsetTop - this.board_width / 2
        let board_left = this.div.offsetLeft - this.board_width / 2

        this.pieces.forEach(function (piece) {
            piece.moveToBoard(board_top, board_left, this.tile_width)
        }.bind(this))
    }

}