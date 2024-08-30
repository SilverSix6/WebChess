import {BoardFactory} from './BoardFactory.js';

document.addEventListener('DOMContentLoaded', function() {

    let board = new BoardFactory().board

    window.addEventListener('resize', board.update.bind(board))

})




