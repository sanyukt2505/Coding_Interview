package problem.leetcode.easy;

/**
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill,
 * and a pixel value newColor, "flood fill" the image.
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]]     sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 */
public class FloodFill_Image_733 {
    /** do a dfs */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }

        int existColor = image[sr][sc];
        dfs(image, sr, sc, existColor, newColor);
        return image;
    }

    void dfs(int[][] image, int sr, int sc, int existColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != existColor) {
            return;
        }

        image[sr][sc] = newColor;
        dfs(image, sr-1, sc, existColor, newColor);
        dfs(image, sr+1, sc, existColor, newColor);
        dfs(image, sr, sc-1, existColor, newColor);
        dfs(image, sr, sc+1, existColor, newColor);
    }
}
