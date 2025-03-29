package woowacourse.omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.omok.model.board.Board
import woowacourse.omok.model.gameState.GameState
import woowacourse.omok.model.gameState.PutState
import woowacourse.omok.model.position.Position
import woowacourse.omok.model.testDouble.BLACK_EXIST_STONE_IF_PUT_1_3
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
        assertThat(omokGame.currentTurn).isEqualTo(GameState.Turn(Stone.BLACK))
    }

    @Test
    fun `흑돌턴 때 받은 포지션이 돌을 놓을 수 있는 경우 CanPut 을 반환한다`() {
        val board: Board = BLACK_WIN_BOARD_IF_PUT_1_5
        val omokGame = OmokGame(board)
        val position: Position = POSITION_1_5

        val actual: PutState = omokGame.checkPutState(position)

        assertThat(actual).isEqualTo(PutState.CanPutStone)
    }

    @Test
    fun `백돌턴 때 받은 포지션이 돌을 놓을 수 있는 경우 CanPut 을 반환한다`() {
        omokGame.checkPutState(Position(1, 1)) // 흑돌 턴 1
        omokGame.checkPutState(Position(8, 1)) // 백돌 턴 1
        omokGame.checkPutState(Position(1, 3)) // 흑돌 턴 2
        omokGame.checkPutState(Position(8, 2)) // 백돌 턴 2
        omokGame.checkPutState(Position(1, 4)) // 흑돌 턴 3
        omokGame.checkPutState(Position(8, 3)) // 백돌 턴 3
        omokGame.checkPutState(Position(1, 5)) // 흑돌 턴 4
        omokGame.checkPutState(Position(8, 4)) // 백돌 턴 4
        omokGame.checkPutState(Position(1, 8)) // 흑돌 턴 5
        val actual: PutState = omokGame.checkPutState(Position(8, 5)) // 백돌 턴 5

        assertThat(actual).isEqualTo(PutState.CanPutStone)
    }

    @Test
    fun `흑돌턴 때 받은 포지션이 금수일 경우 같은 ForbiddenStone 을 반환한다`() {
        omokGame.checkPutState(Position(1, 3)) // 흑돌 턴 1
        omokGame.checkPutState(Position(8, 1)) // 백돌 턴 1
        omokGame.checkPutState(Position(2, 3)) // 흑돌 턴 2
        omokGame.checkPutState(Position(8, 2)) // 백돌 턴 2
        omokGame.checkPutState(Position(3, 2)) // 흑돌 턴 3
        omokGame.checkPutState(Position(8, 3)) // 백돌 턴 3
        omokGame.checkPutState(Position(3, 4)) // 흑돌 턴 4
        omokGame.checkPutState(Position(8, 4)) // 백돌 턴 4
        val actual = omokGame.checkPutState(Position(3, 3)) // 흑돌 턴 5

        assertThat(actual).isEqualTo(PutState.ForbiddenStone)
    }

    @Test
    fun `흑돌턴 때 받은 포지션이 이미 돌이 놓여있으면 ExistStone 을 반환한다`() {
        val board = BLACK_EXIST_STONE_IF_PUT_1_3
        val omokGame = OmokGame(board)
        val position = Position(1, 3)

        val actual: PutState = omokGame.checkPutState(position)

        assertThat(actual).isEqualTo(PutState.ExistStone)
    }
}
