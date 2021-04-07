package problem.leetcode.medium;

public class WaterJug_365 {
    public boolean canMeasureWater(int x, int y, int z) {
        if (z < 0 ||  z > x+y)
            return false;

        int minPossible = helper(x,y);

        if(z % minPossible == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int helper(int x, int y) {
        if (x % y == 0) {
            return y;
        } else {
            return helper(y, x % y);
        }
    }
}
