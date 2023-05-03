import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int width = 9;
        int height = 9;
        int bombs = 5;
        int[][] displayBoard = new int[width][height]; 
        int[][] bombsBoard = new int[width][height];
        Main myObject = new Main();
        Scanner myScan = new Scanner(System.in);
        myObject.organizeBoard(displayBoard);
        myObject.printBoard(displayBoard);        
        int[] bombsList = myObject.addBombs(bombs, width, height);
        bombsBoard = myObject.createBombBoard(bombsList, bombsBoard, width, height);
        myObject.printBombs(bombsBoard);
        while(true){
            int[] location = myObject.getCoordinates(myScan, width, height);
            displayBoard = myObject.find_zeros(displayBoard, bombsBoard, location[0], location[1], width, height, myObject);
            myObject.printBoard(displayBoard);
        }
    }

    public void organizeBoard(int[][] displayBoard){
        int boardWidth = displayBoard.length;
        int boardHeight = displayBoard.length;

        for (int i = 0; i < boardWidth; i++){
            for(int j = 0; j < boardHeight; j++){
                displayBoard[i][j] = 9;
            }
        }
        //System.out.println(Arrays.deepToString(displayBoard));
    }

    public void printBoard(int[][] displayBoard){
        int boardWidth = displayBoard.length;
        int boardHeight = displayBoard.length;
        
        System.out.print("\t");
        for (int i = 0; i < boardWidth; i++){
            System.out.print(" ");
            System.out.print(i + 1);
            System.out.print(" ");
        }
        System.out.println();

        System.out.println("\n");
        for (int i = 0; i < boardWidth; i++){
            System.out.print((char)(i+65));
            System.out.print("\t");
            for(int j = 0; j < boardHeight; j++){
                System.out.print(" ");
                System.out.print(displayBoard[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        } 
        System.out.println("\n\n");
    }

    public int[] getCoordinates(Scanner myScan, int width, int height) {
        int[] location = new int[2];
        System.out.println("Enter Coordinates: ");
        String stringCoordinates = myScan.nextLine();
        int lengthStr = stringCoordinates.length();
        for (int i=0; i < lengthStr; i++){
            int ascii = stringCoordinates.charAt(i);
            if (ascii >= 65 && ascii <=64 + height) {
                location[1] = ascii - 65;
            } 
            else if (ascii >= 97 && ascii <=96+height) {
                location[1] = ascii - 97;
            }
            if (ascii >=49 && ascii <= 48+width){
                location[0] = ascii - 49;
            }
        }
        System.out.println(Arrays.toString(location));
        return location;
    }

    public int[] addBombs(int numBombs, int width, int height) {
        int[] bombs = new int[numBombs];
        int new_bomb = 5;
        boolean inList = true;

        for (int i=0; i < numBombs; i++){
            inList = true;
            while (inList){
                new_bomb = (int)Math.floor(Math.random() * (((width * height)-1) - 0 + 1) + 0);
                inList = false;
                for (int j = 0; j < i; j++) {
                    if (new_bomb == bombs[j]) {
                        inList = true;
                    }
                }
            }
            bombs[i] = new_bomb;
        }
        return bombs;
    }

    public int[][] createBombBoard(int[] bombsLocation, int[][] bombsBoard, int width, int height){
        int y = 0;
        int x = 0;

        for (int i = 0; i < bombsLocation.length; i++){
            y = bombsLocation[i] % width;
            x = (bombsLocation[i] / width);
            bombsBoard[x][y] = 10;

            if (y + 1 < height){
                bombsBoard[x][y+1] += 1;
                if (x + 1 < width){
                    bombsBoard[x + 1][y + 1] += 1;
                }
            }
            if (x + 1 < width){
                bombsBoard[x + 1][y] += 1;
                if (y - 1 >= 0) {
                    bombsBoard[x + 1][y - 1] += 1;
                }
            }
            if (y-1 >=0){
                bombsBoard[x][y-1] += 1;
                if (x - 1 >=0){
                    bombsBoard[x-1][y-1] += 1;
                }
            }
            if (x-1 >=0){
                bombsBoard[x-1][y] += 1;
                if (y+1 < height){
                    bombsBoard[x-1][y+1] += 1;
                }
            }
        }
        return bombsBoard;
    }

    public void printBombs(int[][] displayBoard){
        int boardWidth = displayBoard.length;
        int boardHeight = displayBoard.length;
        
        System.out.print("\t");
        for (int i = 0; i < boardWidth; i++){
            System.out.print(" ");
            System.out.print(i + 1);
            System.out.print(" ");
        }
        System.out.println();

        System.out.println("\n");
        for (int i = 0; i < boardWidth; i++){
            System.out.print((char)(i+65));
            System.out.print("\t");
            for(int j = 0; j < boardHeight; j++){
                System.out.print(displayBoard[i][j]);
                if (displayBoard[i][j] < 10){
                    System.out.print(" ");
                }
                System.out.print("  ");
            }
            System.out.println();
        } 
        System.out.println("\n\n");
    }
    
    public int[][] find_zeros(int[][] displayBoard, int[][] bombsBoard, int y, int x, int width, int height, Main myObject){
        if (x < 0 || x >  width -1 || y < 0 || y > height -1){
            return displayBoard;
        }
        else if (bombsBoard[x][y] != 0){
            displayBoard[x][y] = bombsBoard[x][y];
            return displayBoard;
        }
        else if (displayBoard[x][y] != 9) {
            return displayBoard;
        }

        displayBoard[x][y] = bombsBoard[x][y];

        displayBoard = myObject.find_zeros(displayBoard, bombsBoard, y + 1, x, width, height, myObject);

        displayBoard = myObject.find_zeros(displayBoard, bombsBoard, y, x + 1, width, height, myObject);

        displayBoard = myObject.find_zeros(displayBoard, bombsBoard, y + 1, x + 1, width, height, myObject);

        displayBoard = myObject.find_zeros(displayBoard, bombsBoard, y - 1, x, width, height, myObject);

        displayBoard = myObject.find_zeros(displayBoard, bombsBoard, y, x - 1, width, height, myObject);

        displayBoard = myObject.find_zeros(displayBoard, bombsBoard, y - 1, x - 1, width, height, myObject);

        displayBoard = myObject.find_zeros(displayBoard, bombsBoard, y + 1, x - 1, width, height, myObject);

        displayBoard = myObject.find_zeros(displayBoard, bombsBoard, y - 1, x + 1, width, height, myObject);

        return displayBoard;
    }
}