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
        val adaptedBoard: List<List<Int>> = RuleAdapter.adapt(board)
        val adaptedPosition: Pair<Int, Int> = RuleAdapter.adapt(position)
        return when {
            BlackWinRule.validated(adaptedBoard, adaptedPosition) -> blackWin(board, position)
            FourFourRule.validated(adaptedBoard, adaptedPosition) -> this
            ThreeThreeRule.validated(adaptedBoard, adaptedPosition) -> this
            else -> whiteTurn(board, position)
        }
    }

    private fun blackWin(
        board: Board,
        position: Position,
    ): GameState.Finish {
        board.put(position, stone)
        return GameState.Finish.BLACK_WIN
    }

    private fun whiteTurn(
        board: Board,
        position: Position,
    ): WhiteTurn {
        board.put(position, stone)
        return WhiteTurn
    }
}
