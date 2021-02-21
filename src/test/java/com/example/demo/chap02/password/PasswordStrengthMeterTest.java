package com.example.demo.chap02.password;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    @Test
    void 모든_조건을_충족하면_암호_강도는_강함() {
        assertStrength("!asjdASD123", PasswordStrength.STRONG);
    }

    @Test
    void 길이만_8자_미만이고_나머지를_충족하면_암호_강도는_보통() {
        assertStrength("ASD123", PasswordStrength.NORMAL);
    }

    @Test
    void 숫자를_포함하지_않고_나머지를_충족하면_암호_강도는_보통() {
        assertStrength("ASDalksjdlwjd", PasswordStrength.NORMAL);
    }

    @Test
    void 비어있는_문자열이면_암호_강도는_INVALID() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void 문자열이_null이면_암호_강도는_INVALID() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void 대문자를_포함하지_않고_나머지를_충족하면_암호_강도는_보통() {
        assertStrength("asd123!@#qwe", PasswordStrength.NORMAL);
    }

    @Test
    void 길이가_8글자_이상인_조건만_충족하면_암호_강도는_약함() {
        assertStrength("lsakdjfsalkjf", PasswordStrength.WEAK);
    }

    @Test
    void 숫자_포함_조건만_충족하면_암호_강도는_약함() {
        assertStrength("123", PasswordStrength.WEAK);
    }

    @Test
    void 대문자_조건만_충족하면_암호_강도는_약함() {
        assertStrength("ASDF", PasswordStrength.WEAK);
    }

    @Test
    void 아무조건도_충족하지_않으면_암호_강도는_약함() {
        assertStrength("abc", PasswordStrength.WEAK);
    }

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

}
