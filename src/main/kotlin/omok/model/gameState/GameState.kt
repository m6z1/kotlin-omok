package omok.model.gameState

import omok.model.Stone
import omok.model.board.Board
import omok.model.position.Position

interface GameState {
    val stone: Stone

    interface Playing : GameState {
        fun play(
            board: Board,
            position: Position,
        ): GameState
    }

    data class Finish(
        override val stone: Stone,
    ) : GameState
}
