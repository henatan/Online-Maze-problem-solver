//MazeProgram
//Henok Assefa
//03/10/2020

import java.util.*;
import java.io.*;
 
public class MazeProgram {

public static void main(String[] args) throws FileNotFoundException{
    File input = new File("/Users/henokassefa/NetBeansProjects/MazeProgram/src/maze.txt");
    Scanner scan1 = new Scanner(System.in);
    Scanner readFromFile = new Scanner(input);
    char[][] maze;
    char [][] solvedMaze;
    int rowEntrance = 0;
    int ColumnEntrance = 0;
    maze = new char[20][20];
    solvedMaze = new char[20][20];
    String fileInput = "";
    for (int i = 0; i < 20; i++) {
      fileInput = readFromFile.next();
      for (int j = 0; j < 20; j++) {
        maze[i][j] = fileInput.charAt(j);
      }
    }
  
    boolean path = false;
    boolean valid = false;
    while (path == false) {
      mazeDisplay(maze);
      while (valid == false) {
      System.out.println("Please enter entrance row (must be between 0-19)");
      rowEntrance = scan1.nextInt();
      System.out.println("Please enter entrance column(must be between 0-19)");
      ColumnEntrance = scan1.nextInt();
      if (rowEntrance < 0 || ColumnEntrance < 0 || rowEntrance > 19 || ColumnEntrance > 19)
        System.out.println("Error! Not within the bound, try again");
      else if (maze[rowEntrance][ColumnEntrance] == '1')
        System.out.println("Error, cannot start at 1, try again");
      else if (maze[rowEntrance][ColumnEntrance] == 'E')
        System.out.println("Error, cannot start at entrance, try again");
      else
        valid = true;
      }
    
      for (int i = 0; i < 20; i++) {
        for (int j = 0; j < 20; j++) {
          solvedMaze[i][j] = maze[i][j];
        }
      }
      solvedMaze[rowEntrance][ColumnEntrance] = 'S';
      if (path == false) {
        if (searchForPath(solvedMaze, rowEntrance, ColumnEntrance)) {
          path = true;
          System.out.println("I AM FREE!");
          mazeDisplay(solvedMaze);
        }
        else {
          System.out.println("HELP, I AM TRAPPED!\n");
          valid = false;
        }
      }
    }
    readFromFile.close();
    scan1.close();
}

public static void mazeDisplay(char[][] maze) {
    System.out.print("   ");
    for (int k = 0; k < 20; k++)
      System.out.printf("%3d", k);
    System.out.println("\n");
    for (int i = 0; i < 20; i++) {
      System.out.printf("%3d", i);
      for (int j = 0; j < 20; j++) {
        System.out.printf("%3s", maze[i][j]);
      }
      System.out.println();
    }
}

public static boolean searchForPath(char[][] maze, int R, int C) {
  
   
    if (maze[R][C] == 'E')
      return true;
  

    if(C-1 >= 0 && C-1 <= 19 && maze[R][C-1] != '+') { 
      if (maze[R][C-1] != '1' ) { 
        if (maze[R][C-1] == 'E')
          return true;
        maze[R][C-1] = '+';
        if (searchForPath(maze, R, C-1))
          return true;
        maze[R][C-1] = '0';
      }
    }
  
   
    if(R-1 >= 0 && R-1 <= 29 && maze[R-1][C] != '+') { 
      if (maze[R-1][C] != '1' ) { 
        if (maze[R-1][C] == 'E')
          return true;
        maze[R-1][C] = '+';
        if (searchForPath(maze, R-1, C))
          return true;
        maze[R-1][C] = '0';
      }
    }
  
    if(C+1 >= 0 && C+1 <= 19 && maze[R][C+1] != '+') { 
      if (maze[R][C+1] != '1' ) { 
        if (maze[R][C+1] == 'E')
          return true;
        maze[R][C+1] = '+';
        if (searchForPath(maze, R, C+1))
          return true;
        maze[R][C+1] = '0';
      }
    }
  
    if(R+1 >= 0 && R+1 <= 19 && maze[R+1][C] != '+') { 
      if (maze[R+1][C] != '1') { 
        if (maze[R+1][C] == 'E')
          return true;
        maze[R+1][C] = '+';
        if (searchForPath(maze, R+1, C))
          return true;
        maze[R+1][C] = '0';
      }
    }
  
    return false;
}
}

