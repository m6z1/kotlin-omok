package omok.model

import omok.model.board.Board
import omok.model.board.BoardCell
import omok.model.board.BoardCellState
import omok.model.board.DefaultBoard
import omok.model.board.DefaultBoardCell
import omok.model.position.DefaultPosition
import omok.model.position.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `원하는 한 변의 길이를 받아 정사각형의 보드판을 만들 수 있다`() {
        // given:
        // when:
        val board: Board = DefaultBoard(10)

        // then:
        assertThat(board.positions.size).isEqualTo(100)
    }

    @Test
    fun `특정 위치에 있는 오목판의 상태를 알 수 있다`() {
        // given:
        val position: Position = DefaultPosition(1, 2)
        val board: Board = DefaultBoard(DefaultBoardCell(position, BoardCellState.Exist.White))

        // when:
        val actual: BoardCellState = board.getBoardCellState(position)

        // then:
        assertThat(actual).isEqualTo(BoardCellState.Exist.White)
    }

    @Test
    fun `원하는 위치에 돌을 둘 수 있다`() {
        // given:
        val position: Position = DefaultPosition(1, 2)
        val boardCell: BoardCell = DefaultBoardCell(position)
        val board: Board = DefaultBoard(boardCell)

        // when:
        board.put(position, Stone.WHITE)

        // then:
        assertThat(board.getBoardCellState(position)).isEqualTo(BoardCellState.Exist.White)
    }
}
