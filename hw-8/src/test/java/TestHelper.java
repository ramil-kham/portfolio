import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestHelper {
    User user = new User();
    User userIncorrect = new User("Pavel457", "1987-10-10", "2021-07-5T15:05:10", "Pavel", "pasha");
/*
    @Test
    public void passwordTest() {
        Assertions.assertTrue(TestUser.userPassword(userIncorrect.getPassword()));
    }

    @Test
    public void loginTest() {
        Assertions.assertTrue(TestUser.userLogin(userIncorrect.getLogin()));
    }

    @Test
    public void fullNameTest() {
        Assertions.assertTrue(TestUser.userPassword(userIncorrect.getFullName()));
    }

    @Test
    public void birthdayTest() {
        Assertions.assertTrue(TestUser.userPassword(userIncorrect.getBirthDay()));
    }*/
}
