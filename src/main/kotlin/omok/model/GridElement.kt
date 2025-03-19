package omok.model

interface GridElement {
    val value: Int
}

@JvmInline
value class DefaultGridElement(
    override val value: Int,
) : GridElement {
    init {
        require(value >= MIN_VALUE) { "그리드 요소는 ${MIN_VALUE}이상의 값만 가능합니다." }
    }

    companion object {
        private const val MIN_VALUE = 0
    }
}
