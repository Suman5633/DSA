class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        // Step 1: Find maximum factor and minimum price
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Count frequency of factors
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Step 3: Count all divisible item types
        int[] count = new int[maxFactor + 1];

        for (int i = 1; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j += i) {
                count[i] += freq[j];
            }
        }

        // Step 4: Sort items by price
        Arrays.sort(items, (a, b) -> 
            Integer.compare(a[1], b[1])
        );

        long ans = 0;
        long remaining = budget;

        // Step 5: Buy items that can give free items
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            // Remove the current item itself
            int freeCount = count[factor] - 1;

            // Double-value purchase is not better
            if (price >= 2L * minPrice) {
                break;
            }
            if (freeCount <= 0) {
                continue;
            }

            long buy = Math.min(
                freeCount,
                remaining / price
            );

            ans += 2 * buy;
            remaining -= buy * price;
        }

        // Step 6: Spend remaining budget on cheapest items
        ans += remaining / minPrice;

        return (int) ans;
    }
}