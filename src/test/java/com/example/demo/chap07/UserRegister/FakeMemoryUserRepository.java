package com.example.demo.chap07.UserRegister;

import java.util.HashMap;
import java.util.Map;

public class FakeMemoryUserRepository implements MemoryUserRepository {

    private Map<String, User> map = new HashMap<>();

    @Override
    public void save(User user) {
        map.put(user.getId(), user);
    }

    @Override
    public boolean existsById(String id) {
        return map.containsKey(id);
    }
}
