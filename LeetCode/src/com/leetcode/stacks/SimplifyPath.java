package com.leetcode.stacks;

import java.util.*;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
    For example,
        path = "/home/", => "/home"
        path = "/a/./b/../../c/", => "/c"

    Corner Cases:
        Did you consider the case where path = "/../"?
            In this case, you should return "/".
        Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
            In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {

    public static void main(String[] args) {
        String path = "/a/./b/../../c/";
        String simplifiedPath = simplifyPath(path);
        System.out.println("/a/./b/../../c/ is simplified to: " + simplifiedPath);

        path = "/../";
        simplifiedPath = simplifyPath(path);
        System.out.println("/../ is simplified to: " + simplifiedPath);

        path = "/home//foo/";
        simplifiedPath = simplifyPath(path);
        System.out.println("/home//foo/ is simplified to: " + simplifiedPath);
    }

    /**
     * The main idea is to:
     *  1. push every valid file name (not in {"", ".", ".."}) to the stack
     *  2. pop only if there is something to pop and if we met ".."
     * @param path
     * @return
     */
    public static String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));

        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!skip.contains(dir)) {
                stack.push(dir);
            }
        }

        String res = "";
        for (String dir : stack) {
            res = "/" + dir + res;
        }

        return res.isEmpty() ? "/" : res;
    }
}
