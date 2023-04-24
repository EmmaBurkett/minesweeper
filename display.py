from global_variables import *

class DisplayBoard:
    def __init__(self):
        self.board = []
    
    def create_board(self):
        for i in range(HEIGHT):
            self.board.append([])
            for j in range(WIDTH):
                self.board[i].append("*")

    def print_board(self):
        print("\n\n")
        print("   ", end="")
        for j in range(WIDTH):
            print(j + 1, "", end="")
        print()

        for i in range(HEIGHT):
            print(chr(97 + i), "", *self.board[i], sep = " ")
        
        print('')
