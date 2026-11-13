class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int index, int count,
                           List<String> parts, List<String> result) {

        if (count == 4) {
            if (index == s.length()) {
                result.add(String.join(".", parts));
            }
            return;
        }

        for (int len = 1; len <= 3; len++) {

            if (index + len > s.length()) {
                break;
            }

            String part = s.substring(index, index + len);

            // No leading zeros
            if (part.length() > 1 && part.charAt(0) == '0') {
                break;
            }

            // Value must be between 0 and 255
            int num = Integer.parseInt(part);

            if (num > 255) {
                break;
            }

            parts.add(part);

            backtrack(s, index + len, count + 1, parts, result);

            parts.remove(parts.size() - 1);
        }
    }
}