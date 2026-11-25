class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long mod1 = 1_000_000_007L;
        long mod2 = 1_000_000_009L;
        long base = 911382323L;

        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];
        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];

        p1[0] = 1;
        p2[0] = 1;

        for (int i = 0; i < n; i++) {
            p1[i + 1] = p1[i] * base % mod1;
            p2[i + 1] = p2[i] * base % mod2;

            h1[i + 1] = (h1[i] * base + nums[i]) % mod1;
            h2[i + 1] = (h2[i] * base + nums[i]) % mod2;
        }
        int left = 1, right = n;
        int ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            HashMap<Long, Integer> freq = new HashMap<>();

            for (int i = 0; i + mid <= n; i++) {
                long x = (h1[i + mid]
                        - h1[i] * p1[mid] % mod1 + mod1) % mod1;

                long y = (h2[i + mid]
                        - h2[i] * p2[mid] % mod2 + mod2) % mod2;

                long key = x * mod2 + y;

                freq.put(key, freq.getOrDefault(key, 0) + 1);
            }
            boolean found = false;

            for (int count : freq.values()) {
                if (count == 1) {
                    found = true;
                    break;
                }
            }
            if (found) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}