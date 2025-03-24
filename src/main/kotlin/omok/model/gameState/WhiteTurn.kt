package omok.model.gameState

import omok.model.Stone
import omok.model.adapter.RuleAdapter.toCoordinates
import omok.model.adapter.RuleAdapter.toMatrix
import omok.model.board.Board
import omok.model.position.Position
import omok.model.rule.WhiteWinRule

object WhiteTurn : GameState.Playing {
    override val stone: Stone = Stone.WHITE

    override fun play(
        board: Board,
        position: Position,
    ): GameState {
        val adaptedBoard: List<List<Int>> = board.toMatrix()
        val adaptedPosition: Pair<Int, Int> = position.toCoordinates()
        board.put(position, stone)
        if (WhiteWinRule.validated(adaptedBoard, adaptedPosition)) {
            return GameState.Finish.WHITE_WIN
        }
        return BlackTurn
    }
}
