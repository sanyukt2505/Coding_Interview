package problem.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/the-maze-ii/
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
 * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop
 * at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start
 * position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
 */
public class Maze_505 {
    public static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int [] rows: maze) {
            Arrays.fill(rows, Integer.MAX_VALUE);
        }

        distance[start[0]][start[1]] = 0;
        /** calculate distance to reach all the spots */
        dfs(maze, start, distance);

        if(distance[destination[0]][destination[1]] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return distance[destination[0]][destination[1]];
        }
    }

    public static void dfs(int[][] maze, int[] start, int[][] distance) {
        for (int[] dir: dirs) {
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            int count = 0;

            /** - we reach any position next to a boundary or a wall, keep a track of the number of steps
             * taken in the last direction in count variable  */
            if (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                /** as the ball keeps rolling, keep iterating, until reach the boundary */
                x = x + dir[0];
                y = y + dir[1];
                count++;
            }

            /** check if current path takes lesser steps to reach same positon than any other previous path taken */
            if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, new int[]{x - dir[0],y - dir[1]}, distance);
            }
        }
    }

}
