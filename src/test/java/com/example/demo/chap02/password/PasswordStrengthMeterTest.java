package com.example.demo.chap02.password;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    @Test
    void 모든_조건을_충족하면_암호_강도는_강함() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("!asjdASD123");
        assertEquals(PasswordStrength.STRONG, result);
    }

    @Test
    void 길이만_8자_미만이고_나머지를_충족하면_암호_강도는_보통() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ASD123");
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    void 숫자를_포함하지_않고_나머지를_충족하면_암호_강도는_보통() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ASDalksjdlwjd");
        assertEquals(PasswordStrength.NORMAL, result);
    }
}
