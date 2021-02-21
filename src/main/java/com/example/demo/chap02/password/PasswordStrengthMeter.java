package com.example.demo.chap02.password;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) {
            return PasswordStrength.INVALID;
        }
        int metCount = getMetCriteriaCounts(s);

        if (metCount <= 1) {
            return PasswordStrength.WEAK;
        }
        if (metCount == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String s) {
        int metCount = 0;
        if (s.length() >= 8) {
            metCount++;
        }
        if (meetContainingNumberCriteria(s)) {
            metCount++;
        }
        if (meetContainingUppercaseCriteria(s)) {
            metCount++;
        }
        return metCount;
    }

    private boolean meetContainingNumberCriteria(String s) {
        boolean containsNum = false;
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                containsNum = true;
                break;
            }
        }
        return containsNum;
    }

    private boolean meetContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
