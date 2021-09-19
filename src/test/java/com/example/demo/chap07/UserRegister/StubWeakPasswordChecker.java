package com.example.demo.chap07.UserRegister;

public class StubWeakPasswordChecker implements WeakPasswordChecker {

    private boolean weak = false;

    @Override
    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPasswordWeak(String pw) {
        return weak;
    }
}
