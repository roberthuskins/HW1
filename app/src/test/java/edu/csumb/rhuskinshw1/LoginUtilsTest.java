package edu.csumb.rhuskinshw1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LoginUtilsTest {

    @Test
    public void testCorrectPasswordReturnTrue() {
        assertTrue(LoginUtils.verifyPassword("password3", 3));
        assertTrue(LoginUtils.verifyPassword("password1", 1));
        assertTrue(LoginUtils.verifyPassword("password10", 10));
    }

    @Test
    public void testInvalidPasswordReturnFalse() {
        assertFalse(LoginUtils.verifyPassword("password3", 1));
        assertFalse(LoginUtils.verifyPassword("password1", 3));
        assertFalse(LoginUtils.verifyPassword("password10", 5));
    }

}
