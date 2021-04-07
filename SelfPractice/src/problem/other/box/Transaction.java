package problem.other.box;

import java.util.Stack;

public class Transaction {
    private Stack<String> actions;
    private InMemoryDB db;

    public Transaction(InMemoryDB db){
        this.db = db;
        actions = new Stack<>();
    }

    public void addSetCommand(String[] command){
        String oldValue = db.set(command[1], command[2]);
        if (oldValue == null){
            actions.push(command[1]);
        } else {
            actions.push(command[1] + " " + oldValue);
        }
    }

    public void addDeleteCommand(String[] command){
        String oldValue = db.delete(command[1]);
        if (oldValue != null){
            actions.push(command[1] + " " + oldValue);
        }
    }

    public void rollback(){
        while (!actions.isEmpty()){
            String command = actions.pop();
            String[] cmds = command.split(" ");
            if (cmds.length == 1){
                db.delete(cmds[0]);
            } else {
                db.set(cmds[0], cmds[1]);
            }
        }
    }
}
