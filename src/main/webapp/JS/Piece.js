import {User} from "./User.js";
import {MessageHandler} from "./MessageHandler.js";

export class Piece{
    type;
    color;
    moved;

    posX;
    posY;

    startX;
    startY;

    boundMouseMove;

    //
    //  Piece type IDs:
    //  king    - 0
    //  queen   - 1
    //  knight  - 2
    //  bishop  - 3
    //  pawn    - 4
    //  rook    - 5
    //

    constructor(type, color, board, posX, posY, direction, image) {
        this.type = type
        this.color = color
        this.board = board
        this.posX = posX
        this.posY = posY
        this.direction = direction
        this.image = image
        this.moving = false;
        this.moved = false;
        this.boundMouseMove = this.mouseMove.bind(this)

        this.initializeDiv()
    }

    initializeDiv() {
        this.div = document.createElement('div')
        let imageElement =document.createElement('img')
        imageElement.src = this.image
        this.div.classList.add('piece')
        this.div.append(imageElement)
        if (this.color === User.color)
            this.div.addEventListener('mousedown', this.mouseDown.bind(this))
    }

    toDiv() {
        return this.div
    }

    moveToBoard() {
        this.div.style.top =  this.posTop() + 'px'
        this.div.style.left = this.posLeft() + 'px'
    }

    posTop() {
        return this.board.boardTop() + this.posY * this.board.tile_width
    }

    posLeft() {
        return this.board.boardLeft() + this.posX * this.board.tile_width
    }

    snapToBoard() {

        let newPosY = ((this.div.offsetTop - this.board.boardTop() + this.board.tile_width / 2) / this.board.tile_width ) | 0
        let newPosX = ((this.div.offsetLeft  - this.board.boardLeft() + this.board.tile_width / 2) / this.board.tile_width ) | 0
        if (newPosY >= 0 && newPosY < 8 && newPosX >= 0 && newPosX < 8 && this.board.pieceMoveManager.validMove(this, newPosX, newPosY)) {
            this.moved = true;
            this.newX = newPosX;
            this.newY = newPosY;

        } else {
            // position off game board
        }
    }

    mouseDown(e) {
        if (!User.turn)
            return

        if (this.moving) {
            this.moving = false
            User.turn = false

            this.div.classList.remove('selected')
            this.div.removeEventListener('mousemove', this.boundMouseMove)

            this.board.clearHighlight();
            this.snapToBoard();

            if ((this.newX !== undefined && this.newY !== undefined) && (this.posX !== this.newX || this.posY !== this.newY) ) {

                let move
                if (User.color === 0)
                    move = [this.posX, this.posY, this.newX, this.newY]
                else
                    move = [7 - this.posX, 7 - this.posY, 7 - this.newX, 7 - this.newY]

                let data = {
                    messageType: "2",
                    messages: move
                }

                MessageHandler.send(data)
            } else {
                User.turn = true
                this.moveToBoard()
            }

        } else {
            this.moving = true

            this.startX = e.clientX
            this.startY = e.clientY

            this.div.classList.add('selected')
            this.div.addEventListener('mousemove', this.boundMouseMove)
            this.div.addEventListener('mouseup', this.mouseUp.bind(this))

            let moveSet = this.board.pieceMoveManager.getMoveSet(this.type, this.moved)
            for (let i = 0; i < moveSet.length; i++) {
                this.board.highlightPossible(this.posX + moveSet[i][0] * this.direction, this.posY + moveSet[i][1] * this.direction)
            }

            this.board.highlightPrevious(this.posX, this.posY)
        }
    }

    mouseMove(e) {

        let deltaX = this.startX - e.clientX
        let deltaY = this.startY - e.clientY

        this.startX = e.clientX
        this.startY = e.clientY

        this.div.style.top = (this.div.offsetTop - deltaY) + 'px'
        this.div.style.left = (this.div.offsetLeft - deltaX) + 'px'
    }

    mouseUp(e) {
        this.div.removeEventListener('mousemove', this.mouseMove.bind(this))
    }

}