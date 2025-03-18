package omok.model

interface CheckerboardPosition {
    val position: Position
    val state: CheckerboardPositionState

    fun put(stone: Stone): CheckerboardPosition
}

data class DefaultCheckerboardPosition(
    override val position: Position,
    override val state: CheckerboardPositionState = CheckerboardPositionState.EMPTY,
) : CheckerboardPosition {
    override fun put(stone: Stone): CheckerboardPosition = copy(state = state.put(stone))
}
