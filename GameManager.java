// your file header here

import java.util.*;
import java.io.*;

public class GameManager {
    // Instance variables
    private Board board; // The actual 2048 board
    private String outputBoard; // File to save the board to when exiting

    /*ec*/
    private String outputRecord; // file to save the record file, format: [size] wasdwasdwasdawsd
    StringBuilder history = new StringBuilder(); // a string of commands history
    /*ce*/


    // TODO PSA3
    // GameManager Constructor
    // Generate new game
    // 
    // Constructor in GameManager
    public GameManager(String outputBoard, int boardSize, Random random) {
       //make a new baord, and call the constructors in Board.java
       this.board = new Board(random,boardSize); 
       //initialized the outputBoard is the Private string outputBoard
       this.outputBoard = outputBoard;

      
    }

    // TODO PSA3
    // GameManager Constructor
    // Load a saved game
    //
    // load a filename,inputBoard
    public GameManager(String inputBoard, String outputBoard, Random random) throws IOException {
       //initialized the outputBoard
       this.outputBoard = outputBoard;
       //call the constructor in Board.java
       this.board = new Board(random, inputBoard);
    }
    

    
    // TODO PSA3
    // Main play loop
    // Takes in input from the user to specify moves to execute
    // valid moves are:
    //      w - Move up
    //      s - Move Down
    //      a - Move Left
    //      d - Move Right
    //      q - Quit and Save Board
    //
    //  If an invalid command is received then print the controls
    //  to remind the user of the valid moves.
    //
    //  Once the player decides to quit or the game is over,
    //  save the game board to a file based on the outputBoard
    //  string that was set in the constructor and then return
    //
    //  If the game is over print "Game Over!" to the terminal
    public void play() throws Exception {
     //print printControls 
      this.printControls();
      //called helper method, called printBoard
      board.printBoard();
      //read user's input 
       Scanner keyScanner = new Scanner(System.in);
       //make string key to get the scanner next
       String key;
       while(keyScanner.hasNext())
       {
          key=keyScanner.next();
          System.out.println("key is "+key);
          //when can move up
          if(board.canMove(Direction.UP)==true)
          {
             //the input is w/W
              if(key.equals("W")||key.equals("w"))
              {
                 //call move method
                board.move(Direction.UP);
                //call addRandomTile method
                board.addRandomTile();
                //print out a board after combine and add random tile
                board.printBoard();
              }
          }
          //when can move down
             if(board.canMove(Direction.DOWN)==true)
           {
              //the input is S/s
              if(key.equals("S")||key.equals("s"))
               {
                //call move method
                board.move(Direction.DOWN);
                //call addRandomTile method
                board.addRandomTile();
                //print out a board after combine and add random tile
                board.printBoard();
               }
           }
           //when can move left
           if(board.canMove(Direction.LEFT)==true)
           {
              //the input is A/a
             if(key.equals("A")||key.equals("a"))
             {
                //call move method
                board.move(Direction.LEFT);
                //call addRandomTile method
                board.addRandomTile();
                //print out a board after combine and add random tile
                board.printBoard();
             }
           }
           //when can move right
              if(board.canMove(Direction.RIGHT)==true)
             {
                //the input is D/d
               if(key.equals("D")||key.equals("d"))
                {
                   //call move method
                   board.move(Direction.RIGHT);
                   //call addRandomTile method
                   board.addRandomTile();
                   //print out a board after combine adn add random tile
                   board.printBoard();
                }
             }
             //the input is Q/q
             if(key.equals("Q")||key.equals("q"))
            {
               //save the board, called saveBoard method
               board.saveBoard(outputBoard);
               return;
             }
             //when the four direction can not move 
            if(board.canMove(Direction.UP)==false&&
               board.canMove(Direction.DOWN)==false&&
               board.canMove(Direction.LEFT)==false&&
               board.canMove(Direction.RIGHT)==false)
            {
               //print our Game Over 
               System.out.println("Game Over");
            }
            else
            {
               //when key is not WASDQ
              if(!key.equals("W")&&!key.equals("w")&&
                 !key.equals("S")&&!key.equals("s")&&
                 !key.equals("A")&&!key.equals("a")&&
                 !key.equals("D")&&!key.equals("d")&&
                 !key.equals("Q")&&!key.equals("q"))
              {
                 //print out the printControl
               this.printControls();
            }
          }
    }
    
}


    // Print the Controls for the Game
    private void printControls() {
        System.out.println("  Controls:");
        System.out.println("    w - Move Up");
        System.out.println("    s - Move Down");
        System.out.println("    a - Move Left");
        System.out.println("    d - Move Right");
        System.out.println("    q - Quit and Save Board");
        System.out.println();
    }


   
}
