package omok.model.state

import omok.model.Board
import omok.model.Position
import omok.model.Stone

interface GameState {
    val stone: Stone

    interface Playing : GameState {
        fun play(
            board: Board,
            position: Position,
        ): GameState
    }

    enum class Finish(
        override val stone: Stone,
    ) : GameState {
        BLACK_WIN(Stone.BLACK),
        WHITE_WIN(Stone.WHITE),
    }
}
