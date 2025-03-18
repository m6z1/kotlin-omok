package omok.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GridElementTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 15])
    fun `0~14 사이의 값이 아닐 경우 예외를 발생시킨다`(
        value: Int
    ) {
        assertThrows<IllegalArgumentException> { GridElement(value) }
    }
}
