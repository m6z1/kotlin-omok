# kotlin-omok

# 기능 목록 정의

- 오목 게임을 할 수 있다.
    - 돌
        - [x] 돌에는 흑/백이 있다.
    - 행, 열
        - [x] 0~14 사이의 값이 아닐 경우 예외를 발생시킨다.
    - 위치(좌표)
        - [x] 위치는 행과 열로 이뤄져있다.
    - 오목판 위치
        - [x] 오목판 위치에는 좌표와 상태를 가진다.
            - [x] 오목판 위치의 상태는 비어있음, 흑돌 있음, 백돌 있음중 하나이다.
    - 오목판
        - [x] 오목판은 225개의 중복되지 않는 위치를 가진다.
    - 오목 게임
        - [ ] 흑이 먼저 시작한다.
        - [ ] 원하는 위치에 돌을 둔다.
        - [ ] 번갈아가면서(흑/백) 돌을 둔다.
            - [ ] 돌이 이미 있는 곳에 돌을 둘 수 없다.
        - [ ] 마지막 돌의 위치를 알 수 있다.
        - [ ] 가로나 세로, 대각선으로 다섯 개의 연속된 돌을 먼저 만들면 승리한다.
            - [ ] 6목 이상의 장목도 착수 가능하며 승리 조건으로 인정한다.

# 기능 플로우

1. 시작 메시지를 출력한다.
2. 오목판을 보여준다.
3. 흑부터 시작하며, 돌의 위치를 계속해서 입력받는다.
    - 첫 위치는 반드시 정중앙으로 한다.(H8)
4. 새로운 돌의 위치를 입력받기 전에, 이전 돌들의 위치 오목판을 보여주고, 마지막 돌의 위치를 보여준다.
5. 가로나 세로, 대각선으로 다섯 개의 연속된 돌을 먼저 만들면 승리한다.

# TODO

- 포지션 생성 최적화