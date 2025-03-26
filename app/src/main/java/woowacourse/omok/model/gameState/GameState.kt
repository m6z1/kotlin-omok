package woowacourse.omok.model.gameState

import woowacourse.omok.model.Stone
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.position.Position

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
