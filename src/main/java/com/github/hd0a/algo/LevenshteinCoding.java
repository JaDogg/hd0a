package com.github.hd0a.algo;

/**
 * Day 03 - Levenshtein Coding
 */
public class LevenshteinCoding {
    public String encode(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number should be positive");
        } else if (number == 0) {
            return "0";
        }

        int stepCount = 0;
        int curNumber = number;
        var code = new StringBuilder();

        do {
            stepCount++;
            var binaryString = Integer.toBinaryString(curNumber);
            var withoutLeadingOne = binaryString.substring(1);
            code.insert(0, withoutLeadingOne);

            curNumber = withoutLeadingOne.length();
        } while (curNumber != 0);

        return "1".repeat(stepCount) + "0" + code.toString();
    }

    public int decode(String number) {
        int firstZeroLocation = number.indexOf("0");
        if (firstZeroLocation <= 0) {
            return 0;
        }
        int num = 1;
        int pos = firstZeroLocation + 1; // skip zero
        for (int i = 0; i < firstZeroLocation - 1; i++) {
            var nBits = new StringBuilder(number.substring(pos, pos + num));
            pos = pos + num;
            nBits.insert(0, "1");
            num = Integer.parseInt(nBits.toString(), 2);
        }
        return num;
    }
}
