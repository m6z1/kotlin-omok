package omok.model

interface Board {
    val positions: Set<BoardPosition>
    val sideLength: GridElement

    fun put(
        position: Position,
        stone: Stone,
    )

    fun stateOf(position: Position): BoardPositionState
}

class DefaultBoard(
    positions: Set<BoardPosition>,
    override val sideLength: GridElement = DefaultGridElement(15),
) : Board {
    private val _positions: MutableSet<BoardPosition> = positions.toMutableSet()
    override val positions: Set<BoardPosition> get() = _positions

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
        operator fun invoke(sideLength: GridElement): DefaultBoard {
            val defaultPositions: Set<BoardPosition> =
                (0 until sideLength.value)
                    .flatMap { row: Int ->
                        (0 until sideLength.value).map { column: Int ->
                            val position = DefaultPosition(row, column)
                            DefaultBoardPosition(position)
                        }
                    }.toSet()
            return DefaultBoard(defaultPositions)
        }

        operator fun invoke(sideLength: Int): DefaultBoard {
            val defaultPositions: Set<BoardPosition> =
                (0 until sideLength)
                    .flatMap { row: Int ->
                        (0 until sideLength).map { column: Int ->
                            val position = DefaultPosition(row, column)
                            DefaultBoardPosition(position)
                        }
                    }.toSet()
            return DefaultBoard(defaultPositions)
        }
    }
}
