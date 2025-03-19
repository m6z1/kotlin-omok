package omok.model

import omok.model.fake.FakePosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardPositionTest {
    @Test
    fun `오목판 위치에는 좌표와 상태를 가진다`() {
        val boardPosition =
            DefaultBoardPosition(position = FakePosition(), state = BoardPositionState.Empty)
        assertAll({
            assertThat(boardPosition.position).isEqualTo(FakePosition())
            assertThat(boardPosition.state).isEqualTo(BoardPositionState.Empty)
        })
    }

    @Test
    fun `오목판 위치의 상태는 비어있음, 흑돌 있음, 백돌 있음중 하나이다`() {
        assertAll(
            { assertThat(BoardPositionState.Empty).isInstanceOf(BoardPositionState::class.java) },
            { assertThat(BoardPositionState.Exist.Black).isInstanceOf(BoardPositionState::class.java) },
            { assertThat(BoardPositionState.Exist.White).isInstanceOf(BoardPositionState::class.java) },
        )
    }
}
