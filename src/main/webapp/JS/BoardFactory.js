import {Board} from "./Board.js";
import {Piece} from "./Piece.js";

export class BoardFactory {

    constructor() {
        this.board = new Board()

        this.initializeBoard()
        this.initializeTiles()

        this.loadPieces()
    }

    initializeBoard() {
        this.board.piecePos = []
        this.board.tiles = []
        this.board.highLightedTiles = []

        for (let i = 0; i < 8; i++) {
            this.board.piecePos[i] = []
            this.board.tiles[i] = []

            for (let j = 0; j < 8; j++) {
                this.board.piecePos[i][j] = null // Initialize each cell with null or any default value
                this.board.tiles[i][j] = null
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
                let tile = document.createElement('div')
                tile.classList.add('tile')

                // Alternate Colors
                if (whiteColor)
                    tile.classList.add('white')
                else
                    tile.classList.add('black')

                whiteColor = 1 - whiteColor
                row.append(tile)
                this.board.tiles[x][y] = tile;
            }
            whiteColor = 1 - whiteColor
            this.board.div.append(row);
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
        let color = 1;

        // Player Pieces
        this.addRow(color, playerLayout, 7, -1)
        this.addRow(color, frontLayout, 6, -1)

        color = 1 - color

        // Opponent
        this.addRow(color, frontLayout, 1, 1)
        this.addRow(color, opponentLayout, 0, 1)
    }

    addRow(color, layout, row, direction) {
        let fileNames = ['King.svg', 'Queen.svg', 'Knight.svg', 'Bishop.svg', 'Pawn.svg', 'Rook.svg']
        let pieceColors = ['White_', 'Black_']

        let piecesDiv = document.getElementById('pieces')

        for (let i = 0; i < 8; i++) {
            let piece = new Piece(layout[i], color, this.board, i, row, direction, contextPath + '/ASSETS/Pieces/' + pieceColors[color] + fileNames[layout[i]])
            this.board.pieces.push(piece)
            this.board.piecePos[row][i] = piece
            piecesDiv.append(piece.toDiv())
        }

        this.board.update()
    }
}