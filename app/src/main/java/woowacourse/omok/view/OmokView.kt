package woowacourse.omok.view

import woowacourse.omok.model.Stone
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.board.BoardCellState
import woowacourse.omok.model.position.Position

class OmokView {
    fun showStartMessage(board: Board) {
        println("오목 게임을 시작합니다.\n")
        showBoard(board)
    }

    fun readPosition(
        stone: Stone,
        boundary: Int,
        lastPosition: Position? = null,
    ): Position {
        print("${stone.toUIModel()}의 차례입니다. ")
        if (lastPosition != null) print("(마지막 돌의 위치: ${lastPosition.toUIModel(boundary)})")
        print("\n위치를 입력하세요: ")
        return readln().trim().uppercase().toPosition(boundary)
    }

    fun notifyExistStone() {
        println("해당 위치에는 돌이 있습니다.")
    }

    fun notifyForbiddenPosition() {
        println("해당 위치는 금수입니다.")
    }

    fun showBoard(board: Board) {
        val boardToList: List<List<BoardCellState>> = getMatrix(board)
        val lastIndex = board.sideLength.value - 1
        val boundary = board.sideLength.value
        boardToList.forEachIndexed { columnIndex: Int, row: List<BoardCellState> ->
            row.forEachIndexed { rowIndex: Int, state: BoardCellState ->
                val wantToShow: String =
                    columnIndex(rowIndex, columnIndex, boundary) +
                        boardCellState(state, rowIndex, columnIndex, lastIndex) +
                        cellDivider(rowIndex, lastIndex)
                print(wantToShow)
            }
            println()
        }
        println(rowIndex(lastIndex))
    }

    private fun getMatrix(board: Board) =
        List(board.sideLength.value) { column ->
            List(board.sideLength.value) { row ->
                board.getBoardCellState(row, column)
            }
        }

    private fun columnIndex(
        rowIndex: Int,
        columnIndex: Int,
        boundary: Int,
    ): String =
        if (rowIndex == 0) {
            val columnToString: String = (boundary - columnIndex).toString()
            if (columnToString.length == 1) {
                "  $columnToString "
            } else {
                " $columnToString "
            }
        } else {
            ""
        }

    private fun boardCellState(
        state: BoardCellState,
        rowIndex: Int,
        columnIndex: Int,
        lastIndex: Int,
    ): String =
        when (state) {
            BoardCellState.Empty -> {
                when {
                    rowIndex == 0 && columnIndex == 0 -> "┌"
                    rowIndex == 0 && columnIndex == lastIndex -> "└"
                    rowIndex == lastIndex && columnIndex == lastIndex -> "┘"
                    rowIndex == lastIndex && columnIndex == 0 -> "┐"
                    columnIndex == 0 -> "┬"
                    columnIndex == lastIndex -> "┴"
                    rowIndex == 0 -> "├"
                    rowIndex == lastIndex -> "┤"
                    else -> "┼"
                }
            }

            is BoardCellState.Exist ->
                when (state.stone) {
                    Stone.BLACK -> "●"
                    Stone.WHITE -> "○"
                }
        }

    private fun cellDivider(
        rowIndex: Int,
        lastIndex: Int,
    ) = if (rowIndex == lastIndex) "" else "──"

    private fun rowIndex(lastIndex: Int): String {
        val range = Char(65)..Char(65 + lastIndex)
        return range.joinToString(separator = "  ", prefix = "    ")
    }

    fun showWinner(winner: Stone) {
        println("${winner.toUIModel()}돌이 이겼습니다.")
    }

    private fun Stone.toUIModel(): String =
        when (this) {
            Stone.BLACK -> "흑"
            Stone.WHITE -> "백"
        }

    private fun Position.toUIModel(boundary: Int): String = Char('A'.code + row.value).toString() + (boundary - column.value)

    private fun String.toPosition(boundary: Int): Position {
        val row: Int = this.first { !it.isDigit() } - 'A'
        val column: Int = boundary - this.filter { it.isDigit() }.toInt()
        return Position(row, column)
    }
}
