package omok.model

@JvmInline
value class GridElement(private val value: Int) {
    init {
        require(value in MIN_VALUE..MAX_VALUE)
    }

    companion object {
        private const val MIN_VALUE = 0
        private const val MAX_VALUE = 14
    }
}
