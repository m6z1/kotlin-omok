package omok.model.adapter

import omok.model.board.Board
import omok.model.board.BoardPositionState
import omok.model.position.Position

object RuleAdapter {
    fun Board.toMatrix(): List<List<Int>> {
        val adapted =
            List(sideLength.value) { column ->
                List(sideLength.value) { row ->
                    val state = stateAt(row, column)
                    when (state) {
                        BoardPositionState.Empty -> 0
                        BoardPositionState.Exist.Black -> 1
                        BoardPositionState.Exist.White -> 2
                    }
                }
            }
        return adapted
    }

    fun Position.toCoordinates(): Pair<Int, Int> = Pair(row.value, column.value)
}
