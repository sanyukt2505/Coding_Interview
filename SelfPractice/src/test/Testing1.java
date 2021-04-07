package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Testing1 {
//    public static long kSub(int k, List<Integer> nums) {
//        int res = 0;
//        for (int i = 0; i < nums.size(); i++) {
//            for (int j = 0; j < nums.size() - i; j++) {
//                int last = i+j;
//                int sum = 0;
//                while (last >= j) {
//                    sum += nums.get(last--);
//                }
//                if (sum != 0 && sum % k == 0) {
//                    res++;
//                }
//            }
//        }
//        return res;
//    }

    public static long kSub(int k, List<Integer> nums) {
        int res = 0;
        int sum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
            int rem = sum % k;
            if (map.containsKey(rem)) {
                res += map.get(rem);
            }
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[] {1, 2, 3 ,4 ,1 };
        System.out.println(kSub(3, Arrays.asList(nums)));
    }
}
