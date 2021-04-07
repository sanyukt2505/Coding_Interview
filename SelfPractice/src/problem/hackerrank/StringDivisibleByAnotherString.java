package problem.hackerrank;

public class StringDivisibleByAnotherString {

        /*
         * Complete the 'findSmallestDivisor' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. STRING s
         *  2. STRING t
         */
/*
s= bcdabcdabc dabcdabcdabcda
t = bcdabcdabc
*/
        public static int findSmallestDivisor(String s, String t) {
            if (s == null || t == null) {
                return -1;
            }

            if(isDivisible(s, t) == -1)
                return -1;

            for (int i = 0; i < t.length(); i ++) {
                String subStr = t.substring(0, i+1);
                if (isDivisible(t, subStr) == 1) {
                    return subStr.length();
                }
            }
            return -1;
        }

        public static int isDivisible(String s, String t) {
            boolean isDivisible = false;
            while (s.length() > 0) {
                int index = s.indexOf(t);
                if (index == -1 || index > 0)
                    return -1;

                s = s.substring(t.length());
                if(s.length() == 0)
                    isDivisible = true;
            }
            if(!isDivisible) {
                return -1;
            } else {
                return 1;
            }
        }


    public static void main(String[] args)  {
           System.out.println(findSmallestDivisor("abcdabcd", "abcd"));
    }

}
