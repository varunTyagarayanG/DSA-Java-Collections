/*
DSA Recursion Questions Links (in order):
1. Delete Middle Element of Stack: https://www.geeksforgeeks.org/delete-middle-element-stack/
2. Reverse a Stack: https://www.geeksforgeeks.org/problems/reverse-a-stack/1
3. K-th Symbol in Grammar: https://leetcode.com/problems/k-th-symbol-in-grammar/
4. Print Subsequences of a String: https://www.geeksforgeeks.org/print-subsequences-string/
5. Subsets: https://leetcode.com/problems/subsets/
6. Permutation with Spaces: https://www.geeksforgeeks.org/problems/permutation-with-spaces3627/1
7. Permute String by Changing Case: https://www.geeksforgeeks.org/permute-string-changing-case/
8. Letter Case Permutation (Alphanumeric): https://leetcode.com/problems/letter-case-permutation/
9. Generate Parentheses: https://leetcode.com/problems/generate-parentheses/
10. N-bit Binary Numbers with More 1s than 0s: https://www.geeksforgeeks.org/problems/print-n-bit-binary-numbers-having-more-1s-than-0s0252/1
*/
import java.util.*;

public class B2_Day_04_Recurssion {

    // Question 1: Delete Middle Element of Stack
    // https://www.geeksforgeeks.org/delete-middle-element-stack/
    public static void deleteMidUsingList(Stack<Integer> st, int size) {
        ArrayList<Integer> v = new ArrayList<>();
        while (!st.isEmpty()) {
            v.add(st.pop());
        }

        int mid = size / 2;
        v.remove(mid);

        for (int i = v.size() - 1; i >= 0; i--) {
            st.push(v.get(i));
        }
    }

    public static void deleteMidUsingRecursion(Stack<Integer> st, int size) {
        int k = (int) Math.ceil(size / 2.0);
        deleteHelper(st, k);
    }

    private static void deleteHelper(Stack<Integer> st, int k) {
        if (k == 1) {
            st.pop();
            return;
        }

        int top = st.pop();
        deleteHelper(st, k - 1);
        st.push(top);
    }

    // Question 2: Reverse a Stack
    // https://www.geeksforgeeks.org/problems/reverse-a-stack/1
    static void reverse(Stack<Integer> s) {
        if (s.isEmpty()) {
            return;
        }
        int top = s.pop();
        reverse(s);
        insertAtBottom(s, top);
    }

    static void insertAtBottom(Stack<Integer> s, int item) {
        if (s.isEmpty()) {
            s.push(item);
            return;
        }

        int top = s.pop();
        insertAtBottom(s, item);
        s.push(top);
    }

    // Question 3: K-th Symbol in Grammar
    // https://leetcode.com/problems/k-th-symbol-in-grammar/
    // TODO: Add implementation for Question 3 if needed

    // Question 4: Print Subsequences of a String
    // https://www.geeksforgeeks.org/print-subsequences-string/
    public static void generateAllSubsequences(String ip, String op) {
        if (ip.length() == 0) {
            System.out.println(op);
            return;
        }

        String op1 = op;
        String op2 = op + ip.charAt(0);
        ip = ip.substring(1);

        generateAllSubsequences(ip, op1);
        generateAllSubsequences(ip, op2);
    }

