public class CheckSquares { //reveal squares that are not near a bomb
    /*
    * I had to make two functions for decreasing and increasing because when I tried to put 
    * it all into one function it made an infinte loop. 
    * It is still not perfect logic, unfortunatly.
    */




    public void moveDownAndRight(String[][] hiddenDisplay, int[][] bombArrayDouble, int x, int y){
        Main myObject = new Main(); //call main
        int check = 0; //check is to make sure that the next recursion isn't near a bomb
        if (y >= 0 && y < bombArrayDouble.length && x >= 0 && x < bombArrayDouble[y].length){ //keep x and y in bounds
            myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y, x); //check for bombs around square(x, y)
            if (y > 0) //keep y in bounds
                myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y - 1, x); //check bombs around square(x, y - 1) and reveal to user
            if (y < bombArrayDouble.length - 1) //Keep y in bounds
                myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y + 1, x); //check bombs around square (x, y + 1)and reveal to user
            if (x > 0) //keep x in bounds
                myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y, x - 1); //check bombs around square(x - 1, y) and reveal to user
            if (x < bombArrayDouble[y].length - 1) //keep x in bounds
                myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y, x + 1); //check bombs around square(x + 1, y) and reveal to user
            if (y < bombArrayDouble.length - 1 && x < bombArrayDouble[y].length - 1) //keep x and y in bounds
                check = myObject.isBombsCloseCheck(hiddenDisplay, bombArrayDouble, y + 1, x + 1); //check bombs around square(x + 1, y + 1) pass into a boolean
        }
        if (y < bombArrayDouble.length && x < bombArrayDouble[y].length - 1 && hiddenDisplay[y][x + 1] == "0") //keep y and x in bounds and do not recure if a bomb is near square(x + 1, y)
            moveDownAndRight(hiddenDisplay, bombArrayDouble, x + 1, y); // recure pass in (x + 1) and y
        if (y < bombArrayDouble.length - 1 && x < bombArrayDouble[y].length && hiddenDisplay[y + 1][x] == "0")// keep y and x in bounds and do not recure if bomb is near square (x, y+1)
            moveDownAndRight(hiddenDisplay, bombArrayDouble, x, y + 1); //recure pass in x and (y + 1)
        if (y < bombArrayDouble.length - 1 && x < bombArrayDouble[y].length - 1 && check == 0) // keep y and x in bounds and do not recure if a bomb is near square(x+1, y+1) - boolean here
            moveDownAndRight(hiddenDisplay, bombArrayDouble, x + 1, y + 1); //recure pass in x and (y + 1)
    }

    
    public void moveUpAndLeft(String[][] hiddenDisplay, int[][] bombArrayDouble, int x, int y){
        Main myObject = new Main();
        int check = 0;
        if (y >= 0 && y < bombArrayDouble.length && x >= 0 && x < bombArrayDouble[y].length){ //same as above
            myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y, x);
            if (y > 0)
                myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y - 1, x);
            if (y < bombArrayDouble.length - 1)
                myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y + 1, x);
            if (x > 0)
                myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y, x - 1);
            if (x < bombArrayDouble[y].length - 1)
                myObject.isBombsClose(hiddenDisplay, bombArrayDouble, y, x + 1);
            if (x > 0 && y > 0) // keep x and y in bounds
                check = myObject.isBombsCloseCheck(hiddenDisplay, bombArrayDouble, y - 1, x - 1); //check bombs around square(x - 1, y - 1) pass into a boolean
        }
        if (y >= 0 && x > 0 && hiddenDisplay[y][x - 1] == "0") // keep  x and y in bounds and only call function if square(x - 1, y) is not near a bomb
            moveDownAndRight(hiddenDisplay, bombArrayDouble, x - 1, y); // call the other move function
        if (y > 0 && x >= 0 && hiddenDisplay[y - 1][x] == "0") // keep  x and y in bounds and only call function if square(x, y - 1) is not near a bomb
            moveDownAndRight(hiddenDisplay, bombArrayDouble, x, y - 1); //call the other move function
        if (y > 0 && hiddenDisplay[y - 1][x] == "0") // keep  x and y in bounds and only recur if square(x, y - 1) is not near a bomb
            moveUpAndLeft(hiddenDisplay, bombArrayDouble, x, y - 1); // recure this function
        if (y >= 0 && x > 0 && hiddenDisplay[y][x - 1] == "0") // keep  x and y in bounds and only recur if square(x - 1, y) is not near a bomb
            moveUpAndLeft(hiddenDisplay, bombArrayDouble, x - 1, y); // recure this function
        if (y > 0 && x > 0 && check == 0) // keep  x and y in bounds and only recur if square(x - 1, y - 1) is not near a bomb
            moveUpAndLeft(hiddenDisplay, bombArrayDouble, x - 1, y - 1); //recure this function
    }


}
