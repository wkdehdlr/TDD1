package com.example.demo.chap07.UserRegister;

public interface EmailNotifier {

    boolean isCalled();
    String getEmail();
    void sendRegisterEmail(String email);
}
