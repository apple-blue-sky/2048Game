Name: Chiao J Wang
ETS: cs8bwadg
Student ID:A14206057
CSE8B Winter 2018
PSA3

Part1 
Vim related Question: 
1. How do you jump to a certain line number in your code with a single 
   command in vim? For example, how do you jump directly to line 20?
   In command line, type :20 to jump to line 20.

2. what is the commnad to undo change in vim?
   In command mode, press u to undo change.

3. How do you search for all occurrences of a word in vim?
   In commadn mode, type/(the word you want to search)

4. How do you fix the indentation of your code in vim in a single command?
   In command mode, on the certain line, press = to fix the indentation.

5. How do you switch between two files that are opened in a single vim 
   editor?
   In command mode, type :n to switch next file

6. a.In vim, in command mode, how do you move forward one word in a line 
   of code?
   In command mode, press w to move forward one word
  b.Move back one word?(Not arrow keys)
   In command mode, press b to move back one word


  Unix/Linux related Questions:
  7. How can you remove all .class files in a Unix directory with a single
     command 
     Type rm *.class to remove all .class files 

  8. a. How do you remove a Unix directory when the directory is empty?
     Type rmdir to remove a empty Unix directory

     b.How do you remove a directory when it is not empty?
     Type rm -r mydir to remove a non-empty Unix directory.

  9. List two commands to clear a Unix terminal screen 
      :clear and control L to clear a Unix terminal screen
      
  10. What are swap files? Why do they exist? How do you restore from a swap
      file? How do you delete a swap file?
      Swapping is the process whereby a page of memory is copied to the
      preconfigured space on the hard disk, called swap space.Swap is space on
      a disk that is reserved to be used as virtual memory.

      :rec (swapfile) to restore from a swap
      :rm /swapfile to remove a swap

      
Part3 Summary
   In,Board.java, i work on to set up the board and grid size, and part of them
   check if the tile can move four directions(up, down,right,left)when it move
   each direction, the tile will combine and shift to get a new number.and also
   each move, on the board, a new tile will appear. the last part of Board.java
   does check if the game is over when the board can not move to four
   directions.

   In,GameManager, i work on to setup a new board from Board.java, and in the
   play processing, it will read user's input to move direction and to make the
   game keeping going.
   
#2048GameJava
