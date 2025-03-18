package omok.model

interface GridElement {
    val value: Int
}

@JvmInline
value class DefaultGridElement(
    override val value: Int,
) : GridElement {
    init {
        require(value in MIN_VALUE..MAX_VALUE)
    }

    companion object {
        private const val MIN_VALUE = 0
        private const val MAX_VALUE = 14
    }
}
