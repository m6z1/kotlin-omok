package omok

import omok.controller.Omok
import omok.model.OmokGame
import omok.view.OmokView

fun main() {
    val omokView = OmokView()
    val omokGame = OmokGame()
    Omok(omokView, omokGame).start()
}
