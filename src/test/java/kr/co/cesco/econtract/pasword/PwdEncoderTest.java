package kr.co.cesco.econtract.pasword;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PwdEncoderTest {

    private static final Logger log = LoggerFactory.getLogger(PwdEncoderTest.class);

    @Test
    void runBCryptPasswordTest() {
        int i = 0;
        while (i < 5) {
            String password = "new1234@";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            log.info("@HashedPassword====>{}",hashedPassword);
            assertTrue(passwordEncoder.matches(password, hashedPassword));
            i++;
        }
    }

//    @HashedPassword====>$2a$10$Y/UJfV1eY/84zXWSGbIuiu0I00oMDJk4ypvCDMcEn1FB7YlydbpeC
//    @HashedPassword====>$2a$10$1aXri0rCydsZt6y1u0PM/eHysVJorggeN0Zd6UxhNslmA80QDzcMq
//    @HashedPassword====>$2a$10$rt/OZ9Wg6Rqz831GJ/PLm.FCyAN0.BftCzLY7glmN5llqViuBzN0G
//    @HashedPassword====>$2a$10$wkv7Adp.x0wZKhkIX.kt3O9Fh86/Hhvh1abejtemBmistuSN2L4ie
//    @HashedPassword====>$2a$10$avYJac92hfvVf9VF.qZKXOQoDYGbp8h.vZ6SHvZ9aAaXE0PLvHQay

}
