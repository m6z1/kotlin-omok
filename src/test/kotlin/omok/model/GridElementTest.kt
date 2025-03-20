package omok.model

import omok.model.position.DefaultGridElement
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GridElementTest {
    @Test
    fun `0 이상의 값이 아닐 경우 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> { DefaultGridElement(-1) }
    }
}
