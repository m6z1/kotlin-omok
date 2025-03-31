package woowacourse.omok.model

import woowacourse.omok.model.board.Board
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.gameState.PutState
import woowacourse.omok.model.position.Position

class OmokGame(
    val board: Board,
) {
    var currentState: GameState = GameState.Playing(Stone.BLACK)
        private set
    var lastPosition: Position? = null
        private set
    var lastPlaying: GameState.Playing = GameState.Playing(Stone.BLACK)
        private set

    fun setTurn(lastTurnStone: Stone) {
        lastPlaying =
            when (lastTurnStone) {
                Stone.BLACK -> GameState.Playing(Stone.WHITE)
                Stone.WHITE -> GameState.Playing(Stone.BLACK)
            }
        currentState = lastPlaying
    }

    fun getPutState(position: Position): PutState {
        lastPlaying = currentState as GameState.Playing
        return board.getPutState(position, lastPlaying.stone)
    }

    fun putStone(position: Position) {
        currentState = board.put(position, lastPlaying.stone)
        updateLastPosition(position, lastPlaying)
    }

    private fun updateLastPosition(
        position: Position,
        lastPlaying: GameState.Playing,
    ) {
        lastPosition =
            if (forbiddenPosition(currentState, lastPlaying)) lastPosition else position
    }

    private fun forbiddenPosition(
        currentTurn: GameState,
        lastPlaying: GameState.Playing?,
    ): Boolean = currentTurn == lastPlaying

    fun isPlaying(): Boolean = currentState is GameState.Playing
}
