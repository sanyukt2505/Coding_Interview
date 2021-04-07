package problem.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import javafx.util.Pair;

/**
 * https://leetcode.com/problems/analyze-user-website-visit-pattern/
 *
 */
public class UserWebsitePattern_1152 {
    //Custom comparator for Priority Queue
    class Comp implements Comparator<Pair<String, Integer>>
    {
        public int compare(Pair<String, Integer> a, Pair<String, Integer>b)
        {
            if(a.getValue()!=b.getValue())
            {
                return -1*(a.getValue()-b.getValue());
            }
            else
            {
                return a.getKey().compareTo(b.getKey());
            }
        }
    }

    int seqLen = 3;

    public List<String> mostVisitedPattern(String[] user, int[] time, String[] web) {

        HashMap<Integer, Integer> timeMap= new HashMap<>();
        for(int i=0;i<time.length;i++)
        {
            timeMap.put(time[i],i);
        }
        int[] timestamp = Arrays.copyOfRange(time,0,time.length);
        /** sorting it as given input for time is not sorted  */
        Arrays.sort(timestamp);
        /**  Changing username and website accordingly  */
        String[] username = new String[time.length];
        String[] website = new String[time.length];
        for(int i=0;i<time.length;i++)
        {
            username[i] = user[timeMap.get(timestamp[i])];
            website[i] = web[timeMap.get(timestamp[i])];
        }
        /**  Creating a hashmap of user and websites they visited  */
        HashMap<String, List<String>> userWebsites = fillUserWebsites(username, website);
        /**  Getting a count of how many users visited each 3-sequence website   */
        HashMap<String, Integer> threeSeq = fillThreeSeq(userWebsites);
        /**  As we need only the top 1 element using priority queue so that the time complexity will be O(log(N))  */
        PriorityQueue<Pair<String, Integer>> orderedSeq = new PriorityQueue<>(new Comp());

        for(String key: threeSeq.keySet())
        {
            orderedSeq.add(new Pair(key, threeSeq.get(key)));
        }
        List<String> sol = new LinkedList<>(Arrays.asList(orderedSeq.remove().getKey().split(" ")));
        return sol;
    }

    public HashMap<String, Integer> fillThreeSeq(HashMap<String, List<String>> userWebsites)
    {
        HashMap<String, Integer> threeSeq = new HashMap<>();
        for(String user: userWebsites.keySet())
        {
            List<String> websites = userWebsites.get(user);
            if(websites.size()>=3)
            {
                /** getThreeCom is used to get the list of all possible 3-sequence websites with each 3-sequence
                 * considered only once for one user */
                HashSet<String> threeComb = getThreeComb(websites);
                for(String s: threeComb)
                {
                    int count = threeSeq.getOrDefault(s,0);
                    threeSeq.put(s,count+1);
                }

            }
        }
        return threeSeq;
    }

    public HashSet<String> getThreeComb(List<String> websites)
    {
        HashSet<String> sol = new HashSet<>();
        /**  using a recursive function to construct our 3-sequence list */
        helperFun(sol, new LinkedList<>(), 0, websites);
        return sol;
    }

    public void helperFun(HashSet<String> sol, List<String> cur, int pos, List<String> websites)
    {
        if(cur.size()==seqLen)
        {
            StringBuilder sb = new StringBuilder();
            for(String s:cur)
            {
                sb.append(s);
                sb.append(" ");
            }
            /** Setting the 3-sequence as a string with each website name separated by a space */
            sol.add(sb.toString().trim());
            return;
        }

        for(int i=pos;i<websites.size();i++)
        {
            cur.add(websites.get(i));
            helperFun(sol, cur, i+1, websites);
            cur.remove(cur.size()-1);
        }
    }

    public HashMap<String, List<String>> fillUserWebsites(String[] username, String[] website) {
        HashMap<String, List<String>> userWebsites = new HashMap<>();
        int i = 0;
        for (i = 0; i < username.length; i++) {
            List<String> websites = userWebsites.getOrDefault(username[i], new LinkedList<String>());
            websites.add(website[i]);
            userWebsites.put(username[i], websites);
        }
        return userWebsites;
    }
}
