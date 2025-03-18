package omok.model

import omok.model.fake.FakePosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CheckerboardPositionTest {
    @Test
    fun `오목판 위치에는 좌표와 상태를 가진다`() {
        val checkerBoardPosition =
            DefaultCheckerboardPosition(position = FakePosition(), state = CheckerboardPositionState.EMPTY)
        assertAll({
            assertThat(checkerBoardPosition.position).isEqualTo(FakePosition())
            assertThat(checkerBoardPosition.state).isEqualTo(CheckerboardPositionState.EMPTY)
        })
    }

    @Test
    fun `오목판 위치의 상태는 비어있음, 흑돌 있음, 백돌 있음중 하나이다`() {
        assertThat(CheckerboardPositionState.entries).hasSameElementsAs(
            listOf(
                CheckerboardPositionState.EMPTY,
                CheckerboardPositionState.EXIST_BLACK_STONE,
                CheckerboardPositionState.EXIST_WHITE_STONE,
            ),
        )
    }
}
