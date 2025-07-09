/*
DP on Sequences Links (in order):
1. Longest Repeating Subsequence: https://www.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1
2. Is Subsequence: https://leetcode.com/problems/is-subsequence/
3. Minimum Insertions to Make String Palindrome: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
*/

public class B2_Day_22_DP_on_Sequences {

    // ========================= Question 1: Longest Repeating Subsequence =============================
    // Link: https://www.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1
    public static int longestRepeatingSubsequence(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n];
    }

    // ========================= Question 2: Is Subsequence =============================
    // Link: https://leetcode.com/problems/is-subsequence/

    // 🔹 Method 1: Two-pointer approach
    public static boolean isSubsequenceMethod1(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();

        int p1 = 0;
        int p2 = 0;

        while (p1 < n1 && p2 < n2) {
            if (s.charAt(p1) == t.charAt(p2)) {
                p1++;
            }
            p2++;
        }

        return p1 == n1;
    }

    // 🔹 Method 2: Dynamic Programming (LCS-based)
    public static boolean isSubsequenceMethod2(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();

        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n1][n2] == n1;
    }

    // ========================= Question 3: Minimum Insertions to Make String Palindrome =============================
    // Link: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
    public static int minInsertionsToPalindrome(String s) {
        int n = s.length();
        String s1 = s;
        String s2 = new StringBuffer(s).reverse().toString();

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return n - dp[n][n];
    }

    // ========================= main() =============================
    public static void main(String[] args) {

        System.out.println("=============== QUESTION 1: Longest Repeating Subsequence ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1");
        String input1 = "aabb";
        int result1 = longestRepeatingSubsequence(input1);
        System.out.println("Result: " + result1 + "\n");

        System.out.println("=============== QUESTION 2: Is Subsequence ===============");
        System.out.println("Link: https://leetcode.com/problems/is-subsequence/");
        String s2 = "abc";
        String t2 = "ahbgdc";
        boolean result2a = isSubsequenceMethod1(s2, t2);
        boolean result2b = isSubsequenceMethod2(s2, t2);
        System.out.println("Method 1 (Two-pointer): " + result2a);
        System.out.println("Method 2 (DP based): " + result2b + "\n");

        System.out.println("=============== QUESTION 3: Minimum Insertions to Make String Palindrome ===============");
        System.out.println("Link: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/");
        String input3 = "mbadm";
        int result3 = minInsertionsToPalindrome(input3);
        System.out.println("Result: " + result3 + "\n");
    }
}
