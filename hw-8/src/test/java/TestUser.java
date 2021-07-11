import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUser {
    public static void main(String[] args) {
        System.out.println(user);
        System.out.println(user1);
    }
   static User user = new User();
   static User user1 = new User("34","23", "12", "44", "56");

    public static boolean userPassword()  {
      Pattern pattern = Pattern.compile("^(\b[A-Z]\b)([!@#&()–\\[{}\\]:;',?/*~$^+=<>])([a-zA-Z]){6,}");
      Matcher matcher = pattern.matcher(user.getPassword());
      return matcher.matches();
   }

   public static boolean userLogin() {
      Pattern pattern = Pattern.compile("^[a-zA-Z]{4,6}");
      Matcher matcher = pattern.matcher(user.getLogin());
      Matcher matcher1 = pattern.matcher(user1.getLogin());
      return matcher.matches();
   }
   public static boolean userFullName() {
       Pattern pattern = Pattern.compile("^(\\D)[a-zA-Z]");
       Matcher matcher = pattern.matcher(user.getFullName());
       return matcher.matches();
   }
   public static boolean userBirthDay() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      Pattern pattern = Pattern.compile("([12][09][0-9][0-9])(0[0-9]1[0-2])(0[1-9][12][0-9]3[0-1])");
      Matcher matcher = pattern.matcher(user.getBirthDay());
      return matcher.matches();
    }

}
