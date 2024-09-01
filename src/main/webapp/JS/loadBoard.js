import {BoardFactory} from './BoardFactory.js';

let boardFactory;

document.addEventListener('DOMContentLoaded', function() {

    boardFactory = new BoardFactory()

    window.addEventListener('resize', boardFactory.board.update.bind(boardFactory.board))

})


export function getBoardFactory() {
    return boardFactory
}



