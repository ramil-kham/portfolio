import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUsers {
    User generatedUser = new User();
    User userIncorrect = new User("Pavel457", "1987-10-10", "2021-07-5T15:05:10", "Pavel", "pasha");

    @Test
    public void passwordTest() {
        Assertions.assertTrue(TestHelper.userPassword(generatedUser));
//        System.out.println(generatedUser);
    }

    @Test
    public void loginTest() {
        Assertions.assertTrue(TestHelper.userLogin(generatedUser));
    }

    @Test
    public void fullNameTest() {
        Assertions.assertTrue(TestHelper.userFullName(generatedUser));
    }

    @Test
    public void birthdayTest() {
        Assertions.assertTrue(TestHelper.userBirthDay(generatedUser));
    }
}
