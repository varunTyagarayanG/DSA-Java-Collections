/*
 * DSA Problems Covered in this File:
 * 1. Count frequency of a target number in a sorted array
 *    GFG: https://www.geeksforgeeks.org/problems/number-of-occurrence2259/1
 * 2. Search in Rotated Sorted Array
 *    LeetCode: https://leetcode.com/problems/search-in-rotated-sorted-array/submissions/1685820873/
 * 3. Allocate Minimum Number of Pages
 *    GFG: https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
 * 4. Painter's Partition Problem
 *    GFG: https://www.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
 * 5. Max Sum Subarray of Size K
 *    GFG: https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
 * 6. First Negative Integer in Every Window of Size K
 *    GFG: https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
 * 7. Count Occurrences of Anagrams
 *    GFG: https://www.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
 */

import java.util.*;

public class B2_Day_02_Arrays  {

    // Question 1 : ---------------------------------------------Start-------------------------------------------------
    static int countFreq(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1, firstOCC = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                firstOCC = mid;
                break;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (firstOCC == -1) return 0;
        int count = 1;
        low = firstOCC;
        high = firstOCC;

        while (low - 1 >= 0 && arr[low - 1] == target) {
            count++;
            low--;
        }
        while (high + 1 < n && arr[high + 1] == target) {
            count++;
            high++;
        }

        return count;
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------


    // Question 2 : ---------------------------------------------Start-------------------------------------------------
    static int searchInRotatedArray(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;

        int low = 0, high = n - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        int pivot = low;
        low = 0;
        high = n - 1;

        if (target >= nums[pivot] && target <= nums[high]) {
            low = pivot;
        } else {
            high = pivot - 1;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------


    // Question 3 : ---------------------------------------------Start-------------------------------------------------
    static boolean isPossiblePages(int[] arr, int k, int mid, int n) {
        int studentCount = 1;
        int pageSum = 0;

        for (int i = 0; i < n; i++) {
            if (pageSum + arr[i] <= mid) {
                pageSum += arr[i];
            } else {
                studentCount++;
                if (studentCount > k || arr[i] > mid) {
                    return false;
                }
                pageSum = arr[i];
            }
        }

        return studentCount <= k;
    }

    static int findPages(int[] arr, int k) {
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();

        int low = 0;
        int high = sum;
        int ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossiblePages(arr, k, mid, n)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------


    // Question 4 : ---------------------------------------------Start-------------------------------------------------
    static boolean isPossiblePainter(int[] arr, int k, int mid, int n) {
        int noOfPainters = 1;
        int totalPaint = 0;

        for (int i = 0; i < n; i++) {
            if (totalPaint + arr[i] <= mid) {
                totalPaint += arr[i];
            } else {
                noOfPainters++;
                if (noOfPainters > k || mid < arr[i]) {
                    return false;
                }
                totalPaint = arr[i];
            }
        }
        return noOfPainters <= k;
    }

    static int minTime(int[] arr, int k) {
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();
        int low = Arrays.stream(arr).min().getAsInt();
        int high = sum;
        int ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossiblePainter(arr, k, mid, n)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    // Question 4 : ---------------------------------------------End---------------------------------------------------


    // Question 5 : ---------------------------------------------Start-------------------------------------------------
    static int maximumSumSubarray(int[] arr, int k) {
        int n = arr.length;
        int maxSum = 0;

        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }
        int ans = maxSum;

        for (int i = k; i < n; i++) {
            maxSum = maxSum + arr[i] - arr[i - k];
            if (maxSum > ans) {
                ans = maxSum;
            }
        }
        return ans;
    }
    // Question 5 : ---------------------------------------------End---------------------------------------------------


    // Question 6 : ---------------------------------------------Start-------------------------------------------------
    static List<Integer> firstNegInt(int arr[], int k) {
        int n = arr.length;
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            if (arr[i] < 0) {
                q.add(arr[i]);
            }
        }
        ans.add(q.isEmpty() ? 0 : q.peek());

        for (int i = k; i < n; i++) {
            if (!q.isEmpty() && q.peek() == arr[i - k]) {
                q.remove();
            }
            if (arr[i] < 0) {
                q.add(arr[i]);
            }
            ans.add(q.isEmpty() ? 0 : q.peek());
        }

        return ans;
    }
    // Question 6 : ---------------------------------------------End---------------------------------------------------


    // Question 7 : ---------------------------------------------Start-------------------------------------------------
    static int countAnagrams(String pat, String txt) {
        int n = pat.length();
        int k = txt.length();
        int count = 0;

        if (n > k) return 0;

        int[] freq_n = new int[26];
        int[] freq_k = new int[26];

        for (int i = 0; i < n; i++) {
            freq_n[pat.charAt(i) - 'a']++;
            freq_k[txt.charAt(i) - 'a']++;
        }

        if (Arrays.equals(freq_n, freq_k)) {
            count++;
        }

        for (int i = n; i < k; i++) {
            freq_k[txt.charAt(i - n) - 'a']--;
            freq_k[txt.charAt(i) - 'a']++;

            if (Arrays.equals(freq_n, freq_k)) {
                count++;
            }
        }

        return count;
    }
    // Question 7 : ---------------------------------------------End---------------------------------------------------


    public static void main(String[] args) {
        System.out.println("=============== QUESTION 1: Count frequency of a target number in a sorted array ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/number-of-occurrence2259/1");
        int[] arr = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int result = countFreq(arr, target);
        System.out.println("Frequency of " + target + " is: " + result);

        System.out.println("\n=============== QUESTION 2: Search in Rotated Sorted Array ===============");
        System.out.println("Link: https://leetcode.com/problems/search-in-rotated-sorted-array/submissions/1685820873/");
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int key = 0;
        int index = searchInRotatedArray(nums, key);
        System.out.println("Index of " + key + " is: " + index);

        System.out.println("\n=============== QUESTION 3: Allocate Minimum Number of Pages ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1");
        int[] books = {12, 34, 67, 90};
        int students = 2;
        int minPages = findPages(books, students);
        System.out.println("Minimum number of pages: " + minPages);

        System.out.println("\n=============== QUESTION 4: Painter's Partition Problem ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/the-painters-partition-problem1535/1");
        int[] boards = {10, 20, 30, 40};
        int painters = 2;
        int minTimeToPaint = minTime(boards, painters);
        System.out.println("Minimum time to paint all boards: " + minTimeToPaint);

        System.out.println("\n=============== QUESTION 5: Max Sum Subarray of Size K ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1");
        int[] a = {100, 200, 300, 400};
        int k = 2;
        int maxSum = maximumSumSubarray(a, k);
        System.out.println("Maximum sum of subarray of size " + k + ": " + maxSum);

        System.out.println("\n=============== QUESTION 6: First Negative Integer in Every Window of Size K ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1");
        int[] windowArr = {12, -1, -7, 8, 15, 30, 16, 28};
        int windowSize = 3;
        List<Integer> negatives = firstNegInt(windowArr, windowSize);
        System.out.println("First negative integers in each window: " + negatives);

        System.out.println("\n=============== QUESTION 7: Count Occurrences of Anagrams ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1");
        String pattern = "for";
        String text = "forxxorfxdofr";
        int anagramCount = countAnagrams(pattern, text);
        System.out.println("Count of anagrams of \"" + pattern + "\" in text: " + anagramCount);
    }
}