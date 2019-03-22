// Your file header here
/**
 * Name: Chiao Wang
 * ETC: cs8bwadg
 * Student ID:A14206057
 * Date: 02/18/2018
 * */
/**
 * Sample Board
 * <p/>
 * 0   1   2   3
 * 0   -   -   -   -
 * 1   -   -   -   -
 * 2   -   -   -   -
 * 3   -   -   -   -
 * <p/>
 * The sample board shows the index values for the columns and rows
 * Remember that you access a 2D array by first specifying the row
 * and then the column: grid[row][column]
 */

import java.util.*;
import java.io.*;

public class Board {
    public final int NUM_START_TILES = 2; 
    public final int TWO_PROBABILITY = 90;
    public final int GRID_SIZE;


    private final Random random; // a reference to the Random object, passed in 
                                 // as a parameter in Board’s constructors
    private int[][] grid;  // a 2D int array, its size being boardSize*boardSize
    private int score;     // the current score, incremented as tiles merge
   
   //printBoard is helper method for play() in GameManagerment to print to board
   //the reason why this method must write in Board.java is because we need to
   //access the score. 
    public void printBoard()
    {
       //print out the score 
       System.out.println("Score: "+this.score);
       //print out the int[i][j]for new board
       for(int i =0; i<GRID_SIZE; i++)
       {
          for(int j =0; j<GRID_SIZE;j++)
          {
             System.out.print(grid[i][j]+"\t");
          }
          System.out.println(" ");
       }

    }

   
  //MoveUp is a helper method for Move() method
      private boolean MoveUp()
      {  
         //shift all of number to the top for easiler combine numbers later
         //and if the board size is 4, at least need to shift 3 time to move all
         //nunber to the top
         //
         //so set up the shift start from the GRID_SIZE
       int shift1 =GRID_SIZE;
       //when shift one number to the top
       //the int shift will -1, the while loop will keep going until shift =1
       while(shift1 >0)
      {
            for(int i =0;i<GRID_SIZE; i++)
            {
               for(int j=0; j<GRID_SIZE;j++)
               {
                  //start from the top
                  if(i<GRID_SIZE-1)
                  {
                    
                     if(grid[i][j]==0 && grid[i+1][j]!=0)
                     {
                        //shift number to the top
                        grid[i][j] = grid[i+1][j];
                        grid[i+1][j]=0;
                     }
                  }

               }
         
           }
           //shift -1;
           shift1--;
       }

         //after shift, combine number 
         for(int i=0; i<GRID_SIZE;i++)
         {
            for(int j=0; j<GRID_SIZE;j++)
            {
               //start from the top
               if(i<GRID_SIZE-1)
               {
                  //when the last column, we don't want to check grid[i+1][j]
                  //to out of the bound 
                  //so set up i <GRID_SIZE-1.( i will be 0, 1,2,, if the board
                  //size is 4)
                   if(grid[i][j]== grid[i+1][j] && grid[i][j]!=0)
                  {
                     //check if the top number equals to the below number 
                     //if they are same, combine the two number and shift the
                     //new number to the up to avoid to combine with anoterh
                     //number again
                     grid[i][j]=grid[i][j]+grid[i+1][j];
                     grid[i+1][j]=0;
                      //update score
                      this.score += grid[i][j];
                   }
                  
                }

            }
          
         }
         //shift2
         //after combine, shift the number to the top again
       int shift2 =GRID_SIZE;
       while(shift2 >0)
      {
            for(int i =0;i<GRID_SIZE; i++)
            {
               for(int j=0; j<GRID_SIZE;j++)
               {
                  if(i<GRID_SIZE-1)
                  {
                    
                     if(grid[i][j]==0 && grid[i+1][j] !=0)
                     {
                        //start from the top
                        //if there is 0(empty space ) on the top
                        //shift all numbers to top again
                        grid[i][j] = grid[i+1][j];
                        grid[i+1][j]=0;
                     }
                     
                  }

               }
         
           }
           shift2--;
       }
         return true;
    }    

