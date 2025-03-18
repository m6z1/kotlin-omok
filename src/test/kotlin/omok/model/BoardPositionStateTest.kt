package omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardPositionStateTest {
    @Test
    fun `돌을 놓을 때, 해당 상태가 비어있지 않을 경우 예외를 발생시킨다`() {
        // given:
        // when:
        val state = BoardPositionState.EXIST_BLACK_STONE

        // then:
        assertThrows<IllegalStateException> { state.put(Stone.BLACK) }
    }

    @Test
    fun `돌을 놓을 때, 해당 상태가 비어있는 경우 흑돌을 전달하면 흑돌이 있는 상태를 반환한다`() {
        // given:
        val state = BoardPositionState.EMPTY

        // when:
        val actual = state.put(Stone.BLACK)

        // then:
        assertThat(actual).isEqualTo(BoardPositionState.EXIST_BLACK_STONE)
    }

    @Test
    fun `돌을 놓을 때, 해당 상태가 비어있는 경우 백돌을 전달하면 백돌이 있는 상태를 반환한다`() {
        // given:
        val state = BoardPositionState.EMPTY

        // when:
        val actual = state.put(Stone.WHITE)

        // then:
        assertThat(actual).isEqualTo(BoardPositionState.EXIST_WHITE_STONE)
    }
}
