package omok.controller

import omok.model.OmokGame
import omok.model.board.Board
import omok.model.gameState.GameState
import omok.model.position.Position
import omok.view.OmokView

class OmokController(
    private val omokView: OmokView,
    private val omokGame: OmokGame,
) {
    fun run() {
        omokView.showStartMessage(omokGame.board)

        while (omokGame.currentTurn is GameState.Playing) {
            val position: Position = getPosition(omokGame.currentTurn, omokGame.board, omokGame.lastPosition)
            val currentState: GameState = omokGame.play(position)

            if (omokGame.forbiddenPosition(currentState)) {
                omokView.notifyForbiddenPosition()
            }
            omokView.showBoard(omokGame.board)
        }
        omokView.showWinner(omokGame.currentTurn.stone)
    }

    private fun getPosition(
        currentTurn: GameState,
        board: Board,
        lastPosition: Position?,
    ): Position =
        omokView.readPosition(
            stone = currentTurn.stone,
            boundary = board.sideLength.value,
            lastPosition = lastPosition,
        )
}
