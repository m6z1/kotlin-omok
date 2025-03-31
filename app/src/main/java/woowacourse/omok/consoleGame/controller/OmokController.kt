package woowacourse.omok.consoleGame.controller

import woowacourse.omok.consoleGame.view.OmokView
import woowacourse.omok.model.OmokGame
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.gameState.PutState
import woowacourse.omok.model.position.Position

class OmokController(
    private val omokView: OmokView,
    private val omokGame: OmokGame,
) {
    fun run() {
        omokView.showStartMessage(omokGame.board)

        while (omokGame.isPlaying()) {
            val position: Position =
                getPosition(omokGame.currentState, omokGame.board, omokGame.lastPosition)
            val putState: PutState = omokGame.checkPutState(position)
            when (putState) {
                PutState.ExistStone -> omokView.notifyExistStone()
                PutState.ForbiddenStone -> omokView.notifyForbiddenPosition()
                PutState.CanPutStone -> Unit
            }
            omokView.showBoard(omokGame.board)
        }
        omokView.showWinner(omokGame.currentState.stone)
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
