package omok.model

import omok.model.fake.FakePosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardPositionTest {
    @Test
    fun `오목판 위치에는 좌표와 상태를 가진다`() {
        val boardPosition =
            DefaultBoardPosition(position = FakePosition(), state = BoardPositionState.EMPTY)
        assertAll({
            assertThat(boardPosition.position).isEqualTo(FakePosition())
            assertThat(boardPosition.state).isEqualTo(BoardPositionState.EMPTY)
        })
    }

    @Test
    fun `오목판 위치의 상태는 비어있음, 흑돌 있음, 백돌 있음중 하나이다`() {
        assertThat(BoardPositionState.entries).hasSameElementsAs(
            listOf(
                BoardPositionState.EMPTY,
                BoardPositionState.EXIST_BLACK_STONE,
                BoardPositionState.EXIST_WHITE_STONE,
            ),
        )
    }
}
