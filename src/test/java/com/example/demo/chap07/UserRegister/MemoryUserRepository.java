package com.example.demo.chap07.UserRegister;

public interface MemoryUserRepository {

    void save(User user);
    boolean existsById(String id);
}
