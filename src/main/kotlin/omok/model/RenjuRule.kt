package omok.model

interface RenjuRule {
    fun gameState(position: Position): GameState
}

class DefaultRenjuRule(
    private val board: Board,
) : RenjuRule {
    private val directions =
        listOf(
            Pair(1, 0),
            Pair(0, 1),
            Pair(1, 1),
            Pair(1, -1),
        )

    override fun gameState(position: Position): GameState {
        val boardPositionState = board.stateOf(position) as? BoardPositionState.Exist ?: return GameState.PLAYING
        for ((dx, dy) in directions) {
            if (countStonesInDirection(position, dx, dy, boardPositionState) >= 5) {
                return when (boardPositionState) {
                    BoardPositionState.Exist.Black -> GameState.BLACK_WIN
                    BoardPositionState.Exist.White -> GameState.WHITE_WIN
                }
            }
        }
        return GameState.PLAYING
    }

    private fun countStonesInDirection(
        position: Position,
        dx: Int,
        dy: Int,
        state: BoardPositionState,
    ): Int {
        var count = 1
        count += countOneWay(position, dx, dy, state)
        count += countOneWay(position, -dx, -dy, state)
        return count
    }

    private fun countOneWay(
        position: Position,
        dx: Int,
        dy: Int,
        state: BoardPositionState,
    ): Int {
        var count = 0
        var currentRow = position.row.value + dx
        var currentCol = position.column.value + dy

        while (true) {
            val nextPosition = DefaultPosition(currentRow, currentCol)
            if (board.stateOf(nextPosition) == state) {
                count++
                currentRow += dx
                currentCol += dy
            } else {
                break
            }
        }
        return count
    }
}
