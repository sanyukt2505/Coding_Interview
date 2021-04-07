package problem.geeks;

import java.util.ArrayList;
/**
 * // Given two integers p and q, return a string representation of their quotient p/q, such that
 * // * If p/q has a finite decimal representation, return its simple string form, e.g.
 * //  * p = 5, q = 1 => "5"
 * //  * p = 1, q = 5 => "0.2"
 * // * Otherwise, enclose the repeating pattern within a pair of parentheses, e.g.
 * //  * p = 5, q = 3 => "1.(6)"
 * //  * p = 3, q = 110 => "0.0(27)"
 */
public class FractionStringformat {
    public static String calculateFraction(int num, int den)
    {
        if (num == 0) return "0";
        if (den == 0)  return "Error";
        /** Check the numerator or denominator is negative  */
        boolean neg = false;
        if (num < 0 || den <0)
            neg = true;

        if (num % den == 0)
        {
            int res1 = (num / den);
            return Integer.toString(res1);
        }

        /** Arraylist to store the numbers after the decimal point  */
        ArrayList<Integer> Fraction = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        /** If numerator or denominator is -ve  result will be negative, hence append string with "-" at beginning */
        if (neg == true)
        {
            str.append("-");
        }

        /** Taking only the absolute values from the numerator and denominator */
        num = Math.abs(num);
        den = Math.abs(den);

        /** Appending the number before the decimal point  */
        str.append(num / den);
        str.append(".");

        while(true) {
            int rem = num % den;

            /** The remainder becomes zero, then the result will have no recurring digits after decimal point */
            if (rem == 0)
            {
                for(int i = 0;i < Fraction.size(); i++)
                    str.append(Fraction.get(i));
                break;
            }

            /** The numerator for next iteration will be the product of remainder and 10  */
            num = rem * 10;
            int quo = num / den;

            /** Adding the digit to the Fraction list only if it does not exists in it  */
            if (!(Fraction.contains(quo))) {
                Fraction.add(quo);
            } else if(Fraction.contains(quo)) {
                /** Retrieving the index of the number upto which the quotient is not recurring */
                int ind = Fraction.indexOf(quo);
                for(int i = 0; i <= ind - 1; i++)
                {
                    str.append(Fraction.get(i));
                }
                // Printing the recurring pattern within brackets
                str.append("(");
                for(int i = ind; i < Fraction.size(); i++)
                {
                    str.append(Fraction.get(i));
                }
                str.append(")");
                break;
            }
        }
        return str.toString();
    }

    public static void main (String[] args)
    {
        int num = 50;
        int den = 22;
        String resString1 = calculateFraction(num, den);
        num = -1;
        den = 2;
        String resString2 = calculateFraction(num, den);
        System.out.println(resString1);
        System.out.println(resString2);
    }

}
