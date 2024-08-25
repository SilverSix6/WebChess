export class Piece{
    type;
    color;

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

    constructor(type, color, board, posX, posY, image) {
        this.type = type
        this.color = color
        this.board = board
        this.posX = posX
        this.posY = posY
        this.image = image
        this.moving = false;
        this.boundMouseMove = this.mouseMove.bind(this)

        this.initializeDiv()
    }

    initializeDiv() {
        this.div = document.createElement('div')
        let imageElement =document.createElement('img')
        imageElement.src = this.image
        this.div.classList.add('piece')
        this.div.append(imageElement)

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
        if (newPosY >= 0 && newPosY < 8 && newPosX >= 0 && newPosX < 8) {
            this.posY = newPosY;
            this.posX = newPosX;
        } else {
            // position off game board
        }

        this.moveToBoard()
    }

    mouseDown(e) {
        if (this.moving) {
            this.moving = false

            this.div.classList.remove('selected')
            this.div.removeEventListener('mousemove', this.boundMouseMove)

            this.board.unhighlightTile(this.posX, this.posY)
            this.snapToBoard();
        } else {
            this.moving = true

            this.startX = e.clientX
            this.startY = e.clientY

            this.div.classList.add('selected')
            this.div.addEventListener('mousemove', this.boundMouseMove)
            this.div.addEventListener('mouseup', this.mouseUp.bind(this))

            this.board.highlightTile(this.posX, this.posY)
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