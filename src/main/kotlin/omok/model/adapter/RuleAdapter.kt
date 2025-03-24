package omok.model.adapter

import omok.model.Stone
import omok.model.board.Board
import omok.model.board.BoardPositionState
import omok.model.gameState.BlackTurn
import omok.model.gameState.GameState
import omok.model.gameState.WhiteTurn
import omok.model.position.Position
import omok.model.rule.BlackWinRule
import omok.model.rule.FourFourRule
import omok.model.rule.ThreeThreeRule
import omok.model.rule.WhiteWinRule

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
                val state = getBoardCellState(row, column)
                when (state) {
                    BoardPositionState.Empty -> 0
                    BoardPositionState.Exist.Black -> 1
                    BoardPositionState.Exist.White -> 2
                }
            }
        }

    private fun Position.toCoordinates(): Pair<Int, Int> = Pair(row.value, column.value)
}
