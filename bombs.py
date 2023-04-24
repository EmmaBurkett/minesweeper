from global_variables import *
import random

class CreateBombs:
    def __init__(self):
        self.board = []
        self.bomb_list = []

    def create_bomb_board(self):
        for i in range(HEIGHT):
            self.board.append([])
            for j in range(WIDTH):
                self.board[i].append(0)

    def randomize_bombs(self):
        rand_bomb = random.randint(0, HEIGHT * WIDTH - 1)
        self.bomb_list = [rand_bomb]
        for i in range(BOMBS):
            while rand_bomb in self.bomb_list:
                rand_bomb = random.randint(0, HEIGHT * WIDTH - 1)
            self.bomb_list.append(rand_bomb)

    def add_bombs_to_board(self):
        self._print_bombs_board()
        print("\n")
        for bomb in self.bomb_list:
            y = bomb // WIDTH
            x = bomb % WIDTH
            self.board[y][x] = 10

            if (y + 1) < (HEIGHT):
                self.board[y + 1][x] += 1
                if (x + 1) < (WIDTH):
                        self.board[y + 1][x + 1] += 1
            if (x + 1) < (WIDTH):   
                self.board[y][x + 1] += 1
                if (y - 1) >= 0:
                    self.board[y - 1][x + 1] += 1
            
            if (y - 1) >= 0:
                self.board[y - 1][x] += 1
                if (x - 1) >= 0:
                    self.board[y - 1][x - 1] += 1
            if (x - 1) >= 0:
                self.board[y][x - 1] += 1
                if (y + 1) < (HEIGHT):
                    self.board[y + 1][x - 1] += 1
            

        self._print_bombs_board()
    
    def _print_bombs_board(self):
        for i in range(HEIGHT):
            print("\n\t", end="")
            for j in range(WIDTH):
                if self.board[i][j] >= 10:
                    print(f'{self.board[i][j]}  ', end="")
                else:
                    print(f'{self.board[i][j]}   ', end="")



    def is_bomb(self, y, x):
        return (self.board[y][x] <= 10)
                
                    

            


