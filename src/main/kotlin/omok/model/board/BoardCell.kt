package omok.model.board

import omok.model.Stone
import omok.model.position.Position

interface BoardCell {
    val position: Position
    val state: BoardCellState

    fun replace(stone: Stone): BoardCell
}

data class DefaultBoardCell(
    override val position: Position,
    override val state: BoardCellState = BoardCellState.Empty,
) : BoardCell {
    override fun replace(stone: Stone): BoardCell = copy(state = state.replace(stone))
}
