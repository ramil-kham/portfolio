import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class User implements Serializable {
    private String fullName;
    private String birthDay;
    private String registrationDate;
    private String login;
    private String password;
    private String address;

    public User(String fullName, String birthDay, String registrationDate, String login, String password, String address) {

        this.fullName = fullName;
        this.birthDay = birthDay;
        this.registrationDate = registrationDate;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public User()  {
        this.fullName = generateRandomName();
        this.birthDay = generateRandomDate();
        this.registrationDate = generateRandomRegistrationDate();
        this.login = generateRandomLogin(6);
        this.password = generateRandomPassword(10);
        this.address = generatedRandomAddress();
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

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" + fullName + ", birthday: " + birthDay + ", registration date: " + registrationDate +
                ", login: " + login + ", password: " + password + ", address: " + address;
    }

    public static class FullName implements Serializable{
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

    public static class Address implements Serializable {
        private static String city;
        private static String street;
        private static String house;
        private static int numberOfFlat;

        @Override
        public String toString() {
            return "Address: " + city + ", " + street + ", " + house + ", " + numberOfFlat;
        }
        public static String getCity() {
            return city;
        }
        public static void setCity(String city) {
            Address.city = city;
        }
        public static String getStreet() {
            return street;
        }
        public static void setStreet(String street) {
            Address.street = street;
        }
        public static String getHouse() {
            return house;
        }
        public static void setHouse(String house) {
            Address.house = house;
        }
        public static int getNumberOfFlat() {
            return numberOfFlat;
        }
        public static void setNumberOfFlat(int numberOfFlat) {
            Address.numberOfFlat = numberOfFlat;
        }
        public static String generatedRandomCity() {
            String[] city = {"Kazan", "Ufa", "Moscow", "Chelyabinsk", "Vladivostok"};
            return city[new Random().nextInt(city.length)];
        }
        public static String generatedRandomStreet() {
            String[] street = {"Pushkina", "Lenina", "Ostrovskogo", "Petrova", "Ivanova"};
            return street[new Random().nextInt(street.length)];
        }
        public static String generatedRandomHouse() {
            String[] house = {"1A", "10", "14", "27B", "54"};
            return house[new Random().nextInt(house.length)];
        }
        public static int generatedRandomNumberOfFlat() {
            int[] numberOfFlat = {10,24,37,41,62};
            return numberOfFlat[new Random().nextInt(numberOfFlat.length)];
        }
    }

    private String generatedRandomAddress() {
        return "city: " + Address.generatedRandomCity() + ", street: " + Address.generatedRandomStreet() +
                ", house: " + Address.generatedRandomHouse() + ", number of flat: " + Address.generatedRandomNumberOfFlat();
    }

    private String generateRandomName() {
        return FullName.generatedRandomLastName() + " " + FullName.generatedRandomFirstName() + "." +
                FullName.generatedRandomMiddleName();
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
        return generateRandomAlphaNumeric(count);
    }

    private String generateRandomAlphaNumeric(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    private String generateRandomPassword(int count) {
        return generateRandomAlphaNumeric(count);
    }
}
