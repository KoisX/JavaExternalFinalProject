package com.javacourse.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterSequenceGeneratorTest {

    private CharacterSequenceGenerator generator;

    @Before
    public void setUp() throws Exception {
        generator = new CharacterSequenceGenerator.CharacterSequenceGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .usePunctuation(false)
                .build();
    }

    @Test
    public void generate_zeroLength_returnsEmptyString() {
        String expected = "";
        String actual = generator.generate(0);
        assertEquals(expected, actual);
    }

    @Test
    public void generate_negativeLength_returnsEmptyString() {
        String expected = "";
        String actual = generator.generate(-1);
        assertEquals(expected, actual);
    }

    @Test
    public void generate_fixedPositiveLength_returnsResultOfExactLength() {
        int expected = 10;
        int actual = generator.generate(10).length();
        assertEquals(expected, actual);
    }

    @Test
    public void generate_buildGeneratorWithoutPunctuationCharacterCategory_returnsStringWithoutPunctuationSymbols(){
        long expected = 0;
        long actual = generator.generate(100)
                .chars()
                .filter(x->"!@#$%&*()_+-=[]|,./?><".contains(String.valueOf((char)x)))
                .count();
        assertEquals(expected, actual);
    }

    @Test(timeout = 100)
    public void generate_bigLength_runsUnder100Milisqconds(){
        final int BIG_LENGTH = 1000;
        generator.generate(BIG_LENGTH);
    }
}