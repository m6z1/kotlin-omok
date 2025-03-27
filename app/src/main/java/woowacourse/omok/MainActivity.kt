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
import woowacourse.omok.model.OmokGame
import woowacourse.omok.model.Stone
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.position.Position

class MainActivity : AppCompatActivity() {
    private val omokGame = OmokGame(Board())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val board = findViewById<TableLayout>(R.id.board)

        board
            .children
            .filterIsInstance<TableRow>()
            .forEachIndexed { colIndex, column ->
                column.children
                    .filterIsInstance<ImageView>()
                    .forEachIndexed { rowIndex, view ->
                        view.setOnClickListener {
                            playTurn(rowIndex, colIndex, view)
                        }
                    }
            }
    }

    private fun playTurn(
        row: Int,
        column: Int,
        view: ImageView,
    ) {
        if (!omokGame.isPlaying()) return

        val position = Position(row, column)
        omokGame.getTurnState(position)

        if (omokGame.forbiddenPosition()) {
            Toast.makeText(this, "해당 위치는 금수입니다.", Toast.LENGTH_SHORT).show()
            return
        }

        when (omokGame.lastTurn.stone) {
            Stone.BLACK -> view.setImageResource(R.drawable.black_stone)
            Stone.WHITE -> view.setImageResource(R.drawable.white_stone)
        }

        if (!omokGame.isPlaying()) {
            Toast
                .makeText(
                    this,
                    "${omokGame.lastTurn.stone.toUIModel()}돌이 이겼습니다.",
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }

    private fun Stone.toUIModel(): String =
        when (this) {
            Stone.BLACK -> "흑"
            Stone.WHITE -> "백"
        }
}
