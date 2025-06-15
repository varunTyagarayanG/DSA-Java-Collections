import java.util.Arrays;

public class B2_Day_18_DP_knapsack {

    // Question 1 : ---------------------------------------------Start-------------------------------------------------
    // 0/1 Knapsack using Memoization (Top-Down DP)
    // GFG: https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
    static int knapsackMemo(int W, int val[], int wt[]) {
        int n = val.length;
        int[][] dp = new int[n][W + 1];
        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                dp[i][w] = -1;
            }
        }
        return helperKnapsack(W, val, wt, n - 1, dp);
    }

    static int helperKnapsack(int W, int val[], int wt[], int idx, int[][] dp) {
        if (idx < 0 || W == 0) return 0;
        if (dp[idx][W] != -1) return dp[idx][W];

        if (wt[idx] <= W) {
            int include = val[idx] + helperKnapsack(W - wt[idx], val, wt, idx - 1, dp);
            int exclude = helperKnapsack(W, val, wt, idx - 1, dp);
            dp[idx][W] = Math.max(include, exclude);
        } else {
            dp[idx][W] = helperKnapsack(W, val, wt, idx - 1, dp);
        }
        return dp[idx][W];
    }

    // 0/1 Knapsack using Tabulation (Bottom-Up DP)
    // GFG: https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
    static int knapsackTab(int W, int val[], int wt[]) {
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\nDP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }

        return dp[n][W];
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------


    // Question 2 : ---------------------------------------------Start-------------------------------------------------
    // Subset Sum using Memoization (Top-Down)
    // GFG: https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
    static Boolean helperSubset(int[] arr, int sum, int idx, int currSum, Boolean[][] dp) {
        if (currSum == sum) return true;
        if (idx >= arr.length || currSum > sum) return false;

        if (dp[idx][currSum] != null) return dp[idx][currSum];

        boolean include = helperSubset(arr, sum, idx + 1, currSum + arr[idx], dp);
        boolean exclude = helperSubset(arr, sum, idx + 1, currSum, dp);

        dp[idx][currSum] = include || exclude;
        return dp[idx][currSum];
    }

    static Boolean isSubsetSumMemo(int arr[], int sum) {
        int n = arr.length;
        Boolean[][] dp = new Boolean[n][sum + 1];
        return helperSubset(arr, sum, 0, 0, dp);
    }

    // Subset Sum using Tabulation (Bottom-Up)
    // GFG: https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
    static Boolean isSubsetSumTab(int arr[], int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Initialization
        for (int i = 0; i <= n; i++) dp[i][0] = true;

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j >= arr[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Print the final DP table as T/F
        System.out.println("\nFinal DP Table (T/F):");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                System.out.print(dp[i][j] ? "T\t" : "F\t");
            }
            System.out.println();
        }

        return dp[n][sum];
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------
    // Question 3 : ---------------------------------------------Start-------------------------------------------------
    // Equal Partition Problem using Memoization
    // GFG: https://www.geeksforgeeks.org/problems/partition-equal-subset-sum/0
    static boolean helperEqualPartition(int[] arr, int idx, Boolean[][] dp, int target) {
        if (target == 0) return true;
        if (idx >= arr.length || target < 0) return false;

        if (dp[idx][target] != null) return dp[idx][target];

        boolean include = helperEqualPartition(arr, idx + 1, dp, target - arr[idx]);
        boolean notInclude = helperEqualPartition(arr, idx + 1, dp, target);

        return dp[idx][target] = include || notInclude;
    }

    static boolean equalPartitionMemo(int arr[]) {
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 != 0) return false;

        int target = sum / 2;
        Boolean[][] dp = new Boolean[n][target + 1];
        return helperEqualPartition(arr, 0, dp, target);
    }

    // Equal Partition using Tabulation
    // GFG: https://www.geeksforgeeks.org/problems/partition-equal-subset-sum/0
    static boolean equalPartitionTab(int arr[]) {
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[][] dp = new boolean[n][target + 1];

        for (int i = 0; i < n; i++) dp[i][0] = true;

        if (arr[0] <= target) dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= arr[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Print the final DP table (optional)
        System.out.println("\nFinal Equal Partition DP Table (T/F):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[i][j] ? "T\t" : "F\t");
            }
            System.out.println();
        }

        return dp[n - 1][target];
    }
    // Question 3 : ---------------------------------------------End-------------------------------------------------
    // Question 4 : ---------------------------------------------Start-------------------------------------------------
    // Perfect Sum Problem
    // GFG: https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1
    // Method 1: Simple Recursion (No Memoization)
    static int countPerfectSumRecursive(int[] nums, int target) {
        return helperPerfectSumRec(nums, target, 0, 0);
    }

    static int helperPerfectSumRec(int[] nums, int target, int idx, int sum) {
        if (idx == nums.length) return sum == target ? 1 : 0;

        sum += nums[idx];
        int include = helperPerfectSumRec(nums, target, idx + 1, sum);

        sum -= nums[idx];
        int exclude = helperPerfectSumRec(nums, target, idx + 1, sum);

        return include + exclude;
    }

    // Method 2: Memoization (Top-Down DP)
    static int countPerfectSumMemo(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        return helperPerfectSumMemo(nums, target, 0, 0, dp);
    }

    static int helperPerfectSumMemo(int[] nums, int target, int idx, int sum, int[][] dp) {
        if (idx == nums.length) return sum == target ? 1 : 0;

        if (dp[idx][sum] != -1) return dp[idx][sum];

        int include = 0;
        if (sum + nums[idx] <= target) {
            include = helperPerfectSumMemo(nums, target, idx + 1, sum + nums[idx], dp);
        }

        int exclude = helperPerfectSumMemo(nums, target, idx + 1, sum, dp);

        return dp[idx][sum] = include + exclude;
    }

    // Method 3: Tabulation (Bottom-Up DP with Table Print)
    static int countPerfectSumTab(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];

        // Initialize
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // sum = 0 can be achieved by empty subset
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Print DP Table
        System.out.println("\nPerfect Sum DP Table:");
        System.out.print("      ");
        for (int j = 0; j <= target; j++) {
            System.out.print(j + "\t");
        }
        System.out.println("\n-----------------------------------------------------");
        for (int i = 0; i <= n; i++) {
            System.out.print("i=" + i + " | ");
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }

        return dp[n][target];
    }
    // Question 4 : ---------------------------------------------End-------------------------------------------------
    // Question 5 : ---------------------------------------------Start-------------------------------------------------
    // Minimum Subset Sum Difference
    // GFG: https://www.geeksforgeeks.org/problems/minimum-sum-partition3317/1
    // ------------------------ Method 1 : Recursion ------------------------
    public static int minDifferenceRecursive(int[] arr) {
        return helper(arr, 0, 0, 0);
    }

    private static int helper(int[] arr, int idx, int sum1, int sum2) {
        if (idx == arr.length) {
            return Math.abs(sum1 - sum2);
        }
        int set1 = helper(arr, idx + 1, sum1 + arr[idx], sum2);
        int set2 = helper(arr, idx + 1, sum1, sum2 + arr[idx]);
        return Math.min(set1, set2);
    }

    // ------------------------ Method 2 : Memoization ------------------------
    public static int minDifferenceMemo(int[] arr) {
        int n = arr.length;
        int total = Arrays.stream(arr).sum();
        int[][] dp = new int[n][total + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helperMemo(arr, 0, 0, dp, total);
    }

    private static int helperMemo(int[] arr, int idx, int sum1, int[][] dp, int total) {
        if (idx == arr.length) {
            int sum2 = total - sum1;
            return Math.abs(sum1 - sum2);
        }
        if (dp[idx][sum1] != -1) return dp[idx][sum1];

        int set1 = helperMemo(arr, idx + 1, sum1 + arr[idx], dp, total);
        int set2 = helperMemo(arr, idx + 1, sum1, dp, total);

        return dp[idx][sum1] = Math.min(set1, set2);
    }

    // ------------------------ Method 3 : Tabulation ------------------------
    public static int minDifferenceTab(int[] arr) {
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j >= arr[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println("\nFinal DP Table (T/F):");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                System.out.print(dp[i][j] ? "T\t" : "F\t");
            }
            System.out.println();
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= sum / 2; i++) {
            if (dp[n][i]) {
                int sum2 = sum - i;
                ans = Math.min(ans, Math.abs(sum2 - i));
            }
        }

        return ans;
    }
    // Question 5 : ---------------------------------------------End---------------------------------------------------
    // Question 6 : ---------------------------------------------Start-------------------------------------------------
    // Count Partitions With Given Difference
    // GFG: https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1

    // Method 1 : Pure Recursion
    static int helperPartitionDiff(int[] arr, int d, int idx, int sum1, int total) {
        if (idx == arr.length) {
            int sum2 = total - sum1;
            return (sum1 >= sum2 && sum1 - sum2 == d) ? 1 : 0;
        }
        int include = helperPartitionDiff(arr, d, idx + 1, sum1 + arr[idx], total);
        int exclude = helperPartitionDiff(arr, d, idx + 1, sum1, total);
        return include + exclude;
    }

    static int countPartitionsRec(int[] arr, int d) {
        int total = Arrays.stream(arr).sum();
        return helperPartitionDiff(arr, d, 0, 0, total);
    }


    // Method 2 : Memoization
    static int helperPartitionDiffMemo(int[] arr, int d, int idx, int sum1, int total, int[][] dp) {
        if (idx == arr.length) {
            int sum2 = total - sum1;
            return (sum1 >= sum2 && sum1 - sum2 == d) ? 1 : 0;
        }
        if (dp[idx][sum1] != -1) return dp[idx][sum1];

        int include = helperPartitionDiffMemo(arr, d, idx + 1, sum1 + arr[idx], total, dp);
        int exclude = helperPartitionDiffMemo(arr, d, idx + 1, sum1, total, dp);

        return dp[idx][sum1] = include + exclude;
    }

    static int countPartitionsMemo(int[] arr, int d) {
        int total = Arrays.stream(arr).sum();
        int n = arr.length;
        int[][] dp = new int[n + 1][total + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helperPartitionDiffMemo(arr, d, 0, 0, total, dp);
    }


    // Method 3 : Tabulation (Reformulated as Subset Sum to Target = (sum + d) / 2)
    static int countPartitionsTab(int[] arr, int d) {
        int n = arr.length;
        int total = Arrays.stream(arr).sum();

        if ((total + d) % 2 != 0) return 0;
        int target = (total + d) / 2;

        int[][] dp = new int[n + 1][target + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Print final DP table
        System.out.println("\nFinal DP Table (Tabulation):");
        for (int[] row : dp) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }

        return dp[n][target];
    }
    // Question 6 : ---------------------------------------------End---------------------------------------------------
    // Question 7 : ---------------------------------------------Start-------------------------------------------------
    // Target Sum Problem
    // LeetCode: https://leetcode.com/problems/target-sum/

    // Method 1 : Recursion (Without Memoization)
    static int helperTargetSumRec(int[] nums, int target, int sum, int idx) {
        if (idx == nums.length) {
            return sum == target ? 1 : 0;
        }

        int include = helperTargetSumRec(nums, target, sum + nums[idx], idx + 1);
        int exclude = helperTargetSumRec(nums, target, sum - nums[idx], idx + 1);

        return include + exclude;
    }

    static int findTargetSumWaysRec(int[] nums, int target) {
        return helperTargetSumRec(nums, target, 0, 0);
    }

    // Method 2 : Memoization (Top-Down DP)
    static int helperTargetSumMemo(int[] nums, int target, int sum, int idx, int[][] dp, int offset) {
        if (idx == nums.length) {
            return sum == target ? 1 : 0;
        }

        if (dp[idx][sum + offset] != -1) {
            return dp[idx][sum + offset];
        }

        int include = helperTargetSumMemo(nums, target, sum + nums[idx], idx + 1, dp, offset);
        int exclude = helperTargetSumMemo(nums, target, sum - nums[idx], idx + 1, dp, offset);

        return dp[idx][sum + offset] = include + exclude;
    }

    static int findTargetSumWaysMemo(int[] nums, int target) {
        int total = Arrays.stream(nums).sum();
        int n = nums.length;

        // offset used to handle negative indices
        int[][] dp = new int[n][2 * total + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        return helperTargetSumMemo(nums, target, 0, 0, dp, total);
    }
    // Question 7 : ---------------------------------------------End---------------------------------------------------

    public static void main(String[] args) {

        // ======================== Question 1 Test ========================
        System.out.println("=============== QUESTION 1: 0/1 KNAPSACK ===============");
        int[] val = {1, 2, 3, 4, 5};
        int[] wt = {1, 2, 3, 4, 5};
        int W = 5;

        int maxMemo = knapsackMemo(W, val, wt);
        int maxTab = knapsackTab(W, val, wt);

        System.out.println("\nMaximum Value (Memoization): " + maxMemo);
        System.out.println("Maximum Value (Tabulation): " + maxTab);


       // ======================== Question 2 Test ========================
        System.out.println("\n=============== QUESTION 2: SUBSET SUM PROBLEM ===============");
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;

        boolean resultMemo = isSubsetSumMemo(arr, sum);
        boolean resultTab = isSubsetSumTab(arr, sum);

        System.out.println("Is Subset with Sum " + sum + " Present? (Memoization): " + resultMemo);
        System.out.println("Is Subset with Sum " + sum + " Present? (Tabulation): " + resultTab);
        
        // ======================== Question 3 Test ========================
        System.out.println("\n=============== QUESTION 3: EQUAL PARTITION ===============");
        int[] nums = {1, 5, 11, 5};

        boolean eqMemo = equalPartitionMemo(nums);
        boolean eqTab = equalPartitionTab(nums);

        System.out.println("\nCan array be partitioned into two equal subsets? (Memoization): " + eqMemo);
        System.out.println("Can array be partitioned into two equal subsets? (Tabulation): " + eqTab);
        
        // ======================== Question 4 Test ========================
        System.out.println("\n=============== QUESTION 4: PERFECT SUM PROBLEM ===============");
        int[] nums4 = {1, 2, 3, 3};
        int target4 = 6;

        int resRec = countPerfectSumRecursive(nums4, target4);
        int resMemo = countPerfectSumMemo(nums4, target4);
        int resTab = countPerfectSumTab(nums4, target4);

        System.out.println("Perfect Sum Count (Recursive): " + resRec);
        System.out.println("Perfect Sum Count (Memoization): " + resMemo);
        System.out.println("Perfect Sum Count (Tabulation): " + resTab);
        
        System.out.println("\n====== QUESTION 5: MINIMUM SUBSET SUM DIFFERENCE ======");
        int[] arr5 = {1, 6, 7, 5};

        System.out.println("\nMethod 1 (Recursive):");
        System.out.println("Minimum Difference: " + minDifferenceRecursive(arr5));

        System.out.println("\nMethod 2 (Memoization):");
        System.out.println("Minimum Difference: " + minDifferenceMemo(arr5));

        System.out.println("\nMethod 3 (Tabulation):");
        System.out.println("Minimum Difference: " + minDifferenceTab(arr5));
        
        // ======================== Question 6 Test ========================
        System.out.println("\n=============== QUESTION 6: COUNT PARTITIONS WITH GIVEN DIFFERENCE ===============");

        int[] arr6= {1, 1, 2, 3};
        int d = 1;

        int resultRec6 = countPartitionsRec(arr6, d);
        int resultMemo6 = countPartitionsMemo(arr6, d);
        int resultTab6 = countPartitionsTab(arr6, d);

        System.out.println("\nCount of Partitions (Recursion): " + resultRec6);
        System.out.println("Count of Partitions (Memoization): " + resultMemo6);
        System.out.println("Count of Partitions (Tabulation): " + resultTab6);
        System.out.println("\n=============== QUESTION 7: TARGET SUM PROBLEM ===============");
        int[] nums7 = {1, 1, 1, 1, 1};
        int target7 = 3;

        int resultRec7 = findTargetSumWaysRec(nums7, target7);
        int resultMemo7 = findTargetSumWaysMemo(nums7, target7);

        System.out.println("Target Sum Ways (Recursive): " + resultRec7);
        System.out.println("Target Sum Ways (Memoization): " + resultMemo7);

    }
}
