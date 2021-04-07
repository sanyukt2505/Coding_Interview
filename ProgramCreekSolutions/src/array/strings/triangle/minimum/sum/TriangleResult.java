package array.strings.triangle.minimum.sum;

public class TriangleResult {
  private final int[][] data;
  private final int[][] original;

  public TriangleResult(int[][] data, int[][] original) {
    this.data = Triangle.copy2DArray(data);
    this.original = Triangle.copy2DArray(original);
  }

  public int getSmallestSum() {
    return data[0][0];
  }

  public int[] getPath() {
    int[] result = new int[data.length];
    int x = 0;
    result[0] = original[0][0];

    for (int rowIndex = 1; rowIndex < result.length; rowIndex++) {
      int[] row = data[rowIndex];
      int[] originalRow = original[rowIndex];

      if (x < row.length - 1) {
        int left = row[x];
        int right = row[x + 1];

        if (right < left) {
          x++;
        }
      }

      result[rowIndex] = originalRow[x];
    }

    return result;
  }
}
