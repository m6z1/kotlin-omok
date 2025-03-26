package woowacourse.omok.model

import woowacourse.omok.model.board.Board
import woowacourse.omok.model.gameState.BlackTurn
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.position.Position

class OmokGame(
    val board: Board,
) {
    var currentTurn: GameState = BlackTurn
        private set
    var lastPosition: Position? = null
        private set

    fun getTurnState(position: Position): GameState {
        val lastTurn: GameState.Playing = currentTurn as GameState.Playing
        currentTurn = lastTurn.play(board, position)
        updateLastPosition(position, lastTurn)
        return currentTurn
    }

    private fun updateLastPosition(
        position: Position,
        lastTurn: GameState.Playing,
    ) {
        lastPosition = if (forbiddenPosition(currentTurn, lastTurn)) lastPosition else position
    }

    private fun forbiddenPosition(
        currentTurn: GameState,
        lastTurn: GameState.Playing?,
    ): Boolean = currentTurn == lastTurn

    fun forbiddenPosition(currentTurn: GameState): Boolean = forbiddenPosition(currentTurn, this.currentTurn as? GameState.Playing)
}
