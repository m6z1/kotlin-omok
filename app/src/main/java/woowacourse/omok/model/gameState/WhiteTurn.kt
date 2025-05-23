package omok.model.gameState

import omok.model.Stone
import omok.model.adapter.RuleAdapter
import omok.model.board.Board
import omok.model.position.Position

object WhiteTurn : GameState.Playing {
    override val stone: Stone = Stone.WHITE

    override fun play(
        board: Board,
        position: Position,
    ): GameState {
        board.put(position, stone)
        return RuleAdapter.getState(board, position, stone)
    }
}
