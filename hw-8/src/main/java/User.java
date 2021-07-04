import org.apache.commons.lang3.RandomStringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class User {
    private FullName name;
    private Date birthDay;
    private LocalDateTime registrationDate;
    private String login;
    private String password;

    public User(FullName name, LocalDateTime registrationDate, String login, String password) {

        this.name = name;
        this.birthDay = new Date();
        this.registrationDate = registrationDate;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", registrationDate=" + registrationDate +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public class FullName {
        private String firstName;
        private String lastName;

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

        private String middleName;

        public FullName(String lastName, String firstName, String middleName) {
            this.firstName = firstName = "";
            this.lastName = lastName = "";
            this.middleName = middleName = "";
        }
    }


    private Date generateRandomDate()  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  new Date();
    }
    private FullName generateRandomName() {
        String firstName = "";
        String lastName = "";
        String middleName = "";
        return  new FullName(lastName, firstName, middleName);
    }
    private String generateRandomName(FullName fullName) {
        return fullName.getLastName() + " " + fullName.getFirstName() + "." + fullName.getMiddleName();
    }
    private LocalDateTime generateRandomRegistrationDate() {
        return LocalDateTime.now();
    }
    private String generateRandomLogin(int count) {
        return generateRandomAlphaNumeric(count);
    }

    private String generateRandomAlphaNumeric(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    private String generateRandomPassword(int count) {
        return generateRandomAlphaNumeric(count);
    }
    public User()  {
        this.name = generateRandomName();
        this.birthDay = generateRandomDate();
        this.registrationDate = generateRandomRegistrationDate();
        this.login = generateRandomLogin(6);
        this.password = generateRandomPassword(10);
    }
}
