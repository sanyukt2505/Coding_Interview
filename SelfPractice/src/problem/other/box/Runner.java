package problem.other.box;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InMemoryDB memDB = new InMemoryDB();

        while(true) {
            String command = null;
            try {
                command = reader.readLine();
                memDB.execute(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
