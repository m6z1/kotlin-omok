package omok.model

import omok.model.board.BoardCell
import omok.model.board.BoardPositionState
import omok.model.board.DefaultBoardCell
import omok.model.testDouble.FakePosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardCellTest {
    @Test
    fun `오목판 위치에는 좌표와 상태를 가진다`() {
        val boardCell: BoardCell =
            DefaultBoardCell(position = FakePosition(), state = BoardPositionState.Empty)
        assertAll({
            assertThat(boardCell.position).isEqualTo(FakePosition())
            assertThat(boardCell.state).isEqualTo(BoardPositionState.Empty)
        })
    }
}
