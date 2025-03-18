package omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CheckerboardTest {
    @Test
    fun `오목판은 225개의 중복되지 않는 위치를 가진다`() {
        val checkerboard = DefaultCheckerboard()
        assertThat(checkerboard.size).isEqualTo(225)
    }
}
