package omok.model.board

import omok.model.Stone
import omok.model.position.DefaultPosition
import omok.model.position.GridElement
import omok.model.position.Position

interface Board {
    val positions: Set<BoardCell>
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

    fun stateAt(position: Position): BoardPositionState

    fun stateAt(
        row: Int,
        column: Int,
    ): BoardPositionState
}

class DefaultBoard(
    positions: Set<BoardCell>,
    override val sideLength: GridElement = GridElement(15),
) : Board {
    override var positions: Set<BoardCell> = positions.toSet()
        private set

    constructor(position: BoardCell) : this(setOf(position))

    override fun put(
        position: Position,
        stone: Stone,
    ) {
        val targetPosition: BoardCell = boardPosition(position)
        positions -= targetPosition
        positions += targetPosition.withStone(stone)
    }

    override fun put(
        row: Int,
        column: Int,
        stone: Stone,
    ) {
        put(DefaultPosition(row, column), stone)
    }

    override fun stateAt(position: Position): BoardPositionState = boardPosition(position).state

    override fun stateAt(
        row: Int,
        column: Int,
    ): BoardPositionState = stateAt(DefaultPosition(row, column))

    private fun boardPosition(position: Position): BoardCell =
        positions
            .find { boardPosition ->
                position == boardPosition.position
            } ?: throw IllegalArgumentException("$position 는 존재하지 않는 위치 입니다.")

    companion object {
        operator fun invoke(sideLength: Int = 15): DefaultBoard {
            val defaultPositions: Set<BoardCell> = defaultPositions(sideLength)
            return DefaultBoard(defaultPositions, GridElement(sideLength))
        }

        private fun defaultPositions(size: Int): Set<BoardCell> =
            (0 until size)
                .flatMap { row: Int ->
                    (0 until size).map { column: Int ->
                        val position = DefaultPosition(row, column)
                        DefaultBoardCell(position)
                    }
                }.toSet()
    }
}
