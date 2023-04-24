from global_variables import *
from display import *
from input import UserInput
from bombs import *
from reveal import Reveal

class StartGame:
    """ Bord is (y, x) input is (x, y) #cry
    """
    def __init__(self):
        self.display_board = DisplayBoard()
        self.user_input = UserInput()
        self.bomb_board = CreateBombs()
        self.reveal_squares = Reveal()
        self.still_playing = True
    
    def start(self):
        self.display_board.create_board()
        self.bomb_board.create_bomb_board()
        self.bomb_board.randomize_bombs()
        self.bomb_board.add_bombs_to_board()
        self.reveal_squares.set_bombs(self.bomb_board.board)

        while self.still_playing:
            self.round()

    def round(self):
        self.display_board.print_board()
        self.user_input.get_input()
        pos = self.user_input.get_location()
        self.still_playing = self.bomb_board.is_bomb(pos[1],pos[0])
        self.reveal_squares.set_board(self.display_board.board)
        self.reveal_squares.find_zeros(pos[1],pos[0])


    
potato = StartGame()
potato.start()
