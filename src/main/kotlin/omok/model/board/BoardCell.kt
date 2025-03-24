package omok.model.board

import omok.model.Stone
import omok.model.position.Position

interface BoardCell {
    val position: Position
    val state: BoardPositionState

    fun withStone(stone: Stone): BoardCell
}

data class DefaultBoardCell(
    override val position: Position,
    override val state: BoardPositionState = BoardPositionState.Empty,
) : BoardCell {
    override fun withStone(stone: Stone): BoardCell = copy(state = state.withStone(stone))
}
