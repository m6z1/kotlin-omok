package omok.model

enum class Stone {
    BLACK,
    WHITE,
    ;

    fun next(): Stone =
        when (this) {
            BLACK -> WHITE
            WHITE -> BLACK
        }
}
