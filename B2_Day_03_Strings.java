/*
 * DSA Problems Covered in this File:
 * 1. Maximum of All Subarrays of Size K
 *    GFG: https://www.geeksforgeeks.org/problems/maximum-of-all-subarrays-of-size-k3101/1
 * 2. Longest K Unique Characters Substring
 *    GFG: https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
 * 3. Longest Substring Without Repeating Characters
 *    LeetCode: https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/1686877068/
 */

import java.util.*;

public class B2_Day_03_Strings  {

    // Question 1 : ---------------------------------------------Start-------------------------------------------------
    static ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        int n = arr.length;
        Deque<Integer> dq = new ArrayDeque<>();        
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // Remove elements out of this window
            if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }

            // Remove smaller elements in k range as they are useless
            while (!dq.isEmpty() && arr[i] > arr[dq.peekLast()]) {
                dq.pollLast();
            }

            dq.offerLast(i);

            // If window has hit size k, record max
            if (i >= k - 1) {
                ans.add(arr[dq.peekFirst()]);
            }
        }

        return ans;
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------


    // Question 2 : ---------------------------------------------Start-------------------------------------------------
    static int longestKSubstr(String s, int k) {
        int n = s.length();
        int[] freq = new int[26]; 
        int distinct = 0;
        int ans = -1;

        int pt1 = 0, pt2 = 0;

        while (pt2 < n) {
            int idx = s.charAt(pt2) - 'a';
            if (freq[idx] == 0) {
                distinct++;
            }
            freq[idx]++;

            while (distinct > k) {
                int leftIdx = s.charAt(pt1) - 'a';
                freq[leftIdx]--;
                if (freq[leftIdx] == 0) {
                    distinct--;
                }
                pt1++;
            }

            if (distinct == k) {
                ans = Math.max(ans, pt2 - pt1 + 1);
            }

            pt2++;
        }

        return ans;
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------


    // Question 3 : ---------------------------------------------Start-------------------------------------------------
    static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int[] freq = new int[128]; 
        int pt1 = 0;
        int pt2 = 0;
        int ans = 0;

        while (pt2 < n) {
            char c = s.charAt(pt2);
            freq[c]++;

            while (freq[c] > 1) {
                freq[s.charAt(pt1)]--;
                pt1++;
            }

            ans = Math.max(ans, pt2 - pt1 + 1);
            pt2++;
        }

        return ans;
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------

    public static void main(String[] args) {
        System.out.println("=============== QUESTION 1: Maximum of All Subarrays of Size K ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/maximum-of-all-subarrays-of-size-k3101/1");
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        ArrayList<Integer> result = maxOfSubarrays(nums, k);
        System.out.println("Max of each window: " + result);

        System.out.println("\n=============== QUESTION 2: Longest K Unique Characters Substring ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1");
        String s = "aabacbebebe";
        int k2 = 3;
        int maxLen = longestKSubstr(s, k2);
        System.out.println("Longest substring length with " + k2 + " unique characters: " + maxLen);

        System.out.println("\n=============== QUESTION 3: Longest Substring Without Repeating Characters ===============");
        System.out.println("Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/1686877068/");
        String test = "abcabcbb";
        int length = lengthOfLongestSubstring(test);
        System.out.println("Length of longest substring without repeating characters: " + length);
    }
}
