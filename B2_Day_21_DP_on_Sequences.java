/*
Questions in this file:
1. Shortest Common Supersequence - https://leetcode.com/problems/shortest-common-supersequence/
2. Minimum Deletions to Make Strings Equal - https://leetcode.com/problems/delete-operation-for-two-strings/
3. Longest Palindromic Subsequence - https://leetcode.com/problems/longest-palindromic-subsequence/
4. Minimum Deletions to Make a String Palindrome - https://www.geeksforgeeks.org/problems/minimum-deletions/0/
*/

public class B2_Day_21_DP_on_Sequences {

    // ========================= Question 1: Shortest Common Supersequence =============================
    // Link: https://leetcode.com/problems/shortest-common-supersequence/
    public static String shortestCommonSupersequence(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        printLCS(str1, str2, dp);

        int row = n1;
        int col = n2;
        StringBuilder sb = new StringBuilder();
        while (row > 0 && col > 0) {
            if (str1.charAt(row - 1) == str2.charAt(col - 1)) {
                sb.append(str1.charAt(row - 1));
                row--;
                col--;
            } else if (dp[row - 1][col] > dp[row][col - 1]) {
                sb.append(str1.charAt(row - 1));
                row--;
            } else {
                sb.append(str2.charAt(col - 1));
                col--;
            }
        }

        while (row > 0) {
            sb.append(str1.charAt(row - 1));
            row--;
        }
        while (col > 0) {
            sb.append(str2.charAt(col - 1));
            col--;
        }

        return sb.reverse().toString();
    }

    private static void printLCS(String str1, String str2, int[][] dp) {
        int i = str1.length();
        int j = str2.length();
        StringBuilder lcs = new StringBuilder();

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.println("LCS: " + lcs.reverse().toString());
    }

    // ========================= Question 2: Minimum Deletions to Make Strings Equal =============================
    // Link: https://leetcode.com/problems/delete-operation-for-two-strings/
    public static int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return n1 + n2 - 2 * dp[n1][n2];
    }

    // ========================= Question 3: Longest Palindromic Subsequence =============================
    // Link: https://leetcode.com/problems/longest-palindromic-subsequence/
    public static int longestPalindromeSubseq(String s) {
        String word1 = s;
        String word2 = new StringBuilder(s).reverse().toString();

        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n1][n2];
    }

    // ========================= Question 4: Minimum Deletions to Make a String Palindrome =============================
    // Link: https://www.geeksforgeeks.org/problems/minimum-deletions/0/
    public static int minDeletions(String s) {
        String word1 = s;
        String word2 = new StringBuilder(s).reverse().toString();

        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return s.length() - dp[n1][n2];
    }

    // ========================= main() =============================
    public static void main(String[] args) {

        System.out.println("=============== QUESTION 1: Shortest Common Supersequence ===============");
        System.out.println("Link: https://leetcode.com/problems/shortest-common-supersequence/");
        String str1 = "abac";
        String str2 = "cab";
        String result1 = shortestCommonSupersequence(str1, str2);
        System.out.println("Result: " + result1 + "\n");

        System.out.println("=============== QUESTION 2: Minimum Deletions to Make Strings Equal ===============");
        System.out.println("Link: https://leetcode.com/problems/delete-operation-for-two-strings/");
        String word1 = "sea";
        String word2 = "eat";
        int result2 = minDistance(word1, word2);
        System.out.println("Result: " + result2 + "\n");

        System.out.println("=============== QUESTION 3: Longest Palindromic Subsequence ===============");
        System.out.println("Link: https://leetcode.com/problems/longest-palindromic-subsequence/");
        String s = "bbbab";
        int result3 = longestPalindromeSubseq(s);
        System.out.println("Result: " + result3 + "\n");

        System.out.println("=============== QUESTION 4: Minimum Deletions to Make a String Palindrome ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/minimum-deletions/0/");
        String input4 = "aebcbda";
        int result4 = minDeletions(input4);
        System.out.println("Result: " + result4 + "\n");
    }
}
