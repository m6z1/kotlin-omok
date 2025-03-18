package omok.model

enum class BoardPositionState {
    EMPTY,
    EXIST_BLACK_STONE,
    EXIST_WHITE_STONE,
    ;

    fun put(stone: Stone): BoardPositionState {
        check(this == EMPTY) { "이미 돌이 있습니다." }
        return when (stone) {
            Stone.BLACK -> EXIST_BLACK_STONE
            Stone.WHITE -> EXIST_WHITE_STONE
        }
    }
}
