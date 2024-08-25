export class Piece{
    posX;
    posY;

    startX;
    startY;

    newX;
    newY;

    boundMouseMove;

    constructor(posX, posY, image) {
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

    moveToBoard(board_top, board_left, tile_width) {
        this.div.style.top = board_top + this.posY * tile_width + 'px'
        this.div.style.left = board_left + this.posX * tile_width + 'px'
    }

    mouseDown(e) {
        if (this.moving) {
            this.moving = false

            this.div.removeEventListener('mousemove', this.boundMouseMove)


        } else {
            this.moving = true

            this.startX = e.clientX
            this.startY = e.clientY

            this.div.addEventListener('mousemove', this.boundMouseMove)
            this.div.addEventListener('mouseup', this.mouseUp.bind(this))
        }
    }

    mouseMove(e) {
        this.newX = this.startX - e.clientX
        this.newY = this.startY - e.clientY

        this.startX = e.clientX
        this.startY = e.clientY

        this.div.style.top = (this.div.offsetTop - this.newY) + 'px'
        this.div.style.left = (this.div.offsetLeft - this.newX) + 'px'
    }

    mouseUp(e) {
        this.div.removeEventListener('mousemove', this.mouseMove.bind(this))
    }
}