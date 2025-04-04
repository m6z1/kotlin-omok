package omok.model

import omok.model.board.BoardCellState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class BoardCellStateTest {
    @Test
    fun `오목판 위치의 상태는 비어있음, 흑돌 있음, 백돌 있음 중 하나이다`() {
        assertAll(
            { assertThat(BoardCellState.Empty).isInstanceOf(BoardCellState::class.java) },
            { assertThat(BoardCellState.Exist.Black).isInstanceOf(BoardCellState::class.java) },
            { assertThat(BoardCellState.Exist.White).isInstanceOf(BoardCellState::class.java) },
        )
    }

    @Test
    fun `돌을 놓을 때, 해당 상태가 비어있지 않을 경우 예외를 발생시킨다`() {
        // given:
        // when:
        val state = BoardCellState.Exist.White

        // then:
        assertThrows<IllegalStateException> { state.replace(Stone.BLACK) }
    }

    @Test
    fun `돌을 놓을 때, 해당 상태가 비어있는 경우 흑돌을 전달하면 흑돌이 있는 상태를 반환한다`() {
        // given:
        val state = BoardCellState.Empty

        // when:
        val actual = state.replace(Stone.BLACK)

        // then:
        assertThat(actual).isEqualTo(BoardCellState.Exist.Black)
    }

    @Test
    fun `돌을 놓을 때, 해당 상태가 비어있는 경우 백돌을 전달하면 백돌이 있는 상태를 반환한다`() {
        // given:
        val state = BoardCellState.Empty

        // when:
        val actual = state.replace(Stone.WHITE)

        // then:
        assertThat(actual).isEqualTo(BoardCellState.Exist.White)
    }
}
