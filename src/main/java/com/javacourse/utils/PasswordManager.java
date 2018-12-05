package com.javacourse.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * PasswordManager class simplifies performing tasks
 * connected to password managing
 */
public class PasswordManager {
    /**
     * Represents the length of a password
     * which will be generated randomly for a new user
     * if they wish so
     */
    private static int DEFAULT_PASSWORD_LENGTH = 10;

    /**
     * Standard commonly accepted length for salt
     */
    private static int DEFAULT_SALT_LENGTH = 16;

    private PasswordManager() {
        throw new UnsupportedOperationException("Object instantiation is not supported");
    }

    /**
     * Calculated the hash of the given password concatenated with the given salt.
     * SHA-256 algorithm is used.
     * @param password represents user's password
     * @param salt value which will be concatenated to password to 'salt' it
     * @return salted password hash value
     */
    public static String hash( String password, String salt){
        return DigestUtils.sha256Hex(getSaltedPassword(password, salt));
    }

    private static String getSaltedPassword(String password, String salt){
        return password + salt;
    }

    /**
     * Generates random password for a new user
     * @return new random password of a default length equal to 10
     */
    public static String generateRandomPassword(){
        CharacterSequenceGenerator generator = new CharacterSequenceGenerator
                .CharacterSequenceGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .usePunctuation(false)
                .build();

        return generator.generate(DEFAULT_PASSWORD_LENGTH);
    }

    /**
     * Generates string which can be used for 'salting'
     * user password for security magnification
     * @return salt
     */
    public static String generateSalt(){
        CharacterSequenceGenerator generator = new CharacterSequenceGenerator
                .CharacterSequenceGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .usePunctuation(true)
                .build();

        return generator.generate(DEFAULT_SALT_LENGTH);
    }

    /**
     * Checks if a given password's salted hash is equal to the hash value which we expect
     * @param password represents actual password
     * @param salt user's salt
     * @param expectedHash hash value which we expect to get
     * @return
     */
    public static boolean isExpectedPassword(String password, String salt, String expectedHash){
        String hash = hash(password, salt);
        return expectedHash.equals(hash);
    }

}
