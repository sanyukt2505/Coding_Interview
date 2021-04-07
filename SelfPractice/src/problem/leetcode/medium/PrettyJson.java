package problem.leetcode.medium;

/**
 * convert a json to pretty json
 */
public class PrettyJson {

    public static String prettyJson(String str) {
        int len = str.length();
        int level = 0;
        char prev = '0';
        StringBuilder strBuild = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char curr = str.charAt(i);
            if (Character.isWhitespace(curr))
                continue;

            if (curr == '}' || curr == ']')
                level--;

            if (prev == '{' || prev == '[' || prev == ',' || curr == '}' || curr == ']') {
                strBuild.append("\n");
                for (int j = 0; j < level; j++)
                    strBuild.append("\t");
            }
            strBuild.append(curr);

            if (curr == '{' || curr == '[')
                level++;

            prev = curr;
        }
        return strBuild.toString();
    }

    public static void main(String[] args) {
        String json = "{\"id\": \"0001\", \"type\": \"donut\", \"name\": \"Cake\", \"ppu\": 0.55, \"batters\":{\"batter\":[{ \"id\": \"1001\", \"type\": \"Regular\" },{ \"id\": \"1002\", \"type\": \"Chocolate\" }]},\"topping\":[{ \"id\": \"5001\", \"type\": \"None\" },{ \"id\": \"5002\", \"type\": \"Glazed\" }]}";
        System.out.println(prettyJson(json));
    }
}
