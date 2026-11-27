class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] dp = new int[budget + 1];
        java.util.Arrays.fill(dp, -1000000);
        dp[0] = 0;
        int min = Integer.MAX_VALUE;
        for (int[] x : items)
            min = Math.min(min, x[1]);
        for (int i = 0; i < n; i++) {
            int free = 0;
            for (int j = 0; j < n; j++)
                if (i != j && items[j][0] % items[i][0] == 0)
                    free++;
            for (int b = budget; b >= items[i][1]; b--)
                if (dp[b - items[i][1]] >= 0)
                    dp[b] = Math.max(dp[b],
                        dp[b - items[i][1]] + free + 1);
        }
        int ans = 0;
        for (int b = 0; b <= budget; b++)
            if (dp[b] >= 0)
                ans = Math.max(ans, dp[b] + (budget - b) / min);
        return ans;
    }
}