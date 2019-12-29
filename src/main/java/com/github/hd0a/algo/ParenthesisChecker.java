package com.github.hd0a.algo;

import java.util.Stack;

public final class ParenthesisChecker {
    private static final char PAREN_1_BEGIN = '(';
    private static final char PAREN_2_BEGIN = '[';
    private static final char PAREN_3_BEGIN = '{';
    private static final char PAREN_1_END = ')';
    private static final char PAREN_2_END = ']';
    private static final char PAREN_3_END = '}';

    private ParenthesisChecker() {
        throw new UnsupportedOperationException();
    }

    /**
     * Day 10 - Parenthesis checker
     * @param code code to check - checks for `(), [], {}`
     * @return balanced?
     */
    public static boolean isLineBalanced(String code) {
        final Stack<Character> parens = new Stack<>();
        for (char c : code.toCharArray()) {
            if (c == PAREN_1_BEGIN || c == PAREN_2_BEGIN || c == PAREN_3_BEGIN) {
                parens.push(c);
            } else if (c == PAREN_1_END && (parens.isEmpty() || parens.pop() != PAREN_1_BEGIN)) {
                return false;
            } else if (c == PAREN_2_END && (parens.isEmpty() || parens.pop() != PAREN_2_BEGIN)) {
                return false;
            } else if (c == PAREN_3_END && (parens.isEmpty() || parens.pop() != PAREN_3_BEGIN)) {
                return false;
            }
        }
        return parens.isEmpty();
    }
}
