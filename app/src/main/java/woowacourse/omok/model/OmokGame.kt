package woowacourse.omok.model

import woowacourse.omok.model.board.Board
import woowacourse.omok.model.gameState.BlackTurn
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.gameState.PutState
import woowacourse.omok.model.position.Position

class OmokGame(
    val board: Board,
) {
    var currentTurn: GameState = BlackTurn
        private set
    var lastPosition: Position? = null
        private set
    var lastTurn: GameState.Playing = BlackTurn
        private set

    fun getTurnState(position: Position): PutState {
        lastTurn = currentTurn as GameState.Playing

        return when (board.getPutState(position, lastTurn.stone)) {
            PutState.ExistStone -> PutState.ExistStone
            PutState.ForbiddenStone -> PutState.ForbiddenStone
            PutState.CanPutStone -> {
                currentTurn = lastTurn.play(board, position)
                updateLastPosition(position, lastTurn)
                PutState.CanPutStone
            }
        }
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

    fun isPlaying(): Boolean = currentTurn is GameState.Playing
}
