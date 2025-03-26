package woowacourse.omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.gameState.BlackTurn
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.gameState.WhiteTurn
import woowacourse.omok.model.position.Position
import woowacourse.omok.model.testDouble.BLACK_WIN_BOARD_IF_PUT_1_5
import woowacourse.omok.model.testDouble.POSITION_1_5

class OmokGameTest {
    private lateinit var board: Board
    private lateinit var omokGame: OmokGame

    @BeforeEach
    fun setup() {
        board = Board()
        omokGame = OmokGame(board)
    }

    @Test
    fun `첫 턴은 흑돌턴이다`() {
        assertThat(omokGame.currentTurn).isEqualTo(BlackTurn)
    }

    @Test
    fun `흑돌턴 때 받은 포지션이 오목을 만드는 경우 흑돌의 Finish를 반환한다`() {
        val board: Board = BLACK_WIN_BOARD_IF_PUT_1_5
        val omokGame = OmokGame(board)
        val position: Position = POSITION_1_5

        val actual: GameState = omokGame.getTurnState(position)

        assertThat(actual).isEqualTo(GameState.Finish(Stone.BLACK))
    }

    @Test
    fun `백돌턴 때 받은 포지션이 오목을 만드는 경우 백돌의 Finish를 반환한다`() {
        omokGame.getTurnState(Position(1, 1)) // 흑돌 턴 1
        omokGame.getTurnState(Position(8, 1)) // 백돌 턴 1
        omokGame.getTurnState(Position(1, 3)) // 흑돌 턴 2
        omokGame.getTurnState(Position(8, 2)) // 백돌 턴 2
        omokGame.getTurnState(Position(1, 4)) // 흑돌 턴 3
        omokGame.getTurnState(Position(8, 3)) // 백돌 턴 3
        omokGame.getTurnState(Position(1, 5)) // 흑돌 턴 4
        omokGame.getTurnState(Position(8, 4)) // 백돌 턴 4
        omokGame.getTurnState(Position(1, 8)) // 흑돌 턴 5
        val actual: GameState = omokGame.getTurnState(Position(8, 5)) // 백돌 턴 5

        assertThat(actual).isEqualTo(GameState.Finish(Stone.WHITE))
    }

    @Test
    fun `흑돌턴 때 받은 포지션이 금수일 경우 같은 흑돌턴을 반환한다`() {
        omokGame.getTurnState(Position(1, 3)) // 흑돌 턴 1
        omokGame.getTurnState(Position(8, 1)) // 백돌 턴 1
        omokGame.getTurnState(Position(2, 3)) // 흑돌 턴 2
        omokGame.getTurnState(Position(8, 2)) // 백돌 턴 2
        omokGame.getTurnState(Position(3, 2)) // 흑돌 턴 3
        omokGame.getTurnState(Position(8, 3)) // 백돌 턴 3
        omokGame.getTurnState(Position(3, 4)) // 흑돌 턴 4
        omokGame.getTurnState(Position(8, 4)) // 백돌 턴 4
        val actual = omokGame.getTurnState(Position(3, 3)) // 흑돌 턴 5

        assertThat(actual).isEqualTo(BlackTurn)
    }

    @Test
    fun `흑돌턴 때 받은 포지션이 흑돌을 놓을 수 있으면 흑돌턴을 반환한다`() {
        val actual: GameState = omokGame.getTurnState(Position(1, 3))

        assertThat(actual).isEqualTo(WhiteTurn)
    }

    @Test
    fun `백돌턴 때 받은 포지션이 백돌을 놓을 수 있으면 흑돌턴을 반환한다`() {
        omokGame.getTurnState(Position(8, 1)) // 흑돌턴
        val actual: GameState = omokGame.getTurnState(Position(8, 2)) // 백돌턴

        assertThat(actual).isEqualTo(BlackTurn)
    }
}
