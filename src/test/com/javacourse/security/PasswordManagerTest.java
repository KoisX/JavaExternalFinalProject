package com.javacourse.security;

import com.javacourse.security.PasswordManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordManagerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void hash_generateSHA256Hex_outputLengthIs64() {
        String password = "my_strong_password123";
        String salt = "my_salt123";

        int expected = 64;
        int actual = PasswordManager.hash(password, salt).length();

        assertEquals(expected, actual);
    }

    @Test
    public void hash_emptyInput_correctOutput() {
        String password = "";
        String salt = "";

        int expected = 64;
        int actual = PasswordManager.hash(password, salt).length();

        assertEquals(expected, actual);
    }

    @Test
    public void hash_nullInput_correctOutput() {
        String password = null;
        String salt = null;

        int expected = 64;
        int actual = PasswordManager.hash(password, salt).length();

        assertEquals(expected, actual);
    }

    @Test
    public void generateRandomPassword_noParams_DefaultLength10() {
        int expected = 10;
        int actual = PasswordManager.generateRandomPassword().length();
        assertEquals(expected, actual);
    }

    @Test
    public void isExpectedPassword() {
        String password = "my_strong_password";
        String salt = "my_salt";

        String expectedHash = PasswordManager.hash(password, salt);

        assertTrue(PasswordManager.isExpectedPassword(password, salt, expectedHash));
    }

    @Test
    public void generateSalt_noParams_DefaultLength16() {
        int expected = 16;
        int actual = PasswordManager.generateSalt().length();
        assertEquals(expected, actual);
    }
}