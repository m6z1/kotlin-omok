package omok.model.board

import omok.model.Stone
import omok.model.position.Position

interface BoardPosition {
    val position: Position
    val state: BoardPositionState

    fun put(stone: Stone): BoardPosition
}

data class DefaultBoardPosition(
    override val position: Position,
    override val state: BoardPositionState = BoardPositionState.Empty,
) : BoardPosition {
    override fun put(stone: Stone): BoardPosition = copy(state = state.put(stone))
}
