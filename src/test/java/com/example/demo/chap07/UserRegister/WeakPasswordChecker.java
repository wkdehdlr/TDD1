package com.example.demo.chap07.UserRegister;

public interface WeakPasswordChecker {
    boolean checkPasswordWeak(String pw);
    void setWeak(boolean weak);
}
