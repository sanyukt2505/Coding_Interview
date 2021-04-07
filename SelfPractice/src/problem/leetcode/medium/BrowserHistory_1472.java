package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/design-browser-history/
 * You have a browser of one tab where you start on the homepage and you can visit another url,
 * get back in the history number of steps or move forward in the history number of steps.
 * Implement the BrowserHistory class:
 *
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 *   - void visit(string url) Visits url from the current page. It clears up all the forward history.
 *   - string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x,
 *     you will return only x steps. Return the current url after moving back in history at most steps.
 *   - string forward(int steps) Move steps forward in history. If you can only forward x steps in the history
 *     and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
 */
public class BrowserHistory_1472 {
    public List<String> history;
    int curr;
    // max to keep track of current maximum forward elements in the history
    int max;

    public BrowserHistory_1472(String homepage) {
        this.history = new ArrayList<>();
        this.history.add(homepage);
        this.curr = 0;
        this.max = 0;
    }

    public void visit(String url) {
        // if curr is the last element in list, add new url
        // else set the new url at curr+1 position
        if(curr++ == history.size()-1){
            history.add(url);
        }else{
            history.set(curr, url);
        }
        // update max with curr
        max = curr;
    }

    // validate for steps
    public String back(int steps) {
        curr = (curr - steps) > 0 ? curr - steps : 0;
        return history.get(curr);
    }
    // validate for steps using max elements in the browser history
    public String forward(int steps) {
        curr = (curr + steps) < max ? curr + steps : max;
        return history.get(curr);
    }

    /**
     * two Stack Implementation
     */
    private class BrowserHistory_1472_Stack {
        Stack<String> back;
        Stack<String> forward;
        public BrowserHistory_1472_Stack(String homepage) {
            back = new Stack<>();
            forward = new Stack<>();
            back.push(homepage);
        }

        public void visit(String url) {
            back.push(url);
            forward.clear(); // if we go to new page, then we can't move forward, we can only moive backward, so pushed into back stack.
        }

        public String back(int steps) {
            int count = 0;
            while (count < steps && back.size() > 1) {
                count += 1;
                forward.push(back.pop()); // if we move backward, then we can after sometime move forward, so fill forward stack.
            }
            return back.peek();
        }

        public String forward(int steps) {
            int count = 0;
            while (count < steps && forward.size() > 0) {
                count += 1;
                back.push(forward.pop()); // same idea as that of method back. If we move forward, then after sometime we can move backward. SO we fill the back stack.
            }
            return back.peek();
        }
    }
}


