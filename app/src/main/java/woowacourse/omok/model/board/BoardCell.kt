package woowacourse.omok.model.board

import woowacourse.omok.model.Stone
import woowacourse.omok.model.board.BoardCellState.Empty
import woowacourse.omok.model.board.BoardCellState.Exist
import woowacourse.omok.model.position.Position

data class BoardCell(
    val position: Position,
    val state: BoardCellState = Empty,
) {
    fun replace(stone: Stone): BoardCell = copy(state = updateState(stone))

    private fun updateState(stone: Stone): BoardCellState =
        when (stone) {
            Stone.BLACK -> Exist(Stone.BLACK)
            Stone.WHITE -> Exist(Stone.WHITE)
        }

    fun isEmpty(): Boolean = state is Empty
}
