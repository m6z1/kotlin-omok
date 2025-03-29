package woowacourse.omok.model

import woowacourse.omok.model.board.Board
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.gameState.PutState
import woowacourse.omok.model.position.Position

class OmokGame(
    val board: Board,
) {
    var currentTurn: GameState = GameState.Turn(Stone.BLACK)
        private set
    var lastPosition: Position? = null
        private set
    var lastTurn: GameState.Turn = GameState.Turn(Stone.BLACK)
        private set

    fun setTurn(lastTurnStone: Stone) {
        lastTurn =
            when (lastTurnStone) {
                Stone.BLACK -> GameState.Turn(Stone.WHITE)
                Stone.WHITE -> GameState.Turn(Stone.BLACK)
            }
        currentTurn = lastTurn
    }

    fun checkPutState(position: Position): PutState {
        lastTurn = currentTurn as GameState.Turn

        return when (val putState = board.getPutState(position, lastTurn.stone)) {
            PutState.ExistStone, PutState.ForbiddenStone -> putState
            PutState.CanPutStone -> {
                currentTurn = board.put(position, lastTurn.stone)
                updateLastPosition(position, lastTurn)
                PutState.CanPutStone
            }
        }
    }

    private fun updateLastPosition(
        position: Position,
        lastTurn: GameState.Turn,
    ) {
        lastPosition = if (forbiddenPosition(currentTurn, lastTurn)) lastPosition else position
    }

    private fun forbiddenPosition(
        currentTurn: GameState,
        lastTurn: GameState.Turn?,
    ): Boolean = currentTurn == lastTurn

    fun isPlaying(): Boolean = currentTurn is GameState.Turn
}
