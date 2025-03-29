package woowacourse.omok.model.gameState

import woowacourse.omok.model.Stone

interface GameState {
    val stone: Stone

    data class Turn(
        override val stone: Stone,
    ) : GameState

    data class Finish(
        override val stone: Stone,
    ) : GameState
}
