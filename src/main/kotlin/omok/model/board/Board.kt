package omok.model.board

import omok.model.Stone
import omok.model.position.DefaultGridElement
import omok.model.position.DefaultPosition
import omok.model.position.GridElement
import omok.model.position.Position

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
    override var positions: Set<BoardPosition> = positions.toSet()
        private set

    constructor(position: BoardPosition) : this(setOf(position))

    override fun put(
        position: Position,
        stone: Stone,
    ) {
        val targetPosition: BoardPosition = boardPosition(position)
        positions -= targetPosition
        positions += targetPosition.put(stone)
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
            val defaultPositions: Set<BoardPosition> = defaultPositions(sideLength.value)
            return DefaultBoard(defaultPositions, sideLength)
        }

        operator fun invoke(sideLength: Int = 15): DefaultBoard {
            val defaultPositions: Set<BoardPosition> = defaultPositions(sideLength)
            return DefaultBoard(defaultPositions, DefaultGridElement(sideLength))
        }

        private fun defaultPositions(size: Int): Set<BoardPosition> =
            (0 until size)
                .flatMap { row: Int ->
                    (0 until size).map { column: Int ->
                        val position = DefaultPosition(row, column)
                        DefaultBoardPosition(position)
                    }
                }.toSet()
    }
}
