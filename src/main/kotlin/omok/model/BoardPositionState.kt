package omok.model

sealed interface BoardPositionState {
    data object Empty : BoardPositionState

    enum class Exist : BoardPositionState {
        Black,
        White,
    }

    fun put(stone: Stone): BoardPositionState {
        check(this is Empty) { "이미 돌이 있습니다." }
        return when (stone) {
            Stone.BLACK -> Exist.Black
            Stone.WHITE -> Exist.White
        }
    }
}
