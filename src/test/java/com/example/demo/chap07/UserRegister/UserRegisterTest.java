package com.example.demo.chap07.UserRegister;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class UserRegisterTest {

    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository repository = new FakeMemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, repository, mockEmailNotifier);
    }

    @Test
    void 약한_암호면_가입_실패() {
        given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });

        then(mockPasswordChecker)
            .should()
            .checkPasswordWeak(BDDMockito.anyString());
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
        given(mockEmailNotifier.isCalled()).willReturn(true);
        given(mockEmailNotifier.getEmail()).willReturn("email1@email.com");

        userRegister.register("id1","pw1","email1@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
            .should()
            .sendRegisterEmail(captor.capture());

        assertEquals("email1@email.com", captor.getValue());
    }
}
