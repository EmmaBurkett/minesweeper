class Reveal:
    def __init__(self):
        self.bombs = []
        self.board = []
    
    def set_bombs(self, bombs_board):
        self.bombs = bombs_board
    
    def set_board(self, board):
        self.board = board
    
    def find_zeros(self, y, x):
        if x < 0 or x > 4 or y < 0 or y > 9:
            return
        elif self.bombs[y][x] != 0:
            self.board[y][x] = self.bombs[y][x]
            return
        elif self.board[y][x] == 0:
            return
        
        self.board[y][x] = self.bombs[y][x]
        
        self.find_zeros(y + 1, x)
        
        self.find_zeros(y, x + 1)

        self.find_zeros(y + 1, x + 1)

        self.find_zeros(y - 1, x)

        self.find_zeros(y, x - 1)

        self.find_zeros(y - 1, x - 1)

        self.find_zeros(y - 1, x + 1)

        self.find_zeros(y + 1, x - 1)
        