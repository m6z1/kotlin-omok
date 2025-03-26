package woowacourse.omok

import woowacourse.omok.controller.OmokController
import woowacourse.omok.model.OmokGame
import woowacourse.omok.model.board.Board
import woowacourse.omok.view.OmokView

fun main() {
    val omokView = OmokView()
    val board = Board()
    val omokGame = OmokGame(board)
    OmokController(omokView, omokGame).run()
}
