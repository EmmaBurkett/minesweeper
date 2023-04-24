from global_variables import *

class UserInput:
    def __init__(self):
        self.number = 2048
        self.letter = 2048
    
    def get_input(self):
        pos = input()

        for char in pos:
            character = ord(char)
            if character >= 65 and character <= 74:
                self.letter = character - 65
            elif character >= 97 and character <= 106:
                self.letter = character - 97
            elif character >= 49 and character <= 53:
                self.number = character - 49
                
        if self.number == 2048 or self.letter == 2048:
            print("Enter a number and a letter")
            self.get_input()
    
    def get_location(self):
        location = (self.number, self.letter)
        self.number = 2048
        self.letter = 2048
        return location



        