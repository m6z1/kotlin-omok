package omok.model.fake

import omok.model.GridElement
import omok.model.Position

class FakePosition : Position {
    override val row: GridElement = FakeGridElement()
    override val column: GridElement = FakeGridElement()
}
