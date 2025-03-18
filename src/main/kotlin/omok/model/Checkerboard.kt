package omok.model

interface Checkerboard {
    val positions: Set<Position>
}

class DefaultCheckerboard : Checkerboard {
    override val positions: Set<Position> = positions()

    val size: Int
        get() = positions.size

    private fun positions(): Set<Position> =
        (0..14)
            .flatMap { row: Int ->
                (0..14).map { column: Int ->
                    DefaultPosition(row, column)
                }
            }.toSet()
}
