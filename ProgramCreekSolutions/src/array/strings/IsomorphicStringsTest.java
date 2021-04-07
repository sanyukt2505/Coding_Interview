package array.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings source and target, determine if they are isomorphic. Two strings are isomorphic if the characters in String source can be replace to get String target
 * For example,"egg" and "add" are isomorphic, "foo" and "bar" are not
 * Created by Vijay on 3/12/16.
 */
public class IsomorphicStringsTest {
    public static void main(String[] args) {
        boolean isIsomorphicOne = isIsomorphic("egg", "add");
        System.out.println("Are words egg and add Isomorphic?: " + isIsomorphicOne);

        boolean isIsomorphicTwo = isIsomorphic("paper","title");
        System.out.println("Are words paper and title Isomorphic?: " + isIsomorphicTwo);

        boolean isIsomorphicThree = isIsomorphic("paper","tisle");
        System.out.println("Are words paper and tisle Isomorphic?: " + isIsomorphicThree);

        boolean isIsomorphicFour = isIsomorphic("","");
        System.out.println("Are words '' and '' Isomorphic?: " + isIsomorphicFour);
    }

    private static boolean isIsomorphic(String source, String target) {
        if (source == null || target == null) {
            return false;
        }

        if (source.length() != target.length()) {
            return false;
        }

        if (source.length() == 0 && target.length() == 0) {
            return true;
        }

        HashMap<Character, Character> map = new HashMap<>();

        for (int i = 0; i < source.length(); i++) {
            char sourceChar = source.charAt(i);
            char targetChar = target.charAt(i);

            Character c = getKey(map, targetChar);

            if (c != null && c != sourceChar) {
                return false;
            } else if (map.containsKey(sourceChar)) {
                if (targetChar != map.get(sourceChar))
                    return false;
            } else {
                map.put(sourceChar, targetChar);
            }
        }

        return true;
    }

    private static Character getKey(HashMap<Character, Character> map, char targetChar) {
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            if (entry.getValue().equals(targetChar)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
