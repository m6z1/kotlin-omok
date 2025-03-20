package omok.model.gameState

import omok.model.Stone
import omok.model.board.Board
import omok.model.position.Position
import omok.model.rule.BlackWinRule
import omok.model.rule.FourFourRule
import omok.model.rule.RuleAdapter
import omok.model.rule.ThreeThreeRule

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
