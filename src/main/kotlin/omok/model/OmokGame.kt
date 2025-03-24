package omok.model

import omok.model.board.DefaultBoard
import omok.model.gameState.BlackTurn
import omok.model.gameState.GameState
import omok.model.position.Position

class OmokGame {
    val board = DefaultBoard()
    var currentTurn: GameState = BlackTurn
        private set
    var lastPosition: Position? = null
        private set

    fun play(position: Position): GameState {
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
