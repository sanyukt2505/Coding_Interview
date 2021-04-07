package problem;

import java.util.HashMap;

public class CachedFactorial {
    private static HashMap<Integer, Integer> cache;
    private static Integer calculationsPerformed;

    static {
        cache = new HashMap<Integer, Integer>();
        calculationsPerformed = 0;
    }

    public static long[][] solution(long[] numbersToCalculate) {
        long[][] results = new long[numbersToCalculate.length][2];
        for (int i = 0; i < numbersToCalculate.length; i++) {
            Integer result = factorial((int)numbersToCalculate[i]);
            results[i][1] = calculationsPerformed.longValue();
            results[i][0] = result.longValue();
            calculationsPerformed = 0;
        }

        cache = new HashMap<Integer, Integer>();
        return results;
    }

    private static Integer factorial(Integer value) {
        if (value == 1) {
            return 1;
        }

        Integer cached = cache.get(value);
        if (cached != null) {
            return cached;
        }

        Integer result = value * factorial(value - 1);
        calculationsPerformed++;
        cache.put(value, result);
        return result;
    }

    public static void main(String[] args) {
        long [] arr = {1, 3, 5};
        long[][] resultan = solution(arr);
        System.out.println(arr);
    }
}
