package problem.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-knight-moves/
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
 * then one square in an orthogonal direction.
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 */
public class MinimumKnightMoves_1197 {

    class Pair {
        int row,col,dist;
        Pair(int row,int col,int dist){
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
    int[] dirRow = {2,1,-1,-2,-2,-1,1,2};
    int[] dirCol = {1,2,2,1,-1,-2,-2,-1};

    private boolean isSafe(int x,int y){
        if (x < 0 || y < 0 || x >300 || y >300)
            return false;
        return true;
    }

    private int BFS(int row,int col, int x, int y){
        int dist = 0;
        boolean[][] visited = new boolean[301][301];
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(row,col,0));

        while(!queue.isEmpty()){
            Pair cell = queue.poll();

            if(cell.row == x && cell.col == y)
                return cell.dist;

            if (!visited[cell.row][cell.col])
            {
                visited[cell.row][cell.col] = true;
                for(int i = 0; i < 8; i++){
                    if(isSafe(cell.row+ dirRow[i],cell.col+ dirCol[i])){
                        queue.add(new Pair(cell.row+ dirRow[i],cell.col+ dirCol[i],cell.dist+1));
                    }
                }
            }

        }
        return -1;
    }

    public int minKnightMoves(int x, int y) {
        //handling edge cases
        if(x == 0 && y == 0) return 0;
        if(Math.abs(x) == 1 && Math.abs(y) == 1) return 2;

        // even if the (-x, -y) are at same distance as +ve (x, y) from (0, 0)
        return BFS(0,0, Math.abs(x), Math.abs(y));
    }
}
