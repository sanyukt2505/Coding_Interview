package com.leetcode.linkedlists.merge.klists;

import com.leetcode.linkedlists.SinglyLinkedList;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Vijay on 4/25/16.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class MergeKLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else
                    return 1;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists) {
            if (node != null)
                queue.add(node);
        }

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null) {
                queue.add(tail.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(3);
        ListNode l13 = new ListNode(5);
        ListNode l14 = new ListNode(7);
        l11.next = l12;
        l12.next = l13;
        l13.next = l14;

        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(5);
        ListNode l24 = new ListNode(7);
        l21.next = l22;
        l22.next = l23;
        l23.next = l24;

        ListNode[] lists = new ListNode[2];
        lists[0] = l11;
        lists[1] = l21;

        ListNode resultList = mergeKLists(lists);

        while (resultList != null) {
            System.out.println(resultList.val);
            resultList = resultList.next;
        }
    }
}
