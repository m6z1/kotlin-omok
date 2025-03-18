package omok.model

interface Position {
    val row: GridElement
    val column: GridElement
}

class DefaultPosition(
    override val row: GridElement,
    override val column: GridElement,
) : Position
