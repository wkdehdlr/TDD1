package com.example.demo.chap07.UserRegister;


public class UserRegister {

    private WeakPasswordChecker passwordChecker;
    private MemoryUserRepository repository;
    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker, MemoryUserRepository repository, EmailNotifier emailNotifier) {
        this.passwordChecker = passwordChecker;
        this.repository = repository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }

        if (repository.existsById(id)) {
            throw new DupIdException();
        }

        repository.save(new User(id, pw, email));

        emailNotifier.sendRegisterEmail(email);
    }
}
