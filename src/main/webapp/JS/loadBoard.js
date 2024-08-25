import {Board} from './Board.js';

document.addEventListener('DOMContentLoaded', function() {

    let board = new Board()
    board.loadPieces()
    window.addEventListener('resize', board.update.bind(board))

})




