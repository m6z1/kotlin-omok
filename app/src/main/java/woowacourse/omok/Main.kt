package omok

import omok.controller.OmokController
import omok.model.OmokGame
import omok.model.board.DefaultBoard
import omok.view.OmokView

fun main() {
    val omokView = OmokView()
    val board = DefaultBoard()
    val omokGame = OmokGame(board)
    OmokController(omokView, omokGame).run()
}
