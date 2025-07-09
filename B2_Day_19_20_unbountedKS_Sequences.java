/*
Unbounded Knapsack, LCS, and Related Problems Links (in order):
1. Rod Cutting: https://www.geeksforgeeks.org/problems/rod-cutting0840/1
2. Coin Change: https://www.geeksforgeeks.org/problems/coin-change2448/1
3. Longest Common Subsequence: https://www.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1
4. Longest Common Substring: https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
5. Print Longest Common Subsequence: https://www.geeksforgeeks.org/problems/print-longest-common-subsequence/1
*/

import java.util.Arrays;

public class B2_Day_19_20_unbountedKS_sequences  {

    // Question 1 : ---------------------------------------------Start-------------------------------------------------
    // https://www.geeksforgeeks.org/problems/rod-cutting0840/1
    static int cutRodTab(int[] price) {
        int n = price.length;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= i) {
                    dp[i][j] = Math.max(dp[i - 1][j], price[i - 1] + dp[i][j - i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println("\nRod Cutting DP Table:");
        for (int[] row : dp) {
            for (int val : row) System.out.print(val + "\t");
            System.out.println();
        }

        return dp[n][n];
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------


    // Question 2 : ---------------------------------------------Start-------------------------------------------------
    // https://www.geeksforgeeks.org/problems/coin-change2448/1
    static int coinChangeTab(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i - 1] && dp[i][j - coins[i - 1]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println("\nCoin Change DP Table:");
        for (int[] row : dp) {
            for (int val : row) System.out.print((val == Integer.MAX_VALUE ? "∞" : val) + "\t");
            System.out.println();
        }

        return dp[n][amount] == Integer.MAX_VALUE ? -1 : dp[n][amount];
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------


    // Question 3 : ---------------------------------------------Start-------------------------------------------------
    // https://www.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1

    // Method 1: Memoization
    static int lcsMemo(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helperLCS(text1, text2, text1.length() - 1, text2.length() - 1, dp);
    }

    static int helperLCS(String t1, String t2, int i, int j, int[][] dp) {
        if (i < 0 || j < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        if (t1.charAt(i) == t2.charAt(j)) {
            return dp[i][j] = 1 + helperLCS(t1, t2, i - 1, j - 1, dp);
        }

        int a = helperLCS(t1, t2, i - 1, j, dp);
        int b = helperLCS(t1, t2, i, j - 1, dp);
        return dp[i][j] = Math.max(a, b);
    }

    // Method 2: Tabulation
    static int lcsTab(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Print DP Table
        System.out.println("\nLCS DP Table:");
        for (int[] row : dp) {
            for (int val : row) System.out.print(val + "\t");
            System.out.println();
        }

        return dp[n1][n2];
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------


    // Question 4 : ---------------------------------------------Start-------------------------------------------------
    // https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
    static int longestCommonSubstring(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        int ans = 0;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        System.out.println("\nLongest Common Substring DP Table:");
        for (int[] row : dp) {
            for (int val : row) System.out.print(val + "\t");
            System.out.println();
        }

        return ans;
    }
    // Question 4 : ---------------------------------------------End---------------------------------------------------
    
    // Question 5 : ---------------------------------------------Start-------------------------------------------------
    // https://www.geeksforgeeks.org/problems/print-longest-common-subsequence/1
    static String printLCS(String X, String Y) {
        int n = X.length();
        int m = Y.length();
        int[][] dp = new int[n + 1][m + 1];

        // Build the DP table
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // Backtrack to build the LCS string
        int i = n, j = m;
        StringBuilder lcs = new StringBuilder();

        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcs.append(X.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                j--;
            } else {
                i--;
            }
        }

        // LCS is built backwards, so reverse it
        return lcs.reverse().toString();
    }
    // Question 5 : ---------------------------------------------End---------------------------------------------------


    public static void main(String[] args) {

        // ======================== Question 1 Test ========================
        System.out.println("=============== QUESTION 1: ROD CUTTING ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/rod-cutting0840/1");
        int[] price = {1, 5, 8, 9, 10};
        System.out.println("Max Rod Cut Value: " + cutRodTab(price));

        // ======================== Question 2 Test ========================
        System.out.println("\n=============== QUESTION 2: COIN CHANGE ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/coin-change2448/1");
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Min Coins Needed: " + coinChangeTab(coins, amount));

        // ======================== Question 3 Test ========================
        System.out.println("\n=============== QUESTION 3: LONGEST COMMON SUBSEQUENCE ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1");
        String a = "abcde", b = "ace";
        System.out.println("LCS Length (Memoization): " + lcsMemo(a, b));
        System.out.println("LCS Length (Tabulation): " + lcsTab(a, b));

        // ======================== Question 4 Test ========================
        System.out.println("\n=============== QUESTION 4: LONGEST COMMON SUBSTRING ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/longest-common-substring1452/1");
        String s1 = "abcde", s2 = "abfce";
        System.out.println("Length of Longest Common Substring: " + longestCommonSubstring(s1, s2));
        
         // ======================== Question 5 Test ========================
        System.out.println("=============== QUESTION 5: PRINT LCS STRING ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/print-longest-common-subsequence/1");
        String x = "AGGTAB";
        String y = "GXTXAYB";
        System.out.println("LCS String: " + printLCS(x, y));
    
    }
}
