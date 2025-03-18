package omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CheckerboardTest {
    @Test
    fun `오목판은 225개의 중복되지 않는 위치를 가진다`() {
        val checkerboard = DefaultCheckerboard()
        assertThat(checkerboard.size).isEqualTo(225)
    }

    @Test
    fun `특정 위치에 있는 오목판의 상태를 알 수 있다`() {
        // given:
        val position = DefaultPosition(1, 2)
        val checkerboard = DefaultCheckerboard(DefaultCheckerboardPosition(position, CheckerboardPositionState.EXIST_WHITE_STONE))

        // when:
        val actual: CheckerboardPositionState = checkerboard.stateOf(position)

        // then:
        assertThat(actual).isEqualTo(CheckerboardPositionState.EXIST_WHITE_STONE)
    }

    @Test
    fun `원하는 위치에 돌을 둘 수 있다`() {
        // given:
        val checkerboard = DefaultCheckerboard()
        val position = DefaultPosition(1, 2)

        // when:
        checkerboard.put(position, Stone.WHITE)

        // then:
        assertThat(checkerboard.stateOf(position)).isEqualTo(CheckerboardPositionState.EXIST_WHITE_STONE)
    }
}
