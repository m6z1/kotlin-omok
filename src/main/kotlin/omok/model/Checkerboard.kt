package omok.model

interface Checkerboard {
    val positions: Set<CheckerboardPosition>
    val size: Int

    fun put(
        position: Position,
        stone: Stone,
    )

    fun stateOf(position: Position): CheckerboardPositionState
}

class DefaultCheckerboard(
    positions: Set<CheckerboardPosition>,
) : Checkerboard {
    private val _positions: MutableSet<CheckerboardPosition> = positions.toMutableSet()
    override val positions: Set<CheckerboardPosition> get() = _positions

    override val size: Int
        get() = positions.size

    constructor(position: CheckerboardPosition) : this(setOf(position))

    override fun put(
        position: Position,
        stone: Stone,
    ) {
        val targetPosition: CheckerboardPosition = checkerboardPosition(position)

        _positions -= targetPosition
        _positions += targetPosition.put(stone)
    }

    override fun stateOf(position: Position): CheckerboardPositionState = checkerboardPosition(position).state

    private fun checkerboardPosition(position: Position): CheckerboardPosition =
        positions
            .find { checkerboardPosition ->
                position == checkerboardPosition.position
            } ?: throw IllegalArgumentException("$position 는 존재하지 않는 위치 입니다.")

    companion object {
        operator fun invoke(): DefaultCheckerboard {
            val defaultPositions: Set<CheckerboardPosition> =
                (0..14)
                    .flatMap { row: Int ->
                        (0..14).map { column: Int ->
                            val position = DefaultPosition(row, column)
                            DefaultCheckerboardPosition(position)
                        }
                    }.toSet()
            return DefaultCheckerboard(defaultPositions)
        }
    }
}
