class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        // Step 1: Ignore leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: Check sign
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        // Step 3: Convert digits
        long result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');

            // Step 4: Handle overflow
            if (sign * result >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign * result <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        return (int) (sign * result);
    }
}
