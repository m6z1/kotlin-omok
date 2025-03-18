package omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `오목판은 225개의 중복되지 않는 위치를 가진다`() {
        val board = DefaultBoard()
        assertThat(board.size).isEqualTo(225)
    }

    @Test
    fun `특정 위치에 있는 오목판의 상태를 알 수 있다`() {
        // given:
        val position = DefaultPosition(1, 2)
        val board = DefaultBoard(DefaultBoardPosition(position, BoardPositionState.EXIST_WHITE_STONE))

        // when:
        val actual: BoardPositionState = board.stateOf(position)

        // then:
        assertThat(actual).isEqualTo(BoardPositionState.EXIST_WHITE_STONE)
    }

    @Test
    fun `원하는 위치에 돌을 둘 수 있다`() {
        // given:
        val board = DefaultBoard()
        val position = DefaultPosition(1, 2)

        // when:
        board.put(position, Stone.WHITE)

        // then:
        assertThat(board.stateOf(position)).isEqualTo(BoardPositionState.EXIST_WHITE_STONE)
    }
}
