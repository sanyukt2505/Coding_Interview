package array.strings.triangle.minimum.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Triangle {
  private final int[][] triangle;

  public Triangle(int[][] triangle) {
    this.triangle = copy2DArray(triangle);
  }

  static int[][] copy2DArray(int[][] input) {
    int[][] result = new int[input.length][];
    for (int i = 0; i < input.length; i++) {
      result[i] = Arrays.copyOf(input[i], input[i].length);
    }
    return result;
  }

  public TriangleResult findShortestPath() {
    int rowIndex = triangle.length - 1;
    int[][] progress = new int[triangle.length][];

    for (int y = 0; y < triangle.length; y++) {
      progress[y] = new int[triangle[y].length];
    }

    while (rowIndex >= 0) {
      int[] row = triangle[rowIndex];
      int[] rowProgress = progress[rowIndex];

      for (int x = 0; x < row.length; x++) {
        rowProgress[x] += row[x];
      }

      for (int x = 0; x < row.length - 1; x++) {
        int[] upperLevelSum = progress[rowIndex - 1];
        int value = rowProgress[x];
        int valueToRight = rowProgress[x + 1];
        int min = Math.min(value, valueToRight);
        upperLevelSum[x] = min;
      }
      rowIndex--;
    }
    return new TriangleResult(progress, triangle);
  }

  public static Triangle generate(int size, Random random, int maxValue) {
    int[][] result = new int[size][];
    for (int y = 0; y < result.length; y++) {
      int[] row = new int[y + 1];
      for (int x = 0; x < row.length; x++) {
        row[x] = random.nextInt(maxValue);
      }
      result[y] = row;
    }
    return new Triangle(result);
  }

  public void print() {
    for (int y = 0; y < triangle.length; y++) {
      System.out.println(Arrays.toString(triangle[y]));
    }
  }

  public List<List<Integer>> toList() {
    List<List<Integer>> result = new ArrayList<>();
    for (int[] row : triangle) {
      List<Integer> rowList = Arrays.stream(row).mapToObj(i -> i).collect(Collectors.toList());
      result.add(rowList);
    }

    return result;
  }
}