    //MoveDown is a helper method for move()
      private boolean MoveDown()
      {
        
        int shift1 =GRID_SIZE;
        while(shift1>0)
       {
          //shift down number to the bottom, and check numbers from the bottom
         for(int i=GRID_SIZE-1;i>=0;i--)
         {
            for(int j=0;j<GRID_SIZE;j++)
            {
               if(i>0)
               {
                  if(grid[i][j]==0 && grid[i-1][j]!=0)
                  {
                     grid[i][j]=grid[i-1][j];
                     grid[i-1][j]=0;
                   }
               }
            }
         }
         shift1--;
       }

         //combine
         //shift numbers to the bottom and then start combine numbers
         for(int i=GRID_SIZE-1; i>=0;i--)
         {
            //it also starts from the bottom to check numbers
            for(int j=0;j<GRID_SIZE;j++)
            {
               if(i>0)
               {
                  //(i>0), i will be check from 3, 2, 1 if the board size is 4
                  //when i =0,(the first column, we dont want to keep checking
                  //grid[i-1][j] to be out of bound
                  if(grid[i][j]==grid[i-1][j]&& grid[i][j]!=0)
                   {
                      //if the number above grid[i][j]are same, combine the two
                      //numbers
                     grid[i][j]=grid[i][j]+grid[i-1][j];
                     grid[i-1][j]=0;
                      //update score
                      this.score+= grid[i][j];
                   }
               }
            }
         }
         //Shift number to the bottom again after combine
       int shift2 =GRID_SIZE;
        while(shift2>0)
       {
         for(int i=GRID_SIZE-1;i>=0;i--)
         {
            for(int j=0;j<GRID_SIZE;j++)
            {
               if(i>0)
               {
                  if(grid[i][j]==0 && grid[i-1][j]!=0)
                  {
                     grid[i][j]=grid[i-1][j];
                     grid[i-1][j]=0;
                   }
               }
            }
         }
         shift2--;
       }
         return true;
      }

