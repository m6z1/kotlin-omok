# kotlin-omok

# 기능 목록 정의

- 오목 게임을 할 수 있다.
    - 돌
        - [x] 돌에는 흑과 백이 있다.
    - 행, 열
        - [x] 0 이상의 값이 아닐 경우 예외를 발생시킨다.
    - 위치(좌표)
        - [x] 위치는 행과 열로 이뤄져있다.
    - 오목판 위치
        - [x] 오목판 위치에는 좌표와 상태를 가진다.
            - [x] 오목판 위치의 상태는 비어있음, 흑돌 있음, 백돌 있음중 하나이다.
    - 오목판 위치 상태
        - [x] 돌을 놓을 때, 해당 상태가 비어있지 않을 경우 예외를 발생시킨다.
        - [x] 돌을 놓을 때, 해당 상태가 비어있는 경우 흑돌을 전달하면 흑돌이 있는 상태를 반환한다.
        - [x] 돌을 놓을 때, 해당 상태가 비어있는 경우 백돌을 전달하면 백돌이 있는 상태를 반환한다.
    - 오목판
        - [x] 원하는 한 변의 길이를 받아 정사각형의 보드판을 만들 수 있다.
        - [x] 특정 위치에 있는 오목판의 상태를 알 수 있다.
        - [x] 원하는 위치에 돌을 둘 수 있다.
    - 오목룰
        - [x] 가로나 세로, 대각선으로 다섯 개의 연속된 돌을 먼저 만들면 승리한다.
    - 게임의 상태
        - [x] 흑돌의 턴에 오목을 만들면 흑돌의 승리를 반환한다.
        - [x] 흑돌의 턴에 금수를 두면 흑돌의 턴을 반환한다.
        - [x] 흑돌의 턴이 끝날 때 오목도 금수도 아닌 경우 백돌의 턴을 반환한다.
        - [x] 백돌의 턴에 오목을 만들면 백돌의 승리를 반환한다.
        - [x] 백돌의 턴이 끝날 때 오목도 장목도 아닌 경우 흑돌의 턴을 반환한다.

# 기능 플로우

1. 시작 메시지를 출력한다.
2. 오목판을 보여준다.
3. 흑부터 시작하며, 돌의 위치를 계속해서 입력받는다.
4. 새로운 돌의 위치를 입력받기 전에, 이전 돌들의 위치 오목판을 보여주고, 마지막 돌의 위치를 보여준다.
5. 가로나 세로, 대각선으로 다섯 개의 연속된 돌을 먼저 만들면 승리한다.
