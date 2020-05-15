package assignment7;

// Breath-first  traversal for the path.  
import java.util.*;
import java.awt.Point;
import java.util.LinkedList;

public class MyMazeWithPath
{       
    private InputGraphicMaze maze;
    private int R, C; private int[][] V;

    public MyMazeWithPath() 
    {       
        // an R rows x C columns maze
        maze = new InputGraphicMaze();
        R=maze.Rows(); C=maze.Cols(); V=new int[R+1][C+1];
        for (int i=1; i<=R; i++)
           for (int j=1; j<=C; j++) V[i][j]=0;
        // Path holds the cells of the path
        LinkedList<Point> Path = new LinkedList<Point>();
        //Create the path
        CreatePath(maze, 1, 1, R, C, Path);
        // show the path in the maze
        maze.showPath(Path);
    }

    public boolean CreatePath(InputGraphicMaze maze,      
      int srow, int scol, int erow, int ecol, LinkedList<Point> L)
    {
        int r=srow, c=scol, //starting row and column
        R=maze.Rows(), C=maze.Cols(); int size=R*C+1; //Total area of the maze is calculated
        Point[] P=new Point[size]; //point array created in the total size of the maze
        boolean done=false; V[srow][scol]=1; //boolean is not done
        int scell=(srow-1)*C+scol; int cell; //calculate the starting cell visited
        P[scell]=new Point(0,0); Point u=new Point(r, c); //add 0,0 and the start point into the path
        LinkedList<Point> Q=new LinkedList<Point>(); Q.add(u); //linked list containing the queue      
        while ((Q.size()!=0)&&(!done)) //while Q exists and createpath not finished
        {             
           u=Q.remove(); r=(int) u.getX(); c=(int) u.getY(); //dequeue Q, retrieve x and y position
           if ((r==erow)&&(c==ecol)) done=true; //when end values reached than finish
           else
           {  
              if ((r>1)&&(V[r-1][c]!=1)&&(maze.can_go(r, c,'U'))) 
                 {V[r-1][c]=1; P[(r-2)*C+c]=u; Q.add(new Point(r-1, c));}
              if ((c<C)&&(V[r][c+1]!=1)&&(maze.can_go(r, c,'R')))           
                 {V[r][c+1]=1; P[(r-1)*C+c+1]=u; Q.add(new Point(r, c+1));}
              if ((r<R)&&(V[r+1][c]!=1)&&(maze.can_go(r, c, 'D')))             
                 {V[r+1][c]=1; P[r*C+c]=u; Q.add(new Point(r+1, c));}              
              if ((c>1)&&(V[r][c-1]!=1)&&(maze.can_go(r, c, 'L')))             
                 {V[r][c-1]=1; P[(r-1)*C+c-1]=u; Q.add(new Point(r, c-1));}
           }           
        } //end of while
        while (!u.equals(P[scell]))  //u does not equal startcell
        {         
           r=(int) u.getX(); c=(int) u.getY(); //r becomes next value, coordinates received
           L.addFirst(u); u=P[(r-1)*C+c]; //add u to the path and change u
        }
        return done;
    }
 
    public static void main(String[] args) {new MyMazeWithPath();}
}