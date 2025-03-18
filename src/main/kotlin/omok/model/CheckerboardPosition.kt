package omok.model

interface CheckerboardPosition {
    val position: Position
    val state: CheckerboardPositionState
}

class DefaultCheckerboardPosition(
    override val position: Position,
    override val state: CheckerboardPositionState = CheckerboardPositionState.EMPTY,
) : CheckerboardPosition
