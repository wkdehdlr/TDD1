package com.example.demo.chap07.UserRegister;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {

    private UserRegister userRegister;
    private WeakPasswordChecker passwordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository repository = new FakeMemoryUserRepository();
    private EmailNotifier emailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(passwordChecker, repository, emailNotifier);
    }

    @Test
    void weakPassword() {
        passwordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @Test
    void 아이디_중복가입_불가능() {
        repository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, () -> {
            userRegister.register("id","pw2","email");
        });
    }

    @Test
    void 아이디_중복없으면_회원가입_성공() {
        userRegister.register("id1","pw1","email1@email.com");

        assertDoesNotThrow(() -> userRegister.register("id2","pw2","email2@email.com"));
    }

    @Test
    void 가입에_성공하면_이메일을_발송함() {
        userRegister.register("id1","pw1","email1@email.com");

        assertTrue(emailNotifier.isCalled());
        assertEquals("email1@email.com", emailNotifier.getEmail());
    }
}
