package assignment7;

import java.util.*;
import java.awt.Point;
import java.util.LinkedList;

public class YourMazeWithPath {

    private InputGraphicMaze maze;
    private int R, C; private int[][] V;

    public YourMazeWithPath() {
        // an R rows x C columns maze
        maze = new InputGraphicMaze();
        R = maze.Rows(); C = maze.Cols(); V = new int[R + 1][C + 1]; V[1][1] = 1; // V for visited positions in array initialize the first visited position to be 1
        // Path holds the cells of the path
        LinkedList<Point> Path = new LinkedList<Point>(); //linked list holds the path for the maze
        // Create the path
        CreatePath(maze, 1, 1, R, C, Path); //call create path and send parameters
        // show the path in the maze
        maze.showPath(Path); //displays the path created
    }

    // Creates the path through maze, starting at cell (srow, scol)
    // and ending at cell (erow and ecol),  in L
    public boolean CreatePath(InputGraphicMaze maze, int srow, int scol, int erow, int ecol, LinkedList<Point> L) {
        int r = srow, c = scol;
        Point u = new Point(r, c); //pointer u holds the position in the maze being visited
        boolean done = false; // path not created

        if ((r == erow) && (c == ecol)) { //if everything has been traversed
            done = true; //path created
        } else {
        		//case when start row has an unvisited row above and we can visit it
            if (srow > 1 && V[srow - 1][scol] != 1 && maze.can_go(srow, scol, 'U')) {
                V[srow - 1][scol] = 1; //add it to the visited array
                done = CreatePath(maze, srow - 1, scol, erow, ecol, L); //recursively pass our new position into create path
            }
            	//case when we aren't finished, our column position is left of total columns, a column exists to the right, and we can visit it 
            if (!done && scol < C && V[srow][scol + 1] != 1 && maze.can_go(srow, scol, 'R')) {
                V[r][scol + 1] = 1; //add it to visited array
                done = CreatePath(maze, srow, scol + 1, erow, ecol, L); //recursively pass the new position into create path
            }
            // case when we aren't finished, row is above that of total rows, a row exists below, and we can visit it
            if (!done && srow < R && V[srow + 1][scol] != 1 && maze.can_go(srow, scol, 'D')) {
                V[srow + 1][scol] = 1; //add it to the visited array
                done = CreatePath(maze, srow + 1, scol, erow, ecol, L); //recursively pass the new position to create path
            }
            //case when we aren't finished, column position has an unvisited path location to the left, and we can visit it 
            if (!done && scol > 1 && V[srow][scol - 1] != 1 && maze.can_go(srow, scol, 'L')) {
                V[srow][scol - 1] = 1; //add it to the visited array
                done = CreatePath(maze, srow, scol - 1, erow, ecol, L); //recursively pass the new position to create path
            }

        }
        //when finished
        if (done) { 
            L.addFirst(u); //add all parent points or visited locations to the linked list holding the path
        }
        return done; //return when finished
    }

    public static void main(String[] args) {
        new YourMazeWithPath();
    }
}