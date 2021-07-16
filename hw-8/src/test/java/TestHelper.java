import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestHelper {

    public static boolean userPassword(final User user)  {
      return user.getPassword().matches("(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
   }

   public static boolean userLogin(final User user) {
      return user.getLogin().matches("^[a-zA-Z]{4,6}+$");
   }
   public static boolean userFullName(final User user) {
       return user.getFullName().matches("^([A-Z]|[A-Z][\\x27a-z]{1,}|[A-Z][\\x27a-z]{1,}\\-([A-Z][\\x27a-z]{1,}|))\\040[A-Z][\\x27a-z]{1,}(\\040[A-Z][\\x27a-z]{1,})?$");
   }
   public static boolean userBirthDay(final User user) {
      return user.getBirthDay().matches("(19|20)\\d\\d-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)");
    }

}
