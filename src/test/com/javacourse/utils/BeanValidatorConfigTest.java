package com.javacourse.utils;

import com.javacourse.test.topic.Topic;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BeanValidatorConfigTest {

    private BeanValidatorConfig<Topic> validator;


    @Before
    public void setUp() throws Exception {
        validator = new BeanValidatorConfig<>("en");
    }

    @Test
    public void isValid_invalidModel_returnsFalse() {
        assertFalse(validator.isValid(new Topic(1, "")));
    }

    @Test
    public void isValid_validModel_returnsTrue() {
        assertTrue(validator.isValid(new Topic(1, "123")));
    }

    @Test
    public void getErrorMessage() {
        validator.isValid(new Topic(1, ""));
        assertNotNull(validator.getErrorMessage());
    }
}