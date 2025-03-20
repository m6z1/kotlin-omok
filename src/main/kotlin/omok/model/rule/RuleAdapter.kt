package omok.model.rule

import omok.model.board.Board
import omok.model.board.BoardPositionState
import omok.model.position.Position

object RuleAdapter {
    fun adapt(board: Board): List<List<Int>> {
        val adapted =
            List(board.sideLength.value) { column ->
                List(board.sideLength.value) { row ->
                    val state = board.stateOf(row, column)
                    when (state) {
                        BoardPositionState.Empty -> 0
                        BoardPositionState.Exist.Black -> 1
                        BoardPositionState.Exist.White -> 2
                    }
                }
            }
        return adapted
    }

    fun adapt(position: Position): Pair<Int, Int> = Pair(position.row.value, position.column.value)
}
