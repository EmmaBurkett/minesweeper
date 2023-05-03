import java.util.Scanner; // import the Scanner class 


public class Main {
    public static void main(String[] args) {
        int hiddenDisplayWidth = 10; //width
        int hiddenDisplayHight = 10; //height
        int universalLength = hiddenDisplayHight * hiddenDisplayWidth; //100 squares
        int universalLengthBombs = universalLength / 10; //1 bomb to every ten
        String[][] hiddenDisplay = new String[hiddenDisplayHight][hiddenDisplayWidth]; //what is displayed to the user (UI)
        int[] uniqueBombLocationsCheck = new int[universalLength]; // an array filled with incrimenting numbers starting from 0
        int[] bombArray = new int[universalLength]; //bomb array for bombs to go into
        int[][] bombArrayDouble = new int[hiddenDisplayHight][hiddenDisplayWidth];//; {{0,0,1}, {0,0,0}, {0,0,0}}; //bomb array that is filled with 1's and 0's 1 = bomb 0 = not a bomb (displayed to the user for testing purposes)
        Main myObject = new Main(); 
        myObject.populateHiddenDisplay(hiddenDisplay); //fill with squares
        myObject.uniqueBombPositionsCheck(uniqueBombLocationsCheck); //fill and array to make sure that bombs are random and are in different locations
        myObject.getBombs(hiddenDisplay, uniqueBombLocationsCheck, bombArray, universalLength, universalLengthBombs); // get bombs and use uniqueBombLocationCheck array to make sure that bombs are in different locations.
        myObject.getbombArrayDouble(bombArray, bombArrayDouble); //pass the single array filled with bombs into a double array 
        //myObject.printBombArray(bombArray, hiddenDisplayWidth);
        myObject.game(hiddenDisplay, uniqueBombLocationsCheck, bombArrayDouble, universalLength); //the game
    }
    
    public void game(String[][] hiddenDisplay, int[] uniqueBombLocationsCheck, int[][] bombArrayDouble, int universalLength){
        Scanner myObject = new Scanner(System.in);
        Main myMainObject = new Main();
        CheckSquares recursionObject = new CheckSquares();
        

        myMainObject.printBombArrayDouble(bombArrayDouble);
        myMainObject.printDisplay(hiddenDisplay);

        //get user input
        int yAxis; 
        int xAxis;
        
        while(true){ //goes forever until a bomb is selected
            System.out.println("Enter a Letter: "); // x-axis location
            char xAxisLetter = myObject.next().charAt(0); //make a letter a char
            xAxis = ((int) xAxisLetter) - 65; //make the number in range
            System.out.println("Enter a Number: "); // get the y-axis location
            yAxis = myObject.nextInt();
            
            //if bomb then lose game
            if (bombArrayDouble[yAxis][xAxis] == 1) {//bombArrayDouble holds all the bombs (1's and 0's)
                System.out.println("Game Over.");
                break; //exit loop
            }
            else {
                myMainObject.isBombsClose(hiddenDisplay, bombArrayDouble, yAxis, xAxis); // check location for surrounding bombs
                if (hiddenDisplay[yAxis][xAxis] == "0"){ //if there are not any bombs around then reveal squares until there are bombs
                    recursionObject.moveDownAndRight(hiddenDisplay, bombArrayDouble, xAxis, yAxis); //recures to check squares for surrounding bombs
                    recursionObject.moveUpAndLeft(hiddenDisplay, bombArrayDouble, xAxis, yAxis); //recures as well
                }
            }
            myMainObject.printBombArrayDouble(bombArrayDouble); //prints the bomb locations
            myMainObject.printDisplay(hiddenDisplay); //prints the user interface
        }
        myObject.close(); //close the input object
    
    }


    public void printDisplay(String[][] hiddenDisplay) { //prints the hiddenDisplay array
        char letter;
        System.out.print("     ");
        for (int i = 0; i < hiddenDisplay.length; i++){
            letter = (char)(i + 65);//letters at the top
            System.out.print(letter + " ");
        }
        System.out.println();
        for (int i = 0; i < hiddenDisplay.length; i++){ //prints the display for the user
            if (i < 10) //proper formatting
                System.out.print("  " + i + "  "); //numbers at the top
            else 
                System.out.print("  " + i + " ");
            for (int j = 0; j < hiddenDisplay[i].length; j++) { //double array needs two for loops to cycle through
                System.out.print(hiddenDisplay[i][j] + " "); //print hiddenDisplay
            }
            System.out.println(); //keep the lines in format
        }
        System.out.println(""); //print anything just in case
    }

    public String[][] populateHiddenDisplay(String[][] hiddenDisplay){
        for (int i = 0; i < hiddenDisplay.length; i++){ //prints the display for the user
            for (int j = 0; j < hiddenDisplay[i].length; j++) {
                hiddenDisplay[i][j] = "\u25A0"; // put blocks into the hiddenDisplay array which is displayed to the user.
            }
        }
        return hiddenDisplay;
    }

    public int[] uniqueBombPositionsCheck(int[] uniqueBombLocationsCheck){
        for (int i = 0; i < uniqueBombLocationsCheck.length; i++) { //fills the array that keeps track of bomblocations and makes sure that nothing is repeated. 
            uniqueBombLocationsCheck[i] = i; //this keeps everything maliable for different board sizes.
        }
        return uniqueBombLocationsCheck;// an array filled with 0 - 100 (or whatever size board)
    }

