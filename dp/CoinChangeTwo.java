// You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

// Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

// You may assume that you have an infinite number of each kind of coin.

// The answer is guaranteed to fit into a signed 32-bit integer.

 

// Example 1:

// Input: amount = 5, coins = [1,2,5]
// Output: 4
// Explanation: there are four ways to make up the amount:
// 5=5
// 5=2+2+1
// 5=2+1+1+1
// 5=1+1+1+1+1
// Example 2:

// Input: amount = 3, coins = [2]
// Output: 0
// Explanation: the amount of 3 cannot be made up just with coins of 2.
// Example 3:

// Input: amount = 10, coins = [10]
// Output: 1


class Solution {
    public int change(int amount, int[] coins) {
        int n=coins.length;
        int[][] dp = new int[n][amount+1];
        for(int i=0;i<n;i++) Arrays.fill(dp[i],-1);
        return solve(amount,coins,n-1,dp);
    }

    public int solve(int amount,int[] coins,int ind,int[][] dp){
        if(ind<0) return 0;
        if(amount==0) return 1;
        if(dp[ind][amount]!=-1) return dp[ind][amount];

        // np
        int np=solve(amount,coins,ind-1,dp);

        // p
        int p=0;
        if(amount>=coins[ind]) p=solve(amount-coins[ind],coins,ind,dp);

        return dp[ind][amount]=p+np;
    }
}