      //MoveRight is a helper method for move()
      private boolean MoveRight()
      {
         //shift number to the right side
        int shift1 =GRID_SIZE;
        while(shift1>0)
       {
         for(int i=0;i<GRID_SIZE;i++)
         {
            for(int j=GRID_SIZE-1;j>=0 ;j--)
            {
               //check numbers from rigth side
               if(j>0)
               {
                  if(grid[i][j]==0 && grid[i][j-1]!=0)
                  {
                     //move number to right side
                     grid[i][j]=grid[i][j-1];
                     grid[i][j-1]=0;
                   }
               }
            }
         }
         shift1--;
       }

         //after shift number to rigth side, and then combine numbers
         for(int i=0;i<GRID_SIZE;i++)
         {
            for(int j=GRID_SIZE-1;j>=0 ;j--)
            {
               //starts from rigth side
               if(j >0)
               {
                  if(grid[i][j]==grid[i][j-1]&&grid[i][j]!=0)
                  {
                     //if right side number equals to grid[i][j]
                     //combine the two number 
                     //and make the ritgh side numebr as 0
                     grid[i][j]=grid[i][j]+grid[i][j-1];
                     grid[i][j-1]=0;
                     //update score
                     this.score+=grid[i][j];
                   }
               }
            }
         }
         //Shift2 
         //shift number again after combine number 
        int shift2 =GRID_SIZE;
        while(shift2>0)
       {
         for(int i=0;i<GRID_SIZE;i++)
         {
            for(int j=GRID_SIZE-1;j>=0;j--)
            {
               if(j>0)
               {
                  if(grid[i][j]==0 && grid[i][j-1]!=0)
                  {
                     grid[i][j]=grid[i][j-1];
                     grid[i][j-1]=0;
                   }
               }
            }
         }
         shift2--;
       }
         return true;
      }
      //MoveLeft is a helper method for Move()
      private boolean MoveLeft()
      {
         //shift number to left side
         int shift1 =GRID_SIZE;
        while(shift1>0)
       {
         for(int i=0;i<GRID_SIZE;i++)
         {
            for(int j=0;j<GRID_SIZE;j++)
            {
               //check from left side
               if(j<GRID_SIZE-1)
               {
                  if(grid[i][j]==0 && grid[i][j+1]!=0)
                  {
                     //move all number to left side
                     grid[i][j]=grid[i][j+1];
                     grid[i][j+1]=0;
                   }
               }
            }
         }
         shift1--;
       }
         //combine number 
         for(int i=0;i<GRID_SIZE;i++)
         {
            for(int j=0;j<GRID_SIZE;j++)
            {
               //check numbers from left side
               if(j <GRID_SIZE-1)
               {
                  if(grid[i][j]==grid[i][j+1]&&grid[i][j]!=0)
                  {
                     //if the rigth side number is same as grid[i][j]
                     //combine them and new number shift to left side
                     grid[i][j]=grid[i][j]+grid[i][j+1];
                     grid[i][j+1]=0;
                     //update score
                     this.score+=grid[i][j];
                   }
               }
            }
         }
         //Shift numbers again after combine numbers
        int shift2 =GRID_SIZE;
        while(shift2>0)
       {
         for(int i=0;i<GRID_SIZE;i++)
         {
            for(int j=0;j<GRID_SIZE;j++)
            {
               if(j<GRID_SIZE-1)
               {
                  if(grid[i][j]==0 && grid[i][j+1]!=0)
                  {
                     grid[i][j]=grid[i][j+1];
                     grid[i][j+1]=0;
                   }
               }
            }
         }
         shift2--;
       }
         return true;
      }
        //canMoveUp is a helper method for canMove()
        //to check if the if can move up
      private boolean canMoveUp(int i, int j)
      {
         //dont want to check the first row to be out of bound(i will be 1,2,3)
         while(i>0)
         {
            //if the number above are same as grid[i][j]
            //it can move 
            if(grid[i][j] == grid[i-1][j]&&grid[i][j]!=0)
            {
               return true;
            }
            //if the number ablove it, it is 0
            //it can move 
            if(grid[i-1][j] == 0&&grid[i][j]!=0)
            {
               return true;
            }
            i--;
         }
         return false;
      }
      //canMoveDown is a helper method for canMove
      //to check if it can move down
      private boolean canMoveDown(int i, int j)
      {
         //check from bottom, and dont want to be out of bound(i will be 0,1,2,3
         //if the GRID_SIZE is 4)
         while(i <GRID_SIZE-1 && i >=0)
         {
            if(grid[i][j] == grid[i+1][j]&& grid[i][j]!=0)
            {
               //if the number below is same as grid[i][j]
               //it can move 
               return true;
            }
            //if the below number is 0
            //it can move 
            if(grid[i+1][j] == 0&&grid[i][j]!=0)
            {
               return true;
            }
            i--;

         }
         return false;
      }
      //canMoveLeft is a method for canMove()
      private boolean canMoveLeft(int i, int j)
      {
         //dont check when j=0 to be out of bound
         while(j>0)
         {
            //if the left side number equals to grid[i][j]
            //it can move 
            if(grid[i][j]== grid[i][j-1]&&grid[i][j]!=0)
            {
               return true;
            }
            // if the left side number is 0
            // it can move left side
            if(grid[i][j-1]==0 && grid[i][j]!=0)
            {
               return true;
            }
            //check j from 3,2,1
            j--;
         }
         return false;
      }
      //canMoveRight is a helper method for canMove()
      private boolean canMoveRight(int i, int j)
      {
         //check j from 3,2,1,not 0 the last column to be out of bound
         while(j<GRID_SIZE-1 && j>=0)
         {
            //if the rigth side number equals to grid[i][j]
            //it can move
            if(grid[i][j] == grid[i][j+1]&&grid[i][j]!=0 )
            {
               return true;
            }
            //if the right side number is 0 
            //it can move rigt side
            if(grid[i][j+1]== 0&& grid[i][j]!=0)
            {
               return true;
            }
            //check j from 3,2,1
            j--;
         }
         return false;
      }


