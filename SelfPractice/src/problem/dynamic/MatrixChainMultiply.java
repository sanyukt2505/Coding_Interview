package problem.dynamic;

public class MatrixChainMultiply {
    /**
     * https://www.youtube.com/watch?v=_WncuhSJZyA&list=PLDN4rrl48XKpZkf03iYFl-O29szjTrs_O&index=51&t=1s
     *
     * Time complexity of the above naive recursive approach is exponential.
     * It should be noted that the above function computes the same subproblems again and again.
     */
    // Matrix Ai has dimension p[i-1] x p[i] for i = 1..n
    static int MatrixChainOrder(int p[], int i, int j)
    {
        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;

        // place parenthesis at different places between first and last matrix, recursively calculate count of
        // multiplications for each parenthesis placement and return the minimum count
        for (int k=i; k<j; k++)
        {
            int count = MatrixChainOrder(p, i, k) +
                    MatrixChainOrder(p, k+1, j) +
                    p[i-1]*p[k]*p[j];

            if (count < min)
                min = count;
        }

        // Return minimum count
        return min;
    }

    /**
     * https://www.youtube.com/watch?v=eKkXU3uu2zk&list=PLDN4rrl48XKpZkf03iYFl-O29szjTrs_O&index=52
     */
    // Matrix Ai has dimension p[i-1] x p[i] for i = 1..n
    static int MatrixChainOrderDynamicProgram(int p[], int n)
    {
        /* For simplicity of the program, one extra row and one extra column are allocated in m[][].
         0th row and 0th  column of m[][] are not used */
        int m[][] = new int[n][n];           // used to store cost of multiplication
        int s[][] = new int[n][n];           // to save parenthesis -- where to put parenthesis

        int i, j, k, L, q;

        /* m[i,j] = Minimum number of scalar multiplications needed to compute the matrix A[i]A[i+1]...A[j] = A[i..j]
        where  dimension of A[i] is p[i-1] x p[i] */

        // cost is zero when multiplying one matrix.
        for (i = 1; i < n; i++) {
            m[i][i] = 0;
            s[i][i] = 0;
        }

        /**
         * L is chain length.
         * First find the values for shortest chain m[1,2], m[2,3] and m[3,4].....
         * Then calculate m[1,3] m[2,4].......
         */
        for (L = 1; L < n-1; L++)
        {
            for (i = 1; i < n-L; i++)
            {
                j = i+L;
                if(j == n) continue;
                m[i][j] = Integer.MAX_VALUE;
                for (k=i; k <= j-1; k++)
                {
                    // q = cost/scalar multiplications
                    q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }

                }
            }
        }

        return m[1][n-1];
    }

    public static void main(String args[])
    {
        int arr[] = new int[] {1, 2, 3, 4, 3};
        int n = arr.length;

        System.out.println("Minimum number of multiplications is "+
                MatrixChainOrder(arr, 1, n-1));

        System.out.println("Minimum number by Dynamic "+
                MatrixChainOrderDynamicProgram(arr, n));

    }
}
