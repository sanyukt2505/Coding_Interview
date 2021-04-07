package problem.other.inMemoryDB;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Transaction {

    private ConcurrentLinkedDeque<String> actions;
    private InMemoryDB db;

    public Transaction(InMemoryDB db){
        this.db = db;
        actions = new ConcurrentLinkedDeque<>();
    }

    public void addSetCommand(String[] command){
        if (command[2]==null){
            System.out.println("Cannot set null values");
            return;
        }
        String oldValue = db.set(command[1], command[2]);
        if (oldValue == null){
            actions.push(command[1]);
        }else {
            actions.push(command[1]+" "+oldValue);
        }

    }

    public void addUnsetCommand(String[] command){
        String oldValue = db.unset(command[1]);
        if (oldValue!=null){
            actions.push(command[1]+" "+oldValue);
        }
    }

    public void rollback(){
        while (!actions.isEmpty()){
            String command = actions.pop();
            String[] parsed = command.split(" ");
            if (parsed.length==1){
                db.unset(parsed[0]);
            }else {
                db.set(parsed[0],parsed[1]);
            }
        }
    }

}
