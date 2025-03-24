package omok.controller

import omok.model.board.Board
import omok.model.board.DefaultBoard
import omok.model.gameState.BlackTurn
import omok.model.gameState.GameState
import omok.model.position.Position
import omok.view.OmokView

class Omok(
    private val omokView: OmokView,
) {
    fun play() {
        val board = DefaultBoard()
        omokView.showStartMessage(board)
        var currentTurn: GameState = BlackTurn
        var lastPosition: Position? = null
        var position: Position = getPosition(currentTurn, board)
        while (currentTurn is GameState.Playing) {
            val lastTurn: GameState.Playing = currentTurn
            currentTurn = currentTurn.play(board, position)
            if (currentTurn.forbidden(lastTurn)) omokView.notifyForbiddenPosition()
            lastPosition = getLastPosition(currentTurn, lastTurn, lastPosition, position)
            omokView.showBoard(board)
            if (currentTurn is GameState.Finish) break
            position = getPosition(currentTurn, board, lastPosition)
        }
        omokView.showWinner(currentTurn.stone)
    }

    private fun getPosition(
        currentTurn: GameState,
        board: Board,
        lastPosition: Position? = null,
    ): Position =
        omokView.readPosition(
            stone = currentTurn.stone,
            boundary = board.sideLength.value,
            lastPosition = lastPosition,
        )

    private fun getLastPosition(
        currentTurn: GameState,
        lastTurn: GameState.Playing,
        lastPosition: Position?,
        position: Position,
    ): Position? = if (currentTurn.forbidden(lastTurn)) lastPosition else position

    private fun GameState.forbidden(lastTurn: GameState.Playing?): Boolean = this == lastTurn
}
