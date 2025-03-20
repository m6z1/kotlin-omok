package omok.model

import omok.model.board.BoardPositionState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardPositionStateTest {
    @Test
    fun `돌을 놓을 때, 해당 상태가 비어있지 않을 경우 예외를 발생시킨다`() {
        // given:
        // when:
        val state = BoardPositionState.Exist.White

        // then:
        assertThrows<IllegalStateException> { state.put(Stone.BLACK) }
    }

    @Test
    fun `돌을 놓을 때, 해당 상태가 비어있는 경우 흑돌을 전달하면 흑돌이 있는 상태를 반환한다`() {
        // given:
        val state = BoardPositionState.Empty

        // when:
        val actual = state.put(Stone.BLACK)

        // then:
        assertThat(actual).isEqualTo(BoardPositionState.Exist.Black)
    }

    @Test
    fun `돌을 놓을 때, 해당 상태가 비어있는 경우 백돌을 전달하면 백돌이 있는 상태를 반환한다`() {
        // given:
        val state = BoardPositionState.Empty

        // when:
        val actual = state.put(Stone.WHITE)

        // then:
        assertThat(actual).isEqualTo(BoardPositionState.Exist.White)
    }
}
