package omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingTest {
    @Test
    fun `첫 턴은 흑돌이다`() {
        // given:
        // when:
        val turn = BlackTurn()

        // then:
        assertThat(turn.stone).isEqualTo(Stone.BLACK)
    }

    @Test
    fun `번갈아가면서 흑과 돌 상태를 가진다`() {
        // given:
        val turn = BlackTurn()

        // when:
        turn.put()

        // then:
        assertThat(turn.stone).isEqualTo(Stone.WHITE)
    }
}