    public int[] getBombs(String[][] hiddenDisplay, int[] uniqueBombLocationsCheck, int[] bombArray, int universalLength, int universalLengthBombs){
        int bombPosition;

        Main myObject = new Main();
        
        for (int j = 0; j < (universalLengthBombs);){ //fills the array of bombs
            bombPosition = myObject.bombs(universalLength);
            for (int i = 0; i < uniqueBombLocationsCheck.length; i++){ //makes sure that only numbers that are in the uniqueBombLocationsCheck array are included once in the bombLocations array
                if (bombPosition == uniqueBombLocationsCheck[i]){
                    uniqueBombLocationsCheck[i] = universalLength; //this deletes the possibility of recurring numbers - specially because we start from zero lol 
                    j++;
                    bombArray[bombPosition] = 1;
                }
            }
        }
        return bombArray;
    }

    public int[][] getbombArrayDouble(int[] bombArray, int[][] bombArrayDouble){
        for (int y = 0; y < bombArrayDouble.length; y++){ //prints the display for the user
            for(int x = 0; x < bombArrayDouble[y].length; x++)
                bombArrayDouble[y][x] = bombArray[x + (y * bombArrayDouble.length)]; //transfers a one dimensional array into a two-d array. I thought this would make it more random - I honestly don't know why but that's what I have.
        }
        return bombArrayDouble; //return a 2-d array for bombs
    }

    public void printBombArrayDouble(int[][] bombArrayDouble) { 
        char letter;
        System.out.print("     ");
        for (int i = 0; i < bombArrayDouble.length; i++){ //print with letters above
            letter = (char)(i + 65);
            System.out.print(letter + " ");
        }
        System.out.println();
        for (int i = 0; i < bombArrayDouble.length; i++){ //prints the display for the user
            if (i < 10) //print with numbers next to it
                System.out.print("  " + i + "  ");
            else 
                System.out.print("  " + i + " ");
            for (int j = 0; j < bombArrayDouble[i].length; j++) { //print the array inside the array
                System.out.print(bombArrayDouble[i][j] + " "); 
            }
            System.out.println(); //print it all on the same line lol
        }
        System.out.println(""); // print a new line to give space
    }

    public int bombs(int universalLength) { // randomize bomb placement
        int bombPosition = (int)(Math.random() * (universalLength - 1));  // 0 to 100
        return bombPosition;
    }

    public String[][] isBombsClose(String[][] hiddenDisplay, int[][] bombArrayDouble, int yAxis, int xAxis){// check around square(xAxis, yAxis) for bombs - put results in the hiddenDisplay array for the user to see when called.
        Main myMainObject = new Main();
        int numberOfBombs = myMainObject.isBombsCloseCheck(hiddenDisplay, bombArrayDouble, yAxis, xAxis); // get number of surrounding bombs
        if (numberOfBombs == 0){ //if num of bombs is zero put a zero to display to user later
            hiddenDisplay[yAxis][xAxis] = "0";
        }
        else {// else put that number into the hiddenDisplay array to display to user later
            String numberOfBombs_Display = String.valueOf(numberOfBombs);  //make int a string
            hiddenDisplay[yAxis][xAxis] = numberOfBombs_Display; //put into array
        }
        return hiddenDisplay; //return hiddenDisplay
    }

    public int isBombsCloseCheck(String[][] hiddenDisplay, int[][] bombArrayDouble, int yAxis, int xAxis){ // check around square(xAxis, yAxis) for bombs - this is used later as a boolean.
        int numberOfBombs = 0; //number of surrounding bombs around square(x, y)
        if (yAxis != 0 && xAxis != 0 && bombArrayDouble[yAxis - 1][xAxis - 1] == 1){ //top left corner
            numberOfBombs += 1;
        }
        if (yAxis != 0 && bombArrayDouble[yAxis - 1][xAxis] == 1){ //top
            numberOfBombs += 1;
        }
        if (yAxis != 0 && xAxis != (bombArrayDouble[yAxis].length -1) && bombArrayDouble[yAxis - 1][xAxis + 1] == 1){ //top right corner
            numberOfBombs += 1;
        }
        if (xAxis != 0 && bombArrayDouble[yAxis][xAxis - 1] == 1){ // left side
            numberOfBombs += 1;
        }
        if (yAxis != (bombArrayDouble.length -1) && xAxis != (bombArrayDouble[yAxis].length -1) && bombArrayDouble[yAxis + 1][xAxis + 1] == 1){ //bottom right corner
            numberOfBombs += 1;
        }
        if (yAxis != (bombArrayDouble.length - 1) && bombArrayDouble[yAxis + 1][xAxis] == 1){ //bottom
            numberOfBombs += 1;
        }
        if (yAxis != (bombArrayDouble.length -1) && xAxis != 0 && bombArrayDouble[yAxis + 1][xAxis - 1] == 1){ //bottom left corner
            numberOfBombs += 1;
        }
        if (xAxis != (bombArrayDouble[yAxis].length -1) && bombArrayDouble[yAxis][xAxis + 1] == 1){ // right side
            numberOfBombs += 1;
        }
        return numberOfBombs;
    }
}
