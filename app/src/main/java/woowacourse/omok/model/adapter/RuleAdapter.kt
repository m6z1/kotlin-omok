package woowacourse.omok.model.adapter

import woowacourse.omok.model.Stone
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.board.BoardCellState
import woowacourse.omok.model.gameState.BlackTurn
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.gameState.WhiteTurn
import woowacourse.omok.model.position.Position
import woowacourse.omok.model.rule.BlackWinRule
import woowacourse.omok.model.rule.FourFourRule
import woowacourse.omok.model.rule.ThreeThreeRule
import woowacourse.omok.model.rule.WhiteWinRule

object RuleAdapter {
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
        return when {
            BlackWinRule.validated(adaptedBoard, adaptedPosition) -> GameState.Finish(Stone.BLACK)
            FourFourRule.validated(adaptedBoard, adaptedPosition) -> BlackTurn
            ThreeThreeRule.validated(adaptedBoard, adaptedPosition) -> BlackTurn
            else -> WhiteTurn
        }
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
        return BlackTurn
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