    // Question 5: Subsets
    // https://leetcode.com/problems/subsets/
    public static void helperSubsets(int[] nums, List<Integer> arr, List<List<Integer>> ans, int idx) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(arr));
            return;
        }
        arr.add(nums[idx]);
        helperSubsets(nums, arr, ans, idx + 1);
        arr.remove(arr.size() - 1);
        helperSubsets(nums, arr, ans, idx + 1);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        helperSubsets(nums, arr, ans, 0);
        return ans;
    }

    // Question 6: Permutation with Spaces
    // https://www.geeksforgeeks.org/problems/permutation-with-spaces3627/1
    public static void permutationSolver(String sb, String s, ArrayList<String> ans, int idx) {
        if (idx == s.length()) {
            ans.add(sb);
            return;
        }

        permutationSolver(sb + " " + s.charAt(idx), s, ans, idx + 1);
        permutationSolver(sb + s.charAt(idx), s, ans, idx + 1);
    }

    public static ArrayList<String> spaceInsertedPermutations(String s) {
        ArrayList<String> ans = new ArrayList<>();
        permutationSolver("" + s.charAt(0), s, ans, 1);
        return ans;
    }

    // Question 7: Permute String by Changing Case
    // https://www.geeksforgeeks.org/permute-string-changing-case/
    public static void permuteWithCasesHelper(String ip, String op, ArrayList<String> result) {
        if (ip.length() == 0) {
            result.add(op);
            return;
        }

        char ch = ip.charAt(0);
        String lower = ("" + ch).toLowerCase();
        String upper = ("" + ch).toUpperCase();
        String remaining = ip.substring(1);

        permuteWithCasesHelper(remaining, op + lower, result);
        permuteWithCasesHelper(remaining, op + upper, result);
    }

    public static ArrayList<String> permuteWithCases(String ip) {
        ArrayList<String> result = new ArrayList<>();
        permuteWithCasesHelper(ip, "", result);
        return result;
    }

    // Question 8: Letter Case Permutation (Alphanumeric)
    // https://leetcode.com/problems/letter-case-permutation/
    public static void letterCaseHelper(String sb, String s, List<String> ans, int idx) {
        if (idx == s.length()) {
            ans.add(sb);
            return;
        }

        char ch = s.charAt(idx);

        if (Character.isLetter(ch)) {
            letterCaseHelper(sb + Character.toLowerCase(ch), s, ans, idx + 1);
            letterCaseHelper(sb + Character.toUpperCase(ch), s, ans, idx + 1);
        } else {
            letterCaseHelper(sb + ch, s, ans, idx + 1);
        }
    }

    public static List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        letterCaseHelper("", s, ans, 0);
        return ans;
    }

    // Question 9: Generate Parentheses
    // https://leetcode.com/problems/generate-parentheses/
    public static void generateParenthesesHelper(String output, int open, int close, List<String> ans) {
        if (open == 0 && close == 0) {
            ans.add(output);
            return;
        }

        if (open > 0) {
            generateParenthesesHelper(output + "(", open - 1, close, ans);
        }

        if (close > open) {
            generateParenthesesHelper(output + ")", open, close - 1, ans);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesesHelper("", n, n, ans);
        return ans;
    }

    // Question 10: N-bit Binary Numbers with More 1s than 0s
    // https://www.geeksforgeeks.org/problems/print-n-bit-binary-numbers-having-more-1s-than-0s0252/1
    public static void getSorted(String op, int one, int zero, int N, List<String> result) {
        if (N == 0) {
            result.add(op);
            return;
        }

        // Always add '1'
        getSorted(op + "1", one + 1, zero, N - 1, result);

        // Add '0' only if one > zero
        if (one > zero) {
            getSorted(op + "0", one, zero + 1, N - 1, result);
        }
    }

    public static List<String> NBitBinary(int N) {
        List<String> result = new ArrayList<>();
        getSorted("1", 1, 0, N - 1, result);
        return result;
    }
    // Main
    public static void main(String[] args) {
        // Q1
        System.out.println("=============== QUESTION 1: DELETE MIDDLE ELEMENT ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/delete-middle-element-stack/");
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(10);
        stack1.push(20);
        stack1.push(30);
        stack1.push(40);
        stack1.push(50);
        System.out.println("Original Stack: " + stack1);
        deleteMidUsingRecursion(stack1, stack1.size());
        System.out.println("After Deleting Middle Element: " + stack1);

        // Q2
        System.out.println("\n=============== QUESTION 2: REVERSE STACK ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/reverse-a-stack/1");
        Stack<Integer> stack2 = new Stack<>();
        stack2.push(100);
        stack2.push(200);
        stack2.push(300);
        stack2.push(400);
        stack2.push(500);
        System.out.println("Original Stack: " + stack2);
        reverse(stack2);
        System.out.println("After Reversing Stack: " + stack2);

        // Q3
        System.out.println("\n=============== QUESTION 3: K-TH SYMBOL IN GRAMMAR ===============");
        System.out.println("Link: https://leetcode.com/problems/k-th-symbol-in-grammar/");
        // TODO: Add implementation for Question 3 if needed

        // Q4
        System.out.println("\n=============== QUESTION 4: PRINT SUBSEQUENCES ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/print-subsequences-string/");
        generateAllSubsequences("abc", "");

        // Q5
        System.out.println("\n=============== QUESTION 5: SUBSETS OF ARRAY ===============");
        System.out.println("Link: https://leetcode.com/problems/subsets/");
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = subsets(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }

        // Q6
        System.out.println("\n=============== QUESTION 6: SPACE INSERTED PERMUTATIONS ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/permutation-with-spaces3627/1");
        ArrayList<String> perms = spaceInsertedPermutations("ABC");
        for (String str : perms) {
            System.out.println(str);
        }

        // Q7
        System.out.println("\n=============== QUESTION 7: CASE CHANGE PERMUTATIONS ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/permute-string-changing-case/");
        ArrayList<String> casePerms = permuteWithCases("aB");
        for (String str : casePerms) {
            System.out.println(str);
        }

        // Q8
        System.out.println("\n=============== QUESTION 8: LETTER CASE PERMUTATION (with digits) ===============");
        System.out.println("Link: https://leetcode.com/problems/letter-case-permutation/");
        List<String> caseDigitPerms = letterCasePermutation("a1b2");
        for (String str : caseDigitPerms) {
            System.out.println(str);
        }
        
        System.out.println("\n=============== QUESTION 9: GENERATE PARENTHESES ===============");
        System.out.println("Link: https://leetcode.com/problems/generate-parentheses/");
        List<String> res9 = generateParenthesis(3);
        for (String s : res9) {
            System.out.println(s);
        }

        // Q10 Output
        System.out.println("\n=============== QUESTION 10: N-BIT BINARY WITH MORE 1s THAN 0s ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/print-n-bit-binary-numbers-having-more-1s-than-0s0252/1");
        List<String> res10 = NBitBinary(3);
        for (String s : res10) {
            System.out.println(s);
        }
    }
}
