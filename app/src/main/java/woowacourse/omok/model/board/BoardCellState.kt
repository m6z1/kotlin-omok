package omok.model.board

import omok.model.Stone

sealed interface BoardCellState {
    data object Empty : BoardCellState

    enum class Exist : BoardCellState {
        Black,
        White,
    }

    fun replace(stone: Stone): Exist {
        check(this is Empty) { "이미 돌이 있습니다." }
        return when (stone) {
            Stone.BLACK -> Exist.Black
            Stone.WHITE -> Exist.White
        }
    }
}
