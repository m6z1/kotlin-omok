package woowacourse.omok.model.board

import woowacourse.omok.model.Stone
import woowacourse.omok.model.position.Position

data class BoardCell(
    val position: Position,
    val state: BoardCellState = BoardCellState.Empty,
) {
    fun replace(stone: Stone): BoardCell = copy(state = state.replace(stone))
}
