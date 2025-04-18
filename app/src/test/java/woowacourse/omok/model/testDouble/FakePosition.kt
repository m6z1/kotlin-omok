package omok.model.testDouble

import omok.model.position.GridElement
import omok.model.position.Position

class FakePosition : Position {
    override val row: GridElement = GridElement(5)
    override val column: GridElement = GridElement(5)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakePosition

        if (row != other.row) return false
        if (column != other.column) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row.hashCode()
        result = 31 * result + column.hashCode()
        return result
    }
}
