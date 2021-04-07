package problem.other.inMemoryDB;

import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDB {

    private ConcurrentHashMap<String,String> db;
    private Stack<Transaction> transactions;

    public InMemoryDB(){
        db = new ConcurrentHashMap<>();
        transactions = new Stack<>();
    }

    public void parseCommand(String command){
        String[] parsedCommand =  command.split(" ");
        if (parsedCommand[0].equals("SET") && parsedCommand.length==3){
            if (transactions.isEmpty()){
                if (parsedCommand[2]==null){
                    System.out.println("Cannot set null values");
                    return;
                }
                this.set(parsedCommand[1], parsedCommand[2]);
            }else {
                transactions.peek().addSetCommand(parsedCommand);
            }
            return;
        }
        if (parsedCommand[0].equals("GET")){
            System.out.println(db.get(parsedCommand[1]));
            return;
        }
        if (parsedCommand[0].equals("UNSET")){
            if (transactions.isEmpty()){
                this.unset(parsedCommand[1]);
            }else {
                transactions.peek().addUnsetCommand(parsedCommand);
            }
            return;
        }
        if (parsedCommand[0].equals("BEGIN")){
            this.startXaction();
            return;
        }
        if (parsedCommand[0].equals("ROLLBACK")){
            this.rollback();
            return;
        }
        if (parsedCommand[0].equals("COMMIT")){
            this.commit();
            return;
        }
        else {
            System.out.println("Invalid command");
        }

    }


    public String set(String key, String value){
        String old = null;
        if (db.containsKey(key)){
            old = db.get(key);
        }
        db.put(key, value);
        return old;
    }

    public String unset(String key){
        String value = null;
        if (db.containsKey(key)){
            value = db.get(key);
            db.remove(key);
        }
        return value;
    }

    public String get(String key){
        return db.get(key);
    }

    public void startXaction(){
        transactions.push(new Transaction(this));
    }

    public void rollback(){
        if (transactions.isEmpty()){
            System.out.println("NO TRANSACTIONS IN PROGRESS");
            return;
        }
        transactions.pop().rollback();
    }

    public void commit(){
        if (transactions.isEmpty()){
            System.out.println("NO TRANSACTIONS IN PROGRESS");
            return;
        }
        transactions.clear();
    }

}
