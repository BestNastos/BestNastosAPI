package com.bestnastos.apicases;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TestTest {
    public TestTest() throws IOException {
    }


//[ '1', 2, 'a', 'b', 'c', -1, True, 13.33, None, 41]

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(9);
//
//
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//        l2.next.next.next = new ListNode(9);
//        System.out.println(TestTest.addTwoNumbers(l1, l2));
        int[] arr = {2,7,11,15};

        System.out.println(TestTest.twoSum(arr, 9));

    }

    public static int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }

        return arr;
    }

    public static List<Object> method(List<Object> elems) {

        return elems.stream().filter(elem -> elem instanceof Integer).filter(elem -> ((int) elem) > 0).collect(Collectors.toList());

    }

    public static boolean method1(int num) {
        boolean result = false;
        int tmp = 2;
        if (tmp == num) result = true;

        while (tmp < num) {
            tmp = tmp * 2;
            if (tmp == num) {
                result = true;
                break;

            }
        }
        return result;

    }


    public static boolean method2(int num) {
        boolean result = false;

        while (num % 2 == 0) {
            num = num / 2;
        }

        return result;

    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp = l1;
        StringBuilder num1 = new StringBuilder("");
        while (true) {
            num1.append(tmp.value);
            if (tmp.next == null) break;
            else tmp = tmp.next;
        }

        tmp = l2;
        StringBuilder num2 = new StringBuilder("");
        while (true) {
            num2.append(tmp.value);
            if (tmp.next == null) break;
            else tmp = tmp.next;
        }


        String resNum = String.valueOf(Long.parseLong(num1.reverse().toString())
                + Long.parseLong(num2.reverse().toString()));

        ListNode list = new ListNode();
        ListNode temp1 = list;
        for (int i = resNum.length() - 1; i > -1 ; i--) {
            temp1.value = Integer.parseInt(resNum.substring(i, i + 1));
            if (i == 0) break;
            temp1.next = new ListNode();
            temp1 = temp1.next;
        }

        return list;
    }


    //Definition for singly-linked list.
    public static class ListNode {
        int value;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.value = val;
        }

        ListNode(int val, ListNode next) {
            this.value = val;
            this.next = next;
        }
    }

}
