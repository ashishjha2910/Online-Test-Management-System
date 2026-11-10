package com.onlinetest;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

class PasswordEncodingTest {
    @Test
    void bcryptEncodesAndVerifiesPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String raw = "SecureTest@123";
        String encoded = encoder.encode(raw);

        assertNotEquals(raw, encoded);
        assertTrue(encoder.matches(raw, encoded));
        assertFalse(encoder.matches("incorrect", encoded));
    }
}
