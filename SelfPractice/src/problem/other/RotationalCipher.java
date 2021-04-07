package problem.other;

/**
 * One simple way to encrypt a string is to "rotate" every alphanumeric character by a certain amount.
 * Rotating a character means replacing it with another character that is a certain number of steps away in normal alphabetic or numerical order.
 * For example, if the string "Zebra-493?" is rotated 3 places, the resulting string is "Cheud-726?".
 * Every alphabetic character is replaced with the character 3 letters higher (wrapping around from Z to A), and
 * every numeric character replaced with the character 3 digits higher (wrapping around from 9 to 0).
 * Note that the non-alphanumeric characters remain unchanged.
 */
public class RotationalCipher {
    String rotationalCipher(String input, int rotationFactor) {
        // Write your code here
        if(input == null || input.length() == 0 || rotationFactor == 0) {
            return input;
        }
        StringBuilder sb = new StringBuilder();
        for(char c: input.toCharArray()) {
            int remRF = 0;
            if(Character.isDigit(c)) {
                remRF =  rotationFactor % 10;
                c = (c + remRF > '9') ? (char)(c + remRF - 10): (char) (c + remRF);
            } else if(c >= 'a' && c <='z') {
                remRF = rotationFactor % 26;
                c = (c + remRF > 'z') ? (char)(c + remRF - 26): (char) (c + remRF);
            } else if(c >= 'A' && c <='Z') {
                remRF = rotationFactor % 26;
                c = (c + remRF > 'Z') ? (char)(c + remRF - 26): (char) (c + remRF);
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
