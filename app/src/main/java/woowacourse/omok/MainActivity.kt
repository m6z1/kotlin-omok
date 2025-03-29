package woowacourse.omok

import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.omok.data.DbController
import woowacourse.omok.data.DbHelper
import woowacourse.omok.model.OmokGame
import woowacourse.omok.model.Stone
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.gameState.PutState
import woowacourse.omok.model.position.Position

class MainActivity : AppCompatActivity() {
    private val board by lazy { Board() }
    private val omokGame by lazy { OmokGame(board) }
    private val dbHelper by lazy { DbHelper(this) }
    private val dbController by lazy { DbController(dbHelper) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupBoard()
        loadBoard()
        setLastTurn()
    }

    private fun setupBoard() {
        val boardLayout = findViewById<TableLayout>(R.id.board)

        boardLayout.children
            .filterIsInstance<TableRow>()
            .forEachIndexed { colIndex, row ->
                row.children
                    .filterIsInstance<ImageView>()
                    .forEachIndexed { rowIndex, view ->
                        view.setTag(R.id.cellKey, "$rowIndex,$colIndex")
                        view.setOnClickListener { playTurn(view) }
                    }
            }
    }

    private fun loadBoard() {
        val boardCells: List<Pair<String, String>> = dbController.getAllBoardCells()

        boardCells.forEach { (cellPosition, stone) ->
            val (row, col) = cellPosition.toPosition()
            board.put(cellPosition.toPosition(), stone.toStone())

            findBoardCell(row.value, col.value)?.setImageResource(stone.toDrawable())
        }
    }

    private fun findBoardCell(
        row: Int,
        col: Int,
    ): ImageView? {
        val boardLayout = findViewById<TableLayout>(R.id.board)
        return boardLayout
            .children
            .filterIsInstance<TableRow>()
            .elementAtOrNull(col)
            ?.children
            ?.filterIsInstance<ImageView>()
            ?.elementAtOrNull(row)
    }

    private fun setLastTurn() {
        val lastStone: Stone = dbController.getLastStone()?.toStone() ?: return
        omokGame.setTurn(lastStone)
    }

    private fun playTurn(view: ImageView) {
        if (!omokGame.isPlaying()) return

        val position: Position = view.getTag(R.id.cellKey).toString().toPosition()

        when (omokGame.getTurnState(position)) {
            PutState.ExistStone -> showToast("해당 위치에는 돌이 있습니다.")
            PutState.ForbiddenStone -> showToast("해당 위치는 금수입니다.")
            PutState.CanPutStone -> {
                setStone(view)
                checkWinner()
            }
        }
    }

    private fun checkWinner() {
        if (!omokGame.isPlaying()) {
            showToast("${omokGame.lastTurn.stone.toUIModel()}돌이 이겼습니다.")
        }
    }

    private fun setStone(boardCell: ImageView) {
        val stone: Stone = omokGame.lastTurn.stone
        boardCell.setImageResource(stone.toDrawable())

        dbController.updateBoardCell(
            position = boardCell.getTag(R.id.cellKey).toString(),
            stone = stone.toUIModel(),
        )
    }

    private fun String.toPosition(): Position {
        val (row, column) = split(",").map { it.toInt() }
        return Position(row, column)
    }

    private fun Stone.toUIModel(): String =
        when (this) {
            Stone.BLACK -> "흑"
            Stone.WHITE -> "백"
        }

    private fun String.toDrawable(): Int =
        when (this) {
            "흑" -> R.drawable.black_stone
            "백" -> R.drawable.white_stone
            else -> 0
        }

    private fun Stone.toDrawable(): Int =
        when (this) {
            Stone.BLACK -> R.drawable.black_stone
            Stone.WHITE -> R.drawable.white_stone
        }

    private fun String.toStone(): Stone =
        when (this) {
            "흑" -> Stone.BLACK
            "백" -> Stone.WHITE
            else -> throw IllegalArgumentException("잘못된 돌의 이름입니다.")
        }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
