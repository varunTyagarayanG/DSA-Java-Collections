/*
Questions in this file:
1. Intersection of Two Arrays - https://leetcode.com/problems/intersection-of-two-arrays/
2. Subarray with Given Sum - https://www.geeksforgeeks.org/problems/subarray-with-given-sum/0
3. Merge Sorted Array - https://leetcode.com/problems/merge-sorted-array/
4. Maximum Subarray (Kadane's Algorithm) - https://leetcode.com/problems/maximum-subarray/
5. Peak Index in a Mountain Array - https://leetcode.com/problems/peak-index-in-a-mountain-array/
6. Find First and Last Position of Element in Sorted Array - https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
*/

import java.util.*;

public class B2_Day_1_Arrays  {

    // ========================= Question 1: Intersection of Two Arrays =============================
    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> intersection = new HashSet<>();
        int p1 = 0, p2 = 0;

        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                intersection.add(nums1[p1]);
                p1++; p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }

        int[] result = new int[intersection.size()];
        int index = 0;
        for (int x : intersection) result[index++] = x;
        return result;
    }

    // ========================= Question 2: Subarray with Given Sum =============================
    public static ArrayList<Integer> subarraySum(int[] arr, int target) {
        int start = 0, sum = 0;
        for (int end = 0; end < arr.length; end++) {
            sum += arr[end];
            while (sum > target) sum -= arr[start++];
            if (sum == target) return new ArrayList<>(Arrays.asList(start + 1, end + 1));
        }
        return new ArrayList<>(Arrays.asList(-1));
    }

    // ========================= Question 3: Merge Sorted Array =============================
    public static void mergeSortedArrays(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, end = m + n - 1;
        while (p1 >= 0 && p2 >= 0) nums1[end--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        while (p2 >= 0) nums1[end--] = nums2[p2--];
    }

    // ========================= Question 4: Maximum Subarray (Kadane's Algorithm) =============================
    public static int maxSubArray(int[] nums) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, sum);
            if (sum < 0) sum = 0;
        }
        return max;
    }

    // ========================= Question 5: Peak Index in a Mountain Array =============================
    public static int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) return mid;
            else if (arr[mid] < arr[mid + 1]) left = mid;
            else right = mid;
        }
        return -1;
    }

    // ========================= Question 6: Find First and Last Position of Element in Sorted Array =============================
    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    public static int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1, firstOCC = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                firstOCC = mid;
                break;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (firstOCC == -1) return new int[]{-1, -1};

        low = firstOCC;
        high = firstOCC;

        while (low - 1 >= 0 && nums[low - 1] == target) low--;
        while (high + 1 < n && nums[high + 1] == target) high++;

        return new int[]{low, high};
    }

    // ========================= main() =============================
    public static void main(String[] args) {
        System.out.println("=============== QUESTION 1: Intersection of Two Arrays ===============");
        int[] nums1 = {1, 2, 2, 1}, nums2 = {2, 2};
        System.out.println("Result: " + Arrays.toString(intersection(nums1, nums2)));

        System.out.println("\n=============== QUESTION 2: Subarray with Given Sum ===============");
        int[] arr = {1, 2, 3, 7, 5}; int target = 12;
        System.out.println("Result: " + subarraySum(arr, target));

        System.out.println("\n=============== QUESTION 3: Merge Sorted Array ===============");
        int[] nums3_1 = {1, 2, 3, 0, 0, 0}, nums3_2 = {2, 5, 6};
        mergeSortedArrays(nums3_1, 3, nums3_2, 3);
        System.out.println("Result: " + Arrays.toString(nums3_1));

        System.out.println("\n=============== QUESTION 4: Maximum Subarray ===============");
        int[] nums4 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Result: " + maxSubArray(nums4));

        System.out.println("\n=============== QUESTION 5: Peak Index in Mountain Array ===============");
        int[] nums5 = {0, 2, 4, 6, 3, 1};
        System.out.println("Result: " + peakIndexInMountainArray(nums5));

        System.out.println("\n=============== QUESTION 6: First and Last Position of Element ===============");
        int[] nums6 = {5, 7, 7, 8, 8, 10}; int tgt = 8;
        System.out.println("Result: " + Arrays.toString(searchRange(nums6, tgt)));
    }
}
