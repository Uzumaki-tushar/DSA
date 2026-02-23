// Question
// You are given an integer array nums and an integer target.

// You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

// For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
// Return the number of different expressions that you can build, which evaluates to target.

 

// Example 1:

// Input: nums = [1,1,1,1,1], target = 3
// Output: 5
// Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
// -1 + 1 + 1 + 1 + 1 = 3
// +1 - 1 + 1 + 1 + 1 = 3
// +1 + 1 - 1 + 1 + 1 = 3
// +1 + 1 + 1 - 1 + 1 = 3
// +1 + 1 + 1 + 1 - 1 = 3

// Example 2:
// Input: nums = [1], target = 1
// Output: 1


// *---------------------------------------------------------------------------------------------------------------------------------*

// approach
// Knapsack problem either we can plus or minus two option for each index

// fn(nums,target,ind,0)

// bc --> 
// if(ind==0) {
    // if(target==sum) return 1
    // return 0
// }

// add= fn(nums,target,ind-1,sum+nums[ind])

// minus=fn(nums,target,ind-1,sum-nums[ind])

// return pick + not_pick

//  *----------------------------------------------------------------------------------------------------------------------------------*


class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n=nums.length;
        int sum=0;
        for(var i:nums) sum+=i;

        int[][] dp = new int[n][2*sum+1];
        for(int i=0;i<n;i++) Arrays.fill(dp[i],-1);

        return solve(nums,target,n-1,dp,sum,0);
    }

    public int solve(int[] nums,int target,int ind,int[][] dp,int sum,int total){
        if(ind<0){
            if(total==target) return 1;
            return 0;
        }

        if(dp[ind][total+sum]!=-1) return dp[ind][total+sum];

        int p=solve(nums,target,ind-1,dp,sum,total+nums[ind]);

        int m=solve(nums,target,ind-1,dp,sum,total-nums[ind]);

        return dp[ind][total+sum]=p+m;
    }
}

