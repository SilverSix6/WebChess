import {PieceMoveManager} from "./PieceMoveManager.js";
import {User} from "./User.js";

export class Board {

    tiles;
    piecePos;
    pieces;
    highLightedTiles;
    pieceMoveManager;

    constructor() {
        this.pieces = []
        this.div = document.getElementById('board')
        this.board_width = this.div.clientWidth
        this.tile_width = this.board_width / 8

        this.pieceMoveManager = new PieceMoveManager()
    }

    movePiece(prevX, prevY, newX, newY) {
        // If player is black flip board
        console.log(prevX, prevY, newX, newY)

        if (User.color === 1) {
            prevX = 7 - prevX
            prevY = 7 - prevY
            newX = 7 - newX
            newY = 7 - newY
        }

        console.log(prevX, prevY, newX, newY)

        let piece = this.piecePos[prevY][prevX];
        //console.log(piece, this.piecePos)
        this.piecePos[newY][newX] = piece
        piece.posX = newX
        piece.posY = newY
        this.piecePos[prevY][prevX] = null

        piece.moveToBoard()
    }

    update() {
        this.pieces.forEach(function (piece) {
            piece.moveToBoard()
        }.bind(this))
    }

    boardTop() {
        return this.div.offsetTop - this.board_width / 2
    }

    boardLeft() {
        return this.div.offsetLeft - this.board_width / 2
    }

    highlightPrevious(x,y) {
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            this.tiles[y][x].classList.add('previousPosition')
            this.highLightedTiles.push([y,x])
        }

    }

    highlightPossible(x,y) {
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            this.tiles[y][x].classList.add('possiblePosition')
            this.highLightedTiles.push([y,x])
        }
    }

    clearHighlight() {
        for (let i = 0; i < this.highLightedTiles.length; i++) {
            let posX = this.highLightedTiles[i][1]
            let posY = this.highLightedTiles[i][0]
            this.tiles[posY][posX].classList.remove('possiblePosition')
            this.tiles[posY][posX].classList.remove('previousPosition')
        }
        this.highLightedTiles = []

    }

}