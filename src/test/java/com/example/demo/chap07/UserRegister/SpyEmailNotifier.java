package com.example.demo.chap07.UserRegister;

public class SpyEmailNotifier implements EmailNotifier {
    private boolean called = false;
    private String email;

    @Override
    public boolean isCalled() {
        return called;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void sendRegisterEmail(String email) {
        this.email = email;
        this.called = true;
    }
}
