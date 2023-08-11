// 2a)
// Given an integer array nums and another integer k, the goal is to find the longest subsequence of nums that 
// satisfies the following two conditions: 
// The subsequence is strictly decreasing. 
// The difference between adjacent elements in the subsequence is at most k. 
// The output should be the length of the longest subsequence that meets these requirements. 
// For example, consider the following input: 
// nums = [8,5,4, 2, 1, 4, 3, 4, 3, 1, 15] k = 3 
// output=[8,5,4,2,1] or [8,5,4,3,1] 
// Output: 5 
// Explanation: 
// The longest subsequence that meets the requirements is [8,5,4,2,1] or [8,5,4,3,1]. 
// The subsequence has a length of 5, so we return 5. 
// Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3. 



public class LongestSubsequence {
    public static int longestSequence(int[] nums, int k) {

        // Initialize a dynamic programming array to keep track of the length of longest subsequences
        int[] dp = new int[nums.length];
        
        // Initialize each element of dp with 1, since the minimum length of a subsequence is 1
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        // Iterate over the array to fill in the dp array
        for (int i = 1; i < nums.length; i++) {
            // Iterate over the previous elements
            for (int j = 0; j < i; j++) {
                // Check if the difference between the current element and the previous element
                // is less than or equal to k and nums[i] is strictly less than nums[j]
                if (nums[j] - nums[i] <= k && nums[j] > nums[i]) {
                    // Check if the current element can be added to the previous element's subsequence
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1; // Update the length of the subsequence ending at index i
                    }
                }
            }
        }

        // Find the maximum value in the dp array, which represents the length of the longest subsequence
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    // Helper function to sort the input array in descending order
    public static void sortDesc(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] < nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 8, 5, 4, 2, 1, 4, 3, 4, 3, 1, 15 };
        int k = 3;
        
        // Call the longestSequence function to find the length of the longest subsequence
        int result = longestSequence(nums, k);

        System.out.println(result); // Print the result
    }
}
