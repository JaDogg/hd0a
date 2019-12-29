package com.github.hd0a.algo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestParenthesisChecker {
    @Test
    public void testParenthesis() {
        Assert.assertTrue(ParenthesisChecker.isLineBalanced(""));
        Assert.assertTrue(ParenthesisChecker.isLineBalanced("()"));
        Assert.assertTrue(ParenthesisChecker.isLineBalanced("[]"));
        Assert.assertTrue(ParenthesisChecker.isLineBalanced("{}"));
        Assert.assertTrue(ParenthesisChecker.isLineBalanced("[()]{}{[()()]()}{}"));
        Assert.assertTrue(ParenthesisChecker.isLineBalanced("[({[(((([]))))]})]"));
        Assert.assertTrue(ParenthesisChecker.isLineBalanced("[({[(((([()]))))]})]"));
        Assert.assertFalse(ParenthesisChecker.isLineBalanced("[[[[[[[[["));
        Assert.assertFalse(ParenthesisChecker.isLineBalanced("[()]{}{[()()]()}{"));
        Assert.assertFalse(ParenthesisChecker.isLineBalanced("[)]{}{[()()]()}{"));
        Assert.assertFalse(ParenthesisChecker.isLineBalanced("["));
        Assert.assertFalse(ParenthesisChecker.isLineBalanced("("));
        Assert.assertFalse(ParenthesisChecker.isLineBalanced("{"));
    }
}
