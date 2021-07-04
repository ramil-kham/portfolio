import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Random;

public class TestUser {
   User user = new User();


   public  int getRandomNumber(int min, int max) {
       return (int) ((Math.random() * (max - min)) + min);
   }
    public  int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max- min)+max;
    }
   @Test
    void name() throws ParseException {
       System.out.println(LocalDateTime.now());
       System.out.println(user);
   }
}
