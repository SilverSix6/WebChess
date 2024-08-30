export class PieceMoveManager {

    validMove(piece, newPosX, newPosY) {

        // in move set for given type
        let moveSet = this.getMoveSet(piece.type, piece.moved)
        for (let i = 0; i < moveSet.length; i++) {
            if (piece.posX + moveSet[i][0] * piece.direction === newPosX && piece.posY + moveSet[i][1] * piece.direction  === newPosY)
                return true
        }

        return false
    }

    getMoveSet(type, moved) {
        //
        //  Piece type IDs:
        //  king    - 0
        //  queen   - 1
        //  knight  - 2
        //  bishop  - 3
        //  pawn    - 4
        //  rook    - 5
        //
        switch (type) {
            case 0: // King
                return this.getKingMoveSet();
            case 1: // Queen
                return this.getQueenMoveSet();
            case 2: // Knight
                return this.getKnightMoveSet();
            case 3: // Bishop
                return this.getBishopMoveSet();
            case 4: // Pawn
                return this.getPawnMoveSet(moved);
            case 5: // Rook
                return this.getRookMoveSet();
        }
        return null
    }

    getKingMoveSet() {
        return [[1,0],[0,1],[1,1],[-1,0],[0,-1],[-1,-1],[1,-1],[-1,1]]
    }

    getQueenMoveSet() {
        return this.getRookMoveSet().concat(this.getBishopMoveSet())
    }

    getKnightMoveSet() {
        return [[2,1],[2,-1],[1,2],[1,-2],[-2,1],[-2,-1],[-1,2],[-1,-2]]
    }

    getBishopMoveSet() {
        let set = []
        for (let i = -7; i <= 7; i++) {
            if (i === 0)
                continue;
            set.push([i, i])
            set.push([-i, i])
        }
        return set
    }

    getPawnMoveSet(moved) {
        if (moved)
            return [[0,1]]
        return [[0,1],[0,2]]
    }

    getRookMoveSet() {
        let set = []
        for (let i = -7; i <= 7; i++) {
            if (i === 0)
                continue;
            set.push([i, 0])
            set.push([0, i])
        }
        return set
    }
}