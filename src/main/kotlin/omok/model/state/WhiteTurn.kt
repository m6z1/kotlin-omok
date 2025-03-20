package omok.model.state

import omok.model.Board
import omok.model.Position
import omok.model.RuleAdapter
import omok.model.Stone
import omok.model.omokRule.WhiteWinRule

object WhiteTurn : GameState.Playing {
    override val stone: Stone = Stone.WHITE

    override fun play(
        board: Board,
        position: Position,
    ): GameState {
        val adaptedBoard = RuleAdapter.adapt(board)
        val adaptedPosition = RuleAdapter.adapt(position)
        board.put(position, stone)
        if (WhiteWinRule.validated(adaptedBoard, adaptedPosition)) {
            return GameState.Finish.WHITE_WIN
        }
        return BlackTurn
    }
}
