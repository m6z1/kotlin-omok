package omok.model

interface Board {
    val positions: Set<BoardPosition>
    val size: Int

    fun put(
        position: Position,
        stone: Stone,
    )

    fun stateOf(position: Position): BoardPositionState
}

class DefaultBoard(
    positions: Set<BoardPosition>,
) : Board {
    private val _positions: MutableSet<BoardPosition> = positions.toMutableSet()
    override val positions: Set<BoardPosition> get() = _positions

    override val size: Int
        get() = positions.size

    constructor(position: BoardPosition) : this(setOf(position))

    override fun put(
        position: Position,
        stone: Stone,
    ) {
        val targetPosition: BoardPosition = boardPosition(position)

        _positions -= targetPosition
        _positions += targetPosition.put(stone)
    }

    override fun stateOf(position: Position): BoardPositionState = boardPosition(position).state

    private fun boardPosition(position: Position): BoardPosition =
        positions
            .find { boardPosition ->
                position == boardPosition.position
            } ?: throw IllegalArgumentException("$position 는 존재하지 않는 위치 입니다.")

    companion object {
        operator fun invoke(): DefaultBoard {
            val defaultPositions: Set<BoardPosition> =
                (0..14)
                    .flatMap { row: Int ->
                        (0..14).map { column: Int ->
                            val position = DefaultPosition(row, column)
                            DefaultBoardPosition(position)
                        }
                    }.toSet()
            return DefaultBoard(defaultPositions)
        }
    }
}
