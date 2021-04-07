package problem.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class AboveAverageSubArray {

    public static List<List<Integer>> aboveAverageSubarrays(int[] A) {
        List<List<Integer>> res = new ArrayList<>();
        int sum = 0;
        double totalAvg = 0;

        for (int num: A) {
            sum += num;
        }

        totalAvg = (double)sum/A.length;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length - i; j++) {
                int localSum = 0;
                double localAvg = 0.0;
                List<Integer> subArray = new ArrayList<>();
                int k = j;
                while (k <= j+i) {
                    localSum += A[k];
                    subArray.add(k+1);
                    k++;
                }
                localAvg = (double)localSum / subArray.size();

                if (localAvg > totalAvg) {
                    res.add(subArray);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (List<Integer> list: aboveAverageSubarrays(new int[]{3,4,2}))
            System.out.println(list);
    }












    //
    //      Longest Common Substring in an Array of Strings
    //        #Input : [grace graceful disgraceful gracefully test]
    //        #Output : grace
    //        #Input : sadness sad sadly
    //        #Output : sad
    //         Input : [gra grace graceful apple disgracful gracefully test]  --- [grac, test] -- ""
    //        #Output : e

    //


//    public static String longestCommonSubString(List<String> strings) {
//        String result = new String();
//        if (strings == null || strings.size() == 0) {
//            return result;
//        }
//
//        if (strings.size() == 1) {
//            return strings.get(0);
//        }
//
//        // prev = ra ,   curr = graceful
//        String prev = strings.get(0);
//        for (int i = 1; i < strings.size(); i++) {
//            String curr = strings.get(i);
//
//            for (int j = 0; j < prev.length(); j++) {
//                for (int k = prev.length(); k > i; k--) {
//                    if (curr.contains(prev.substring(i, k+1))) {
//                        result = prev.substring(i, k+1);
//                    }
//                }
//            }
//            prev = result;
//        }
//        return result;
//    }
}
