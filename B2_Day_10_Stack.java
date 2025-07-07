
    // ========================= Question List =============================
    // Question 1: The Celebrity Problem
    // 🔗 https://www.geeksforgeeks.org/problems/the-celebrity-problem/1
    // Question 2: Trapping Rain Water
    // 🔗 https://leetcode.com/problems/trapping-rain-water/submissions/1230659371/
    // Question 3: First Non-Repeating Character in a Stream
    // 🔗 https://www.geeksforgeeks.org/problems/first-non-repeating-character-in-a-stream/0

import java.util.*;




public class B2_Day_10_Stack {


    // ========================= Question 1 =============================
    public static int celebrity(int[][] mat) {
        int n = mat.length;
        if (n < 2) return 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            st.push(i);
        }

        while (st.size() > 1) {
            int a = st.pop();
            int b = st.pop();

            if (mat[a][b] == 1) {
                st.push(b);
            } else {
                st.push(a);
            }
        }

        int ans = st.pop();

        for (int i = 0; i < n; i++) {
            if (i != ans) {
                if (mat[i][ans] != 1 || mat[ans][i] == 1) {
                    return -1;
                }
            }
        }

        return ans;
    }

    // ========================= Question 2 =============================
    public static int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] left = new int[n];
        int[] right = new int[n];
        int storedWater = 0;

        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        for (int i = 0; i < n; i++) {
            int minHeight = Math.min(left[i], right[i]);
            storedWater += minHeight - height[i];
        }

        return storedWater;
    }

    // ========================= Question 3 =============================
    public static String firstNonRepeating(String s) {
        int n = s.length();
        Queue<Character> q = new LinkedList<>();
        int[] freq = new int[26];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']++;
            q.add(ch);

            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.remove();
            }

            if (!q.isEmpty()) {
                sb.append(q.peek());
            } else {
                sb.append('#');
            }
        }

        return sb.toString();
    }

    // ========================= Main Method =============================
    public static void main(String[] args) {
        System.out.println("=============== QUESTION 1: The Celebrity Problem ===============");
        int[][] mat = {
            {0, 1, 0},
            {0, 0, 0},
            {0, 1, 0}
        };
        System.out.println(celebrity(mat));

        System.out.println("=============== QUESTION 2: Trapping Rain Water ===============");
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));

        System.out.println("=============== QUESTION 3: First Non-Repeating Character in a Stream ===============");
        String stream = "aabc";
        System.out.println(firstNonRepeating(stream));
    }
}