    // TODO PSA3
    // Constructs a fresh board with random tiles
    //
    //
    public Board(Random random, int boardSize) {
       //initialize the random
        this.random = random;
        //initialize the boardSize
        GRID_SIZE = boardSize;
        //creat a new grid
        grid = new int[GRID_SIZE][GRID_SIZE];
        //add NUM_START_TILES
        for (int i = 0; i < NUM_START_TILES; i++)
           {
            //call addRandomTile
            this.addRandomTile();
           }

    }

    // TODO PSA3
    // Construct a board based off of an input file
    // assume board is valid
    public Board(Random random, String inputBoard) throws IOException {
       //load a save board
        File myFile = new File(inputBoard);
        Scanner myScanner = new Scanner(myFile);
        //initialize the random
        this.random = random;
        String GRID_SIZEstr = "";
        //read the scanner.next 
        GRID_SIZEstr  = myScanner.next();
        //convert the GRID_SIZEstr to int GRID_SIZE
        GRID_SIZE = Integer.parseInt(GRID_SIZEstr);
        
        String scoreStr = "";
        //read the scanner  next one as score
        scoreStr = myScanner.next();
        //convert scoreStr to int score
        score = Integer.parseInt(scoreStr);
        //read the next from Scanner to put in grid
        grid = new int [GRID_SIZE] [GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i ++)
        {   
            for( int j = 0; j < GRID_SIZE; j++)
            {
             String gridStr = " ";
             gridStr = myScanner.next();
             //convert gridStr to int grid[i][j]
             grid[i][j] = Integer.parseInt(gridStr);  
            }
        }
  }
    
        //this.random = random; // FIXME
        //GRID_SIZE = 0; // FIXME


    // TODO PSA3
    // Saves the current board to a file
    public void saveBoard(String outputBoard) throws IOException {
       //save the board to outputBoard file
        File myFile = new File(outputBoard);
        PrintWriter myWriter = new PrintWriter(myFile);
        //write GRID_SIZE and score to the outputBoard file
        myWriter.println(GRID_SIZE);
        myWriter.println(score);
        for(int i =0; i<GRID_SIZE ;i++)
        {
           for(int j =0; j <GRID_SIZE;j++)
           {
              //write each line of grid to outputBoard
              myWriter.print(grid[i][j]+" ");
            }
           myWriter.println(""); 
        }
        //to close the PrintWriter to save the file
        myWriter.close();

    }

    // TODO PSA3
    // Adds a random tile (of value 2 or 4) to a
    // random empty space on the board
    public void addRandomTile() {
      int count = 0;
      //get how many numbers that can add tiles
      //if the number of tile is 0
      //count ++
     for (int i = 0; i < GRID_SIZE; i++)
      {
         for (int j = 0; j < GRID_SIZE; j++)
         {
            if(grid[i][j] == 0)
            {
               count++;
            }
         }

      }
      //random add tile in the availbale tiles and called it location
      int location = random.nextInt(count);
      //get random number from 0-100 called value
      int value = random.nextInt(100);
      count =0;
      for (int i = 0; i < GRID_SIZE; i++)
      {
         for( int j = 0; j< GRID_SIZE; j++)
         {
            if(grid[i][j] == 0)
            {
               //the random available tile(count) equals to location
               if(count == location)
               {
                  //when the value is around 1-90
                  if(value < TWO_PROBABILITY)
                  {
                     // the location will be 2 in the tile
                     grid[i][j] = 2;
                  }
                  //when the value is around 91-100
                  if(value > TWO_PROBABILITY)
                  {
                     //the location will 4 in the tile
                     grid[i][j] = 4;
                  }

               }
               count++;
            }
         }
      }
      //when the count is 0, there is no room to add tiles
      if(count == 0)
      {
         //so return 
         return;
      }
   }

