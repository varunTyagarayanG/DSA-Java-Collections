import java.util.*;

// ========================= Question List =============================
// Question 1: Stock Span Problem
// 🔗 https://www.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
// Question 2: Maximum Rectangular Area in a Histogram
// 🔗 https://www.geeksforgeeks.org/problems/maximum-rectangular-area-in-a-histogram-1587115620/1
// Question 3: Maximal Rectangle
// 🔗 https://leetcode.com/problems/maximal-rectangle/

public class B2_Day_09_Stack {
    // ========================= Question 1 =============================
    public static ArrayList<Integer> calculateSpan(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }

            if (st.isEmpty()) {
                ans.add(i + 1);
            } else {
                ans.add(i - st.peek());
            }

            st.add(i);
        }

        return ans;
    }

    // ========================= Question 2 =============================
    public static int getMaxArea(int[] arr) {
        int n = arr.length;
        List<Integer> NSR = new ArrayList<>();
        List<Integer> NSL = new ArrayList<>();

        Stack<Integer> st = new Stack<>();

        // NSR - Nearest Smaller to Right
        // Loop Direction: Right → Left
        // Comparator: <
        // Stack Condition: arr[st.peek()] >= arr[i]
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            if (st.isEmpty()) {
                NSR.add(0, n);
            } else {
                NSR.add(0, st.peek());
            }
            st.add(i);
        }

        st.clear();

        // NSL - Nearest Smaller to Left
        // Loop Direction: Left → Right
        // Comparator: <
        // Stack Condition: arr[st.peek()] >= arr[i]
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            if (st.isEmpty()) {
                NSL.add(-1);  
            } else {
                NSL.add(st.peek());
            }
            st.add(i);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int width = NSR.get(i) - NSL.get(i) - 1;
            int area = arr[i] * width;
            ans = Math.max(ans, area);
        }

        return ans;
    }

    // ========================= Question 3 =============================
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[] hist = new int[col];
        int maxArea = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                hist[j] = (matrix[i][j] == '1') ? hist[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, getMaxArea(hist));
        }

        return maxArea;
    }

    // ========================= Main Method =============================
    public static void main(String[] args) {
        // 📌 Quick Summary:
        // NGL: Left → Right | > | arr[st.peek()] <= arr[i]
        // NGR: Right → Left | > | arr[st.peek()] <= arr[i]
        // NSL: Left → Right | < | arr[st.peek()] >= arr[i]
        // NSR: Right → Left | < | arr[st.peek()] >= arr[i]

        System.out.println("=============== QUESTION 1: Stock Span Problem ===============");
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        ArrayList<Integer> spans = calculateSpan(prices);
        System.out.println(spans);

        System.out.println("=============== QUESTION 2: Maximum Rectangular Area in a Histogram ===============");
        int[] histogram = {6, 2, 5, 4, 5, 1, 6};
        int maxArea = getMaxArea(histogram);
        System.out.println(maxArea);

        System.out.println("=============== QUESTION 3: Maximal Rectangle ===============");
        char[][] matrix = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        int maximalArea = maximalRectangle(matrix);
        System.out.println(maximalArea);
    }
}
