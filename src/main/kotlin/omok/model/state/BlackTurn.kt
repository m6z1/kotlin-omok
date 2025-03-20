package omok.model.state

import omok.domain.omokRule.ThreeThreeRule
import omok.model.Board
import omok.model.Position
import omok.model.RuleAdapter
import omok.model.Stone
import omok.model.rule.BlackWinRule
import omok.model.rule.FourFourRule

object BlackTurn : GameState.Playing {
    override val stone: Stone = Stone.BLACK

    override fun play(
        board: Board,
        position: Position,
    ): GameState {
        val adaptedBoard = RuleAdapter.adapt(board)
        val adaptedPosition = RuleAdapter.adapt(position)
        return when {
            BlackWinRule.validated(adaptedBoard, adaptedPosition) -> {
                board.put(position, stone)
                GameState.Finish.BLACK_WIN
            }

            FourFourRule.validated(adaptedBoard, adaptedPosition) -> this
            ThreeThreeRule.validated(adaptedBoard, adaptedPosition) -> this
            else -> {
                board.put(position, stone)
                WhiteTurn
            }
        }
    }
}