    // TODO PSA3
    // determins whether the board can move in a certain direction
    // return true if such a move is possible
    public boolean canMove(Direction direction){
            //when direction is up
            //for loop the grid to see if it can move up
             if(direction == Direction.UP)
             {
                for(int i = 0; i<GRID_SIZE;i++)
                { 
                   for(int j=0; j<GRID_SIZE;j++)
                  {
                     //call helper method, canMoveUp
                     if(canMoveUp(i,j))
                     {
                        //if it can move up
                        //return true
                        return true;
                     }
                
                   }
                 }
              }
             //when the direction is down
             //for loop the grid to see if it can move down
             if(direction == Direction.DOWN)
             {
                for(int i=0;i<GRID_SIZE;i++)
                {
                   for(int j = 0; j<GRID_SIZE;j++)
                   {
                      //call helper method, canMoveDown
                      if(canMoveDown(i,j))
                      {
                         //if it can move down
                         //return true
                         return true;
                      }
                   }
                }
             }
            //when the direction is left
            //for loop the grid to see if it can move left
             if(direction == Direction.LEFT)
             {
                for(int i=0; i<GRID_SIZE;i++)
                {
                   for(int j =0; j<GRID_SIZE;j++)
                   {
                      //call helper method,canMoveLeft
                      if(canMoveLeft(i,j))
                      {
                         //if it can move left
                         //return true
                         return true;
                      }
                   }
                }
             }
             //when the direction is right
             //for loop the grid to see if it can move right
             if(direction == Direction.RIGHT)
             {
                for(int i=0; i<GRID_SIZE;i++)
                {
                   for(int j=0; j<GRID_SIZE;j++)
                   {
                      //call helper method,canMoveRight
                      if(canMoveRight(i,j))
                      {
                         //if it can move right
                         ////return true
                         return true;
                      }
                   }
                }
             }
       //else return false
       //it can not move
       return false;

     }

    // TODO PSA3
    // move the board in a certain direction
    // return true if such a move is successful
    //
    // check if it can move the direction
    public boolean move(Direction direction) {
       if(direction == Direction.UP)
       {
          //check when direction is up
          //if it can move up
          if(canMove(Direction.UP))
          {
             //if it can, call helper method, called MoveUp
             //shift and combine tiles
             if(MoveUp())
             {
               return true;
             }
          }
       }
       //check when direction is down
       //if it can move down
       if(direction ==Direction.DOWN)
       {
          if(canMove(Direction.DOWN))
          {
             //if it can, call helper method, called MoveDown
             //shift and combine tiles
             if(MoveDown())
             {
                return true;
             }
          }
       }
       //check when the direction is left
       //if it can move left
       if(direction ==Direction.LEFT)
       {
          if(canMove(Direction.LEFT))
          {
             //if it can, call helper method,called MoveLeft
             //shift and combine tiles
             if(MoveLeft())
             {
                return true;
             }
          }
       }
       //check when direction is right
       //if it can move right
       if(direction ==Direction.RIGHT)
       {
          if(canMove(Direction.RIGHT))
          {
             //if it can, call helper method,called MoveRight
             //shift and combine tiles
             if(MoveRight())
             {
                return true;
             }
          }
       }
       return false; // FIXME
    }

    // No need to change this for PSA3
    // Check to see if we have a game over
    public boolean isGameOver()
    {
       //when four direction can not move
       //the game is over
       if(canMove(Direction.UP) == false && 
          canMove(Direction.DOWN) == false &&
          canMove(Direction.LEFT) == false && 
          canMove(Direction.RIGHT)  == false)
       {
          return true;
       }
      
       return false;
       
    }

    // Return the reference to the 2048 Grid
    public int[][] getGrid() {
        return grid;
    }

    // Return the score
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        outputString.append(String.format("Score: %d\n", score));
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++)
                outputString.append(grid[row][column] == 0 ? "    -" :
                        String.format("%5d", grid[row][column]));

            outputString.append("\n");
        }
        return outputString.toString();
    }
} 
