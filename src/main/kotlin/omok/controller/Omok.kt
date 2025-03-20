package omok.controller

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
        omokView.start(board)
        var currentTurn: GameState = BlackTurn
        var lastTurn: GameState.Playing
        var lastPosition: Position? = null
        var position: Position =
            omokView.position(
                stone = currentTurn.stone,
                boundary = board.sideLength.value,
            )
        while (currentTurn is GameState.Playing) {
            lastTurn = currentTurn
            currentTurn = currentTurn.play(board, position)
            if (!currentTurn.forbidden(lastTurn)) {
                lastPosition = position
            }
            omokView.show(board)
            if (currentTurn is GameState.Finish) break
            position =
                omokView.position(
                    stone = currentTurn.stone,
                    boundary = board.sideLength.value,
                    lastPosition = lastPosition,
                    forbiddenPosition = if (currentTurn.forbidden(lastTurn)) lastPosition else null,
                )
        }
        omokView.show(currentTurn.stone)
    }

    private fun GameState.forbidden(lastTurn: GameState.Playing): Boolean = this == lastTurn
}
