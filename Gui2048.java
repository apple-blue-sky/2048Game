/**
 * Name: Chiao Wang
 * ETC: cs8bwadg
 * Student ID:A14206057
 * Date: 02/18/2018
 * */
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;

import java.io.*;

public class Gui2048 extends Application
{
   private String outputBoard; // The filename for where to save the Board
   private Board board; // The 2048 Game Board

   private GridPane pane;

   public Rectangle[][] rec;

   public int[][] num;

   public Text[][] numText;

   public Text realScore;

   public Stage stage;






   //private static tileNum;
   /** Add your own Instance Variables here */
   @Override
      public void start(Stage primaryStage)
      {
         // Process Arguments and Initialize the Game Board
         processArgs(getParameters().getRaw().toArray(new String[0]));

         // Create the pane that will hold all of the visual objects
         pane = new GridPane();
         pane.setAlignment(Pos.CENTER);
         pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
         pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
         // Set the spacing between the Tiles
         pane.setHgap(15); 
         pane.setVgap(15);

         /** Add your Code for the GUI Here */
         //initialize the size of rec, num, numText, and stage
         //(instance variable) 
         rec = new Rectangle[board.GRID_SIZE+1][board.GRID_SIZE];
         num = new int[board.GRID_SIZE+1][board.GRID_SIZE];
         numText= new Text[board.GRID_SIZE+1][board.GRID_SIZE];
         stage = primaryStage;

         //make the title (gameName)
         Text gameName = new Text();
         gameName.setText("2048");
         gameName.setFill(Color.BLACK);
         gameName.setFont(Font.font("Helvetica Neue",
                  FontWeight.BOLD,40));
         //make the Text for score
         realScore= new Text();
         realScore.setText("Score : " +board.getScore());
         realScore.setFill(Color.BLACK);
         realScore.setFont(Font.font("Helvetica Neue",
                  FontWeight.BOLD,20));
         //put realScore on pane
         pane.add(realScore, board.GRID_SIZE-1, 0);
         //put gameName on the pane
         pane.add(gameName,0,0);

         //calling tile method to make Gui
         tile();
         //create a scene , and add pane we just created above
         Scene scene = new Scene(pane, 600, 600);
         //scene add KeyEvent
         scene.setOnKeyPressed(new KeyHandler());
         //Stage adds up title 
         primaryStage.setTitle("Gui2048");
         //Stage adds up scene
         primaryStage.setScene(scene);
         //stage display
         primaryStage.show();

      }
   //helper method for updating the score
   public void scoreNum(){
      //update the realScore text
      realScore.setText("Score : " +board.getScore());
      realScore.setFill(Color.BLACK);
      realScore.setFont(Font.font("Helvetica Neue",
               FontWeight.BOLD,20));

   }
   //make 2048 Gui at the begining of game
   public void tile(){

      //run through the whole 2D array 
      //i set up i starts from 1 because i want to make 
      //the row bigger to put the gameName and score text
      for(int i = 1; i < board.GRID_SIZE+1; i++)
      {
         for(int j=0; j <board.GRID_SIZE; j++)
         {
            //make each 2d array rec as a rectangle
            rec[i][j] = new Rectangle();
            //make each 2d array numText as a text
            numText[i][j] = new Text();
            //make int grid is the number got from board.getGrid
            int[][]grid =board.getGrid();
            //the tile is 0
            if(grid[i-1][j] == 0)
            {
               //make a rectangle
               //set up its width and height
               //and set up its color from Constants2048.java
               rec[i][j].setWidth(Constants2048.TILE_WIDTH);
               rec[i][j].setHeight(Constants2048.TILE_WIDTH);
               rec[i][j].setFill(Constants2048.COLOR_EMPTY);


               //make Text 0 and its color white
               numText[i][j].setText("  ");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,30));
               numText[i][j].setFill(Color.GRAY);


               //add tiles and text on pane 
               pane.add(rec[i][j],j,i);
               pane.add(numText[i][j],j,i);
               //make text at the middle of tiles 
               GridPane.setHalignment(numText[i][j],HPos.CENTER);
               num[i][j] = 0;

            }
            //when the tile is 2
            else if(grid[i-1][j] ==2)
            {  
               //make a rectangle
               //set up its width and height
               //and set up its color from Constants2048.java
               rec[i][j].setWidth(Constants2048.TILE_WIDTH);
               rec[i][j].setHeight(Constants2048.TILE_WIDTH);
               rec[i][j].setFill(Constants2048.COLOR_2);
               //make Text 2 and its color white 
               numText[i][j].setText("2");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
               numText[i][j].setFill(Color.GRAY);

               //add tiles and text on pane 
               pane.add(rec[i][j],j,i);
               pane.add(numText[i][j],j,i);
               //make text at the middle of tiles 
               GridPane.setHalignment(numText[i][j],HPos.CENTER);
               num[i][j] = 2;
            }
            //the tile is 4
            else if(grid[i-1][j] == 4)
            {

               //make a rectangle
               //set up its width and height
               //and set up its color from Constants2048.java
               rec[i][j].setWidth(Constants2048.TILE_WIDTH);
               rec[i][j].setHeight(Constants2048.TILE_WIDTH);
               rec[i][j].setFill(Constants2048.COLOR_4);
               //make Text 4 and its color white 
               numText[i][j].setText("4");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
               numText[i][j].setFill(Color.GRAY);
               //add tiles and text on pane 
               pane.add(rec[i][j],j,i);
               pane.add(numText[i][j],j,i);
               //make text at the middle of tiles 
               GridPane.setHalignment(numText[i][j],HPos.CENTER);
               num[i][j] = 4;
            }

         }
      }
   }




   //make the keyEvent to control the game 
   private class KeyHandler implements EventHandler<KeyEvent>{

      public void handle(KeyEvent e){
         //when isGameOver is true from board.java
         if(board.isGameOver() == true)
         {
            
            System.out.println("GameOver real");
            //calling gameOver halper method
            gameOver();
            //return and end the method 
            return;
         }
         //when press Up arrow 
         if(e.getCode()== KeyCode.UP)
         {
            //if move(Direction.UP) is true
            if(board.move(Direction.UP))
            {
               //add Random tile and update Gui
               board.addRandomTile();
               //helper method for undatign Gui
               tileUpdate();
               //update score num
               scoreNum();
            }
         }
         //press Down arrow 
         else if(e.getCode()== KeyCode.DOWN)
         {
            //if move(Direction.DOWN) is true)
            if(board.move(Direction.DOWN))
            {
               //add Random tile 
               board.addRandomTile();
               //update gui
               tileUpdate();
               //update score num
               scoreNum();
            }
         }
         // press left arrow 
         else if(e.getCode()== KeyCode.LEFT)
         {
            //if move(Direction.LEFT) is true
            if(board.move(Direction.LEFT))
            {
               //add random tile 
               board.addRandomTile();
               //update gui
               tileUpdate();
               //update score num
               scoreNum();

            }
         }
         //press rigth arrow 
         else if(e.getCode()== KeyCode.RIGHT)
         {  
            //if move(direction.left) is true
            if(board.move(Direction.RIGHT))
            {
               //add random tile 
               board.addRandomTile();
               //upadte gui
               tileUpdate();
               //unpadte score num
               scoreNum();
            }
         }
         //press S key
         else if(e.getCode() ==KeyCode.S)
         {
            try{
               //save board and print it out
               board.saveBoard(outputBoard);
               System.out.println("saving board"+ outputBoard);
               // update gui
               tileUpdate();
            }
            catch(IOException a){
               System.out.println("saveBoard threw an Exception");
            }
         }
      }

   }
   
   //helper method when game is over 
   public void gameOver()
   {
      //create a StackPane
      StackPane stack = new StackPane();
      //make rectangle and text
      Rectangle gameOverR = new Rectangle();
      gameOverR.setWidth(pane.getWidth());
      gameOverR.setHeight(pane.getHeight());
      Text gameOverT = new Text();
      gameOverT.setText("GAME OVER");
      gameOverT.setFont(Font.font("Helvetica Neue",
               FontWeight.BOLD,100));
      gameOverR.setFill(Constants2048.COLOR_GAME_OVER);

      //StackPane add up pane(GridPane),rectangle,ant text
      stack.getChildren().addAll(pane,gameOverR,gameOverT);
      //make a new scene and passing in stackpane
      Scene gameOverScene = new Scene(stack);
      //set the scene in stage 
      stage.setScene(gameOverScene);
     



     // stack.setAlignment(gameOverR,Pos.CENTER);
     // pane.setHalignment(stack,HPos.CENTER);
     
      System.out.println("GameOver!!");




   }



   //helper method to update gui
   public void tileUpdate(){
      //run throght the board
      for(int i = 1; i < board.GRID_SIZE+1; i++)
      {
         for(int j=0; j <board.GRID_SIZE; j++)

         {
            //check the number 
            int[][] grid = board.getGrid();
            //when it is 0
            if(grid[i-1][j]==0)
            {
               //update rectangle color
               rec[i][j].setFill(Constants2048.COLOR_EMPTY);
               //make Text 0 and its color white
               numText[i][j].setText("  ");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,30));
               numText[i][j].setFill(Color.GRAY);
            }
            //when it 2
            else if(grid[i-1][j]==2)
            {
               //update the rectangle color
               rec[i][j].setFill(Constants2048.COLOR_2);
               //make Text 2 and its color white 
               numText[i][j].setText("2");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
               numText[i][j].setFill(Color.GRAY);

            }
            //when it is 4
            else if(grid[i-1][j]==4)
            {
               //update the rectangle color
               rec[i][j].setFill(Constants2048.COLOR_4);
               //make Text 4 and its color white 
               numText[i][j].setText("4");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
               numText[i][j].setFill(Color.GRAY);
            }
            //when it 8
            else if(grid[i-1][j]== 8)
            {
               //update the rectangle color
               rec[i][j].setFill(Constants2048.COLOR_8);
               //make Text 8 and its color white 
               numText[i][j].setText("8");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
               numText[i][j].setFill(Color.GRAY);

            }
            //when it 16
            else if(grid[i-1][j]==16)
            {
               //update the rantangle color
               rec[i][j].setFill(Constants2048.COLOR_16);
               //make Text 16 and its color white 
               numText[i][j].setText("16");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
               numText[i][j].setFill(Color.GRAY);

            }

            else if(grid[i-1][j]== 32)
            {
               //update the rectangle color
               rec[i][j].setFill(Constants2048.COLOR_32);
               //make Text 16 and its color white 
               numText[i][j].setText("32");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
               numText[i][j].setFill(Color.GRAY);

            }
            else if(grid[i-1][j]==64)
            {
               //update the rectangle color
               rec[i][j].setFill(Constants2048.COLOR_64);
               //make Text 16 and its color white 
               numText[i][j].setText("64");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_LOW));
               numText[i][j].setFill(Color.GRAY);

            }
            else if(grid[i-1][j]== 128)
            {
               //updaet the rectangle color
               rec[i][j].setFill(Constants2048.COLOR_128);
               //make Text 16 and its color white 
               numText[i][j].setText("128");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_MID));
               numText[i][j].setFill(Color.GRAY);

            }
            else if(grid[i-1][j] == 256)
            {

               rec[i][j].setFill(Constants2048.COLOR_256);
               //make Text 16 and its color white 
               numText[i][j].setText("256");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_MID));
               numText[i][j].setFill(Color.GRAY);

            }
            else if(grid[i-1][j] == 512)
            {
               rec[i][j].setFill(Constants2048.COLOR_512);
               //make Text 16 and its color white 
               numText[i][j].setText("512");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_MID));
               numText[i][j].setFill(Color.GRAY);

            }
            else if(grid[i-1][j]== 1024)
            {
               rec[i][j].setFill(Constants2048.COLOR_1024);
               //make Text 16 and its color white 
               numText[i][j].setText("1024");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_HIGH));
               numText[i][j].setFill(Color.GRAY);

            }
            if(grid[i-1][j] == 2048)
            {

               rec[i][j].setFill(Constants2048.COLOR_2048);
               //make Text 16 and its color white 
               numText[i][j].setText("2048");
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_HIGH));
               numText[i][j].setFill(Color.GRAY);

            }

            else if(grid[i-1][j] > 2048)
            {
               rec[i][j].setFill(Constants2048.COLOR_OTHER);
               //make Text otehr number and its color white 
               int otherNum = board.getGrid()[i][j];
               String NumAsString = Integer.toString(otherNum);
               numText[i][j].setText(NumAsString);
               numText[i][j].setFont(Font.font("Helvetica Neue",
                        FontWeight.BOLD,Constants2048.TEXT_SIZE_HIGH));
               numText[i][j].setFill(Color.GRAY);

            }

         }

      }

   }





   /** Add your own Instance Methods Here */


   /** DO NOT EDIT BELOW */

   // The method used to process the command line arguments
   private void processArgs(String[] args)
   {
      String inputBoard = null;   // The filename for where to load the Board
      int boardSize = 0;          // The Size of the Board

      // Arguments must come in pairs
      if((args.length % 2) != 0)
      {
         printUsage();
         System.exit(-1);
      }

      // Process all the arguments 
      for(int i = 0; i < args.length; i += 2)
      {
         if(args[i].equals("-i"))
         {   // We are processing the argument that specifies
            // the input file to be used to set the board
            inputBoard = args[i + 1];
         }
         else if(args[i].equals("-o"))
         {   // We are processing the argument that specifies
            // the output file to be used to save the board
            outputBoard = args[i + 1];
         }
         else if(args[i].equals("-s"))
         {   // We are processing the argument that specifies
            // the size of the Board
            boardSize = Integer.parseInt(args[i + 1]);
         }
         else
         {   // Incorrect Argument 
            printUsage();
            System.exit(-1);
         }
      }

      // Set the default output file if none specified
      if(outputBoard == null)
         outputBoard = "2048.board";
      // Set the default Board size if none specified or less than 2
      if(boardSize < 2)
         boardSize = 4;

      // Initialize the Game Board
      try{
         if(inputBoard != null)
            board = new Board(new Random(), inputBoard);
         else
            board = new Board(new Random(), boardSize);
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getName() + 
               " was thrown while creating a " +
               "Board from file " + inputBoard);
         System.out.println("Either your Board(String, Random) " +
               "Constructor is broken or the file isn't " +
               "formated correctly");
         System.exit(-1);
      }
   }

   // Print the Usage Message 
   private static void printUsage()
   {
      System.out.println("Gui2048");
      System.out.println("Usage:  Gui2048 [-i|o file ...]");
      System.out.println();
      System.out.println("  Command line arguments come in pairs of the "+ 
            "form: <command> <argument>");
      System.out.println();
      System.out.println("  -i [file]  -> Specifies a 2048 board that " + 
            "should be loaded");
      System.out.println();
      System.out.println("  -o [file]  -> Specifies a file that should be " + 
            "used to save the 2048 board");
      System.out.println("                If none specified then the " + 
            "default \"2048.board\" file will be used");  
      System.out.println("  -s [size]  -> Specifies the size of the 2048" + 
            "board if an input file hasn't been"); 
      System.out.println("                specified.  If both -s and -i" + 
            "are used, then the size of the board"); 
      System.out.println("                will be determined by the input" +
            " file. The default size is 4.");
   }
}

