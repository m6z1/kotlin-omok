package omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TurnTest {
    @Test
    fun `첫 턴은 흑돌이다`() {
        // given:
        // when:
        val turn = DefaultTurn()

        // then:
        assertThat(turn.current).isEqualTo(Stone.BLACK)
    }

    @Test
    fun `번갈아가면서 흑과 돌 상태를 가진다`() {
        // given:
        val turn = DefaultTurn()

        // when:
        turn.finish()

        // then:
        assertThat(turn.current).isEqualTo(Stone.WHITE)
    }
}
