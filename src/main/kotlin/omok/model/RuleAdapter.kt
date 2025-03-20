package omok.model

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
