package com.javacourse.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for generating random string of any length
 */
public class CharacterSequenceGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    private static final int NUMBER_OF_CHAR_CATEGORIES = 4;

    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;
    private boolean usePunctuation;

    public CharacterSequenceGenerator(CharacterSequenceGeneratorBuilder builder) {
        this.useLower = builder.useLower;
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
        this.usePunctuation = builder.usePunctuation;
    }

    public static class CharacterSequenceGeneratorBuilder {
        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;
        private boolean usePunctuation;

        public CharacterSequenceGeneratorBuilder() {
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
            this.usePunctuation = false;
        }

        public CharacterSequenceGeneratorBuilder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        public CharacterSequenceGeneratorBuilder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        public CharacterSequenceGeneratorBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        public CharacterSequenceGeneratorBuilder usePunctuation(boolean usePunctuation) {
            this.usePunctuation = usePunctuation;
            return this;
        }

        public CharacterSequenceGenerator build() {
            return new CharacterSequenceGenerator(this);
        }

    }

    public String generate(int length){

        if(length<=0){
            return "";
        }

        StringBuilder password = new StringBuilder(length);
        Random random = new Random();

        List<String> charCategories = new ArrayList<>(NUMBER_OF_CHAR_CATEGORIES);
        if(useLower){
            charCategories.add(LOWER);
        }
        if(useUpper){
            charCategories.add(UPPER);
        }
        if(useDigits){
            charCategories.add(DIGITS);
        }
        if (usePunctuation) {
            charCategories.add(PUNCTUATION);
        }

        String charCategory;
        for(int i=0; i<length; ++i){
            charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return new String(password);
    }

}
