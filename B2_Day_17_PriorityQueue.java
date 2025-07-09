/*
 * Priority Queue Problems (in order):
 * 1. Find K Closest Elements: https://leetcode.com/problems/find-k-closest-elements/
 * 2. Top K Frequent Elements: https://leetcode.com/problems/top-k-frequent-elements/
 * 3. Sort Array by Increasing Frequency: https://leetcode.com/problems/sort-array-by-increasing-frequency/
 * 4. K Closest Points to Origin: https://leetcode.com/problems/k-closest-points-to-origin/
 * 5. Minimum Cost of Ropes: https://www.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1
 */

import java.util.*;

public class B2_Day_17_PriorityQueue {

    // ========================= Question 1 =============================
    // Find K Closest Elements
    // 🔗 LeetCode: https://leetcode.com/problems/find-k-closest-elements/
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(b[0], a[0]);
                return Integer.compare(b[1], a[1]);
            }
        );

        for (int num : arr) {
            int diff = Math.abs(num - x);
            maxHeap.add(new int[]{diff, num});
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll()[1]);
        }

        Collections.sort(result);
        return result;
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 2 =============================
    // Top K Frequent Elements
    // 🔗 LeetCode: https://leetcode.com/problems/top-k-frequent-elements/
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.add(new int[]{entry.getValue(), entry.getKey()});
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll()[1];
        }

        return result;
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 3 =============================
    // Sort Array by Increasing Frequency
    // 🔗 LeetCode: https://leetcode.com/problems/sort-array-by-increasing-frequency/
    public static int[] frequencySort(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                return Integer.compare(b[1], a[1]);
            }
        );

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(new int[]{entry.getValue(), entry.getKey()});
        }

        List<Integer> resultList = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            int[] entry = minHeap.poll();
            int freq = entry[0], num = entry[1];
            for (int i = 0; i < freq; i++) {
                resultList.add(num);
            }
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 4 =============================
    // K Closest Points to Origin
    // 🔗 LeetCode: https://leetcode.com/problems/k-closest-points-to-origin/
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(distance(b), distance(a))
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }

    private static int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    // Question 4 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 5 =============================
    // Minimum Cost of Ropes
    // 🔗 GFG: https://www.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1
    public static int minCost(int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : arr) {
            minHeap.add(num);
        }

        int totalCost = 0;

        while (minHeap.size() > 1) {
            int a = minHeap.poll();
            int b = minHeap.poll();
            int cost = a + b;
            totalCost += cost;
            minHeap.add(cost);
        }

        return totalCost;
    }
    // Question 5 : ---------------------------------------------End---------------------------------------------------

    public static void main(String[] args) {
        System.out.println("=============== DAY 17: PRIORITY QUEUE PROBLEMS ===============");

        // Question 1
        System.out.println("\n=============== QUESTION 1: FIND K CLOSEST ELEMENTS ===============");
        System.out.println("Link: https://leetcode.com/problems/find-k-closest-elements/");
        int[] a1 = {1, 2, 3, 4, 5};
        int k1 = 4, x1 = 3;
        System.out.println("Closest Elements: " + findClosestElements(a1, k1, x1));

        // Question 2
        System.out.println("\n=============== QUESTION 2: TOP K FREQUENT ELEMENTS ===============");
        System.out.println("Link: https://leetcode.com/problems/top-k-frequent-elements/");
        int[] a2 = {1, 1, 1, 2, 2, 3};
        int k2 = 2;
        System.out.println("Top " + k2 + " frequent elements: " + Arrays.toString(topKFrequent(a2, k2)));

        // Question 3
        System.out.println("\n=============== QUESTION 3: SORT ARRAY BY INCREASING FREQUENCY ===============");
        System.out.println("Link: https://leetcode.com/problems/sort-array-by-increasing-frequency/");
        int[] a3 = {1, 1, 2, 2, 2, 3};
        System.out.println("Sorted by frequency: " + Arrays.toString(frequencySort(a3)));

        // Question 4
        System.out.println("\n=============== QUESTION 4: K CLOSEST POINTS TO ORIGIN ===============");
        System.out.println("Link: https://leetcode.com/problems/k-closest-points-to-origin/");
        int[][] pts = {{1, 3}, {-2, 2}, {5, 8}, {0, 1}};
        int k4 = 2;
        int[][] closest = kClosest(pts, k4);
        System.out.print("K Closest Points: ");
        for (int[] point : closest) {
            System.out.print(Arrays.toString(point) + " ");
        }
        System.out.println();

        // Question 5
        System.out.println("\n=============== QUESTION 5: MINIMUM COST OF ROPES ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1");
        int[] ropes = {4, 3, 2, 6};
        System.out.println("Minimum total cost to connect ropes: " + minCost(ropes));
    }
}
