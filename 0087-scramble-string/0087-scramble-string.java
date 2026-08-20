class Solution {
    private Boolean[][][] memo;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();

        if (n != s2.length()) {
            return false;
        }

        memo = new Boolean[n][n][n + 1];

        return solve(s1, s2, 0, 0, n);
    }

    private boolean solve(String s1, String s2,
                          int i, int j, int len) {

        // Already calculated
        if (memo[i][j][len] != null) {
            return memo[i][j][len];
        }

        // Same substring
        if (s1.regionMatches(i, s2, j, len)) {
            return memo[i][j][len] = true;
        }

        // Check character frequencies
        int[] count = new int[26];

        for (int k = 0; k < len; k++) {
            count[s1.charAt(i + k) - 'a']++;
            count[s2.charAt(j + k) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) {
                return memo[i][j][len] = false;
            }
        }

        // Try every possible split
        for (int split = 1; split < len; split++) {

            // No swap
            if (solve(s1, s2, i, j, split) &&
                solve(s1, s2, i + split, j + split, len - split)) {

                return memo[i][j][len] = true;
            }

            // Swap
            if (solve(s1, s2, i, j + len - split, split) &&
                solve(s1, s2, i + split, j, len - split)) {

                return memo[i][j][len] = true;
            }
        }

        return memo[i][j][len] = false;
    }
}