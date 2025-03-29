package woowacourse.omok.model.adapter

import woowacourse.omok.model.Stone
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.board.BoardCellState
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.position.Position
import woowacourse.omok.model.rule.BlackWinRule
import woowacourse.omok.model.rule.FourFourRule
import woowacourse.omok.model.rule.ThreeThreeRule
import woowacourse.omok.model.rule.WhiteWinRule

object RuleAdapter {
    fun canPut(
        board: Board,
        position: Position,
        stone: Stone,
    ): Boolean {
        val adaptedBoard: List<List<Int>> = board.toMatrix()
        val adaptedPosition: Pair<Int, Int> = position.toCoordinates()
        return when (stone) {
            Stone.BLACK ->
                when {
                    BlackWinRule.validated(adaptedBoard, adaptedPosition) -> true
                    FourFourRule.validated(adaptedBoard, adaptedPosition) -> false
                    ThreeThreeRule.validated(adaptedBoard, adaptedPosition) -> false
                    else -> true
                }

            Stone.WHITE -> true
        }
    }

    fun getState(
        board: Board,
        position: Position,
        stone: Stone,
    ): GameState =
        when (stone) {
            Stone.BLACK -> getBlackStoneState(board, position)
            Stone.WHITE -> getWhiteStoneState(board, position)
        }

    private fun getBlackStoneState(
        board: Board,
        position: Position,
    ): GameState {
        val adaptedBoard: List<List<Int>> = board.toMatrix()
        val adaptedPosition: Pair<Int, Int> = position.toCoordinates()
        if (BlackWinRule.validated(
                adaptedBoard,
                adaptedPosition,
            )
        ) {
            return GameState.Finish(Stone.BLACK)
        }
        return GameState.Turn(Stone.WHITE)
    }

    private fun getWhiteStoneState(
        board: Board,
        position: Position,
    ): GameState {
        val adaptedBoard: List<List<Int>> = board.toMatrix()
        val adaptedPosition: Pair<Int, Int> = position.toCoordinates()
        if (WhiteWinRule.validated(adaptedBoard, adaptedPosition)) {
            return GameState.Finish(Stone.WHITE)
        }
        return GameState.Turn(Stone.BLACK)
    }

    private fun Board.toMatrix(): List<List<Int>> =
        List(sideLength.value) { column ->
            List(sideLength.value) { row ->
                when (val state: BoardCellState = getBoardCellState(row, column)) {
                    BoardCellState.Empty -> 0
                    is BoardCellState.Exist ->
                        when (state.stone) {
                            Stone.BLACK -> 1
                            Stone.WHITE -> 2
                        }
                }
            }
        }

    private fun Position.toCoordinates(): Pair<Int, Int> = Pair(row.value, column.value)
}
