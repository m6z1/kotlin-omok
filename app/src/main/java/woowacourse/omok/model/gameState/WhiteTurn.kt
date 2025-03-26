package woowacourse.omok.model.gameState

import woowacourse.omok.model.Stone
import woowacourse.omok.model.adapter.RuleAdapter
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.position.Position

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
