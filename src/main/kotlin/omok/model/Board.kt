package omok.model

interface Board {
    val positions: Set<BoardPosition>
    val sideLength: GridElement

    fun put(
        position: Position,
        stone: Stone,
    )

    fun put(
        row: Int,
        column: Int,
        stone: Stone,
    )

    fun stateOf(position: Position): BoardPositionState

    fun stateOf(
        row: Int,
        column: Int,
    ): BoardPositionState
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

        _positions.remove(targetPosition)
        _positions.add(targetPosition.put(stone))
    }

    override fun put(
        row: Int,
        column: Int,
        stone: Stone,
    ) {
        put(DefaultPosition(row, column), stone)
    }

    override fun stateOf(position: Position): BoardPositionState = boardPosition(position).state

    override fun stateOf(
        row: Int,
        column: Int,
    ): BoardPositionState = stateOf(DefaultPosition(row, column))

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
            return DefaultBoard(defaultPositions, sideLength)
        }

        operator fun invoke(sideLength: Int = 15): DefaultBoard {
            val defaultPositions: Set<BoardPosition> =
                (0 until sideLength)
                    .flatMap { row: Int ->
                        (0 until sideLength).map { column: Int ->
                            val position = DefaultPosition(row, column)
                            DefaultBoardPosition(position)
                        }
                    }.toSet()
            return DefaultBoard(defaultPositions, DefaultGridElement(sideLength))
        }
    }
}
