import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    private String fullName;
    private String birthDay;
    private String registrationDate;
    private String login;
    private String password;

    public User(String fullName, String birthDay, String registrationDate, String login, String password) {

        this.fullName = fullName;
        this.birthDay = birthDay;
        this.registrationDate = registrationDate;
        this.login = login;
        this.password = password;
    }

    public User()  {
        this.fullName = generateRandomName();
        this.birthDay = generateRandomDate();
        this.registrationDate = generateRandomRegistrationDate();
        this.login = generateRandomLogin(6);
        this.password = generateRandomPassword(6);
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" + fullName + ", birthday: " + birthDay + ", registration date: " + registrationDate +
                ", login: " + login + ", password: " + password;
    }

    public static class FullName {
        private static String firstName;
        private static String lastName;
        private static String middleName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public FullName() {
            this.firstName = generatedRandomFirstName();
            this.lastName = generatedRandomLastName();
            this.middleName = generatedRandomMiddleName();
        }
        public static String generatedRandomLastName() {
            String[] lastname = {"Ivanov", "Petrov", "Makarov", "Andreev", "Sidorov"};
            return lastname[new Random().nextInt(lastname.length)];
        }
        public static String generatedRandomFirstName() {
            String[] firstname = {"Ivan", "Alexandr", "Sergey", "Andrey", "Maxim"};
            return firstname[new Random().nextInt(firstname.length)];
        }
        public static String generatedRandomMiddleName() {
            String[] middlename = {"Ivanovich", "Petrovich", "Makarovich", "Andreevich", "Vladimirovich"};
            return middlename[new Random().nextInt(middlename.length)];
        }

        @Override
        public String toString() {
            return lastName + " " + firstName + " " + middleName;
        }
        public String getFIO() {
            return lastName + " " + firstName.charAt(0) + "." + middleName.charAt(0) + ".";
        }
    }
    private String generateRandomName() {
        return FullName.generatedRandomLastName() + " " + FullName.generatedRandomFirstName() + " " + FullName.generatedRandomMiddleName();
    }

    private String generateRandomDate()  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        RandomDataGenerator dataGenerator = new RandomDataGenerator();
        return sdf.format(new Date(dataGenerator.nextLong(1000, new Date().getTime())));
    }
    private String generateRandomRegistrationDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }
    private String generateRandomLogin(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    private String generateRandomPassword(int count) {
        String[] randomSymbol = {"!","@","#","&","(",")","–","[","{","}","]",":",";","'",",","?","/","*","~","$","^","+",
            "=","<",">"};
        return RandomStringUtils.randomAlphanumeric(count) + randomSymbol[new Random().nextInt(randomSymbol.length)];
    }
}
