package omok.model

interface Turn {
    val current: Stone

    fun finish()
}

class DefaultTurn : Turn {
    private var _current: Stone = Stone.BLACK
    override val current: Stone get() = _current

    override fun finish() {
        _current = _current.next()
    }
}
