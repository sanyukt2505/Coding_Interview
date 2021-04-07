package problem.other.box;

import java.util.HashMap;
import java.util.Stack;

public class InMemoryDB {
    private HashMap<String, String> db;
    private HashMap<String, Integer> countMap;
    private Stack<Transaction> transactions;

    public InMemoryDB() {
        this.db = new HashMap<>();
        this.transactions = new Stack<>();
        this.countMap = new HashMap<>();
    }

    public void execute(String command){
        String[] cmds =  command.split(" ");
        if (cmds[0].equals("SET")){
            if (transactions.isEmpty()) {
                this.set(cmds[1], cmds[2]);
            }
            else {
                transactions.peek().addSetCommand(cmds);
            }
        } else if (cmds[0].equals("GET")) {
            this.get(cmds[1]);
        }else if (cmds[0].equals("COUNT")) {
            this.count(cmds[1]);
        }else if (cmds[0].equals("DELETE")){
            if (transactions.isEmpty()){
                this.delete(cmds[1]);
            }else {
                transactions.peek().addDeleteCommand(cmds);
            }
        } else if (cmds[0].equals("BEGIN")){
            this.recordTransaction();
            return;
        }else if (cmds[0].equals("COMMIT")){
            this.commitTransaction();
        }else if (cmds[0].equals("ROLLBACK")){
            this.rollbackTransaction();
        } else {
            System.out.println("Invalid cmd");
        }
    }

    protected void count(String value){
        System.out.println(countMap.getOrDefault(value, 0));
    }

    protected void rollbackTransaction(){
        if (transactions.isEmpty()){
            System.out.println("NO TRANSACTION");
            return;
        }
        transactions.pop().rollback();
    }

    protected String set(String key, String value){
        String old = null;
        if (db.containsKey(key)){
            old = db.get(key);
            countMap.put(old, countMap.get(old) - 1);
        }
        db.put(key, value);
        countMap.put(value, countMap.getOrDefault(value, 0) + 1);
        return old;
    }

    protected String delete(String key){
        String value = null;
        if (db.containsKey(key)){
            value = db.get(key);
            db.remove(key);
            countMap.put(value, countMap.get(value) - 1);
        }
        return value;
    }

    protected void get(String key){
        System.out.println(db.get(key));
    }

    protected void recordTransaction(){
        transactions.push(new Transaction(this));
    }

    protected void commitTransaction(){
        transactions.clear();
    }

}
