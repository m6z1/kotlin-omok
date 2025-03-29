package woowacourse.omok.model.board

import woowacourse.omok.model.Stone
import woowacourse.omok.model.adapter.RuleAdapter
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.gameState.PutState
import woowacourse.omok.model.position.GridElement
import woowacourse.omok.model.position.Position

class Board(
    positions: Set<BoardCell>,
    val sideLength: GridElement = GridElement(15),
) {
    var positions: Set<BoardCell> = positions.toSet()
        private set

    constructor(position: BoardCell) : this(setOf(position))

    fun getPutState(
        position: Position,
        stone: Stone,
    ): PutState {
        if (!getBoardCell(position).isEmpty()) return PutState.ExistStone
        if (!RuleAdapter.canPut(this, position, stone)) return PutState.ForbiddenStone
        return PutState.CanPutStone
    }

    fun put(
        position: Position,
        stone: Stone,
    ): GameState {
        val targetPosition: BoardCell = getBoardCell(position)
        positions -= targetPosition
        positions += targetPosition.replace(stone)
        return RuleAdapter.getState(this, position, stone)
    }

    fun put(
        row: Int,
        column: Int,
        stone: Stone,
    ): GameState = put(Position(row, column), stone)

    fun getBoardCellState(position: Position): BoardCellState = getBoardCell(position).state

    fun getBoardCellState(
        row: Int,
        column: Int,
    ): BoardCellState = getBoardCellState(Position(row, column))

    private fun getBoardCell(position: Position): BoardCell =
        positions
            .find { boardPosition ->
                position == boardPosition.position
            } ?: throw IllegalArgumentException("$position 는 존재하지 않는 위치 입니다.")

    companion object {
        operator fun invoke(sideLength: Int = 15): Board {
            val defaultPositions: Set<BoardCell> = defaultPositions(sideLength)
            return Board(defaultPositions, GridElement(sideLength))
        }

        private fun defaultPositions(size: Int): Set<BoardCell> =
            (0 until size)
                .flatMap { row: Int ->
                    (0 until size).map { column: Int ->
                        val position = Position(row, column)
                        BoardCell(position)
                    }
                }.toSet()
    }
}
