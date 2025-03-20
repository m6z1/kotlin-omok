package omok.controller

import omok.model.DefaultBoard
import omok.model.Position
import omok.model.state.BlackTurn
import omok.model.state.GameState
import omok.view.OmokView

class Omok(
    private val omokView: OmokView,
) {
    fun play() {
        val board = DefaultBoard()
        omokView.start(board)
        var turn: GameState = BlackTurn
        var lastStonePosition: Position
        var position: Position =
            omokView.position(
                stone = turn.stone,
                boundary = board.sideLength.value,
                lastStonePosition = null,
            )
        while (turn is GameState.Playing) {
            turn = turn.play(board, position)
            omokView.show(board)
            if (turn is GameState.Finish) break
            lastStonePosition = position
            position =
                omokView.position(
                    stone = turn.stone,
                    boundary = board.sideLength.value,
                    lastStonePosition = lastStonePosition,
                )
        }
        omokView.show(turn.stone)
    }
}
