class Solution {
    public String customSortString(String order, String s) {
        int[] freq = new int[26];

        // Count frequency of each character in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder result = new StringBuilder();

        // Add characters according to order
        for (char c : order.toCharArray()) {
            while (freq[c - 'a'] > 0) {
                result.append(c);
                freq[c - 'a']--;
            }
        }
        for (char c = 'a'; c <= 'z'; c++) {
            while (freq[c - 'a'] > 0) {
                result.append(c);
                freq[c - 'a']--;
            }
        }

        return result.toString();
    }
}