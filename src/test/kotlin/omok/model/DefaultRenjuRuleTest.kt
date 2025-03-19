package omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultRenjuRuleTest {
    @Test
    fun `가로로 다섯 개의 연속된 돌을 먼저 만들면 승리 상태를 반환한다`() {
        // given:
        val board = DefaultBoard()
        val renjuRule = DefaultRenjuRule(board)

        // when:
        board.put(1, 1, Stone.BLACK)
        board.put(2, 1, Stone.BLACK)
        board.put(3, 1, Stone.BLACK)
        board.put(4, 1, Stone.BLACK)
        board.put(5, 1, Stone.BLACK)

        // then:
        assertThat(renjuRule.gameState(DefaultPosition(5, 1))).isEqualTo(GameState.BLACK_WIN)
    }

    @Test
    fun `세로로 다섯 개의 연속된 돌을 먼저 만들면 승리 상태를 반환한다`() {
        // given:
        val board = DefaultBoard()
        val renjuRule = DefaultRenjuRule(board)

        // when:
        board.put(1, 1, Stone.BLACK)
        board.put(1, 2, Stone.BLACK)
        board.put(1, 3, Stone.BLACK)
        board.put(1, 4, Stone.BLACK)
        board.put(1, 5, Stone.BLACK)

        // then:
        assertThat(renjuRule.gameState(DefaultPosition(1, 5))).isEqualTo(GameState.BLACK_WIN)
    }

    @Test
    fun `대각선으로 다섯 개의 연속된 돌을 먼저 만들면 승리 상태를 반환한다`() {
        // given:
        val board = DefaultBoard()
        val renjuRule = DefaultRenjuRule(board)

        // when:
        board.put(1, 1, Stone.BLACK)
        board.put(2, 2, Stone.BLACK)
        board.put(3, 3, Stone.BLACK)
        board.put(4, 4, Stone.BLACK)
        board.put(5, 5, Stone.BLACK)

        // then:
        assertThat(renjuRule.gameState(DefaultPosition(5, 5))).isEqualTo(GameState.BLACK_WIN)
    }
}
