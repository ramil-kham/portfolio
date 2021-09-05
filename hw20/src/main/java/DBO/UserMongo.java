package DBO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Objects;

public class UserMongo implements Serializable {
    @JsonProperty("_id")
    private Id id;
    private String name;
    private String dateOfBirth;
    private String city;
    private String phoneNumber;

    public UserMongo(Id id, String name, String dateOfBirth, String city, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public UserMongo(String name, String dateOfBirth, String city, String phoneNumber) {
        this.id = new Id();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public UserMongo() {
        this.id = new Id();
        this.name = RandomStringUtils.randomAlphabetic(5);;
        this.dateOfBirth = RandomStringUtils.randomNumeric(2) + "." + RandomStringUtils.randomNumeric(2) + "." +  RandomStringUtils.randomNumeric(4);
        this.city = RandomStringUtils.randomAlphabetic(5);;
        this.phoneNumber = "+79" + RandomStringUtils.randomNumeric(9);
    }

    public class Id {
        @JsonProperty("$oid")
        private String oid;

        public Id(String oid) {
            this.oid = oid;
        }

        public Id() {
            this.oid = new ObjectId().toString();
        }

        public String getOid() {
            return oid;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "oid='" + oid + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(oid, id.oid);
        }

        @Override
        public int hashCode() {
            return Objects.hash(oid);
        }
    }

    public Id getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "UserMongo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMongo userMongo = (UserMongo) o;
        return Objects.equals(id, userMongo.id) && Objects.equals(name, userMongo.name) && Objects.equals(dateOfBirth, userMongo.dateOfBirth) && Objects.equals(city, userMongo.city) && Objects.equals(phoneNumber, userMongo.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, city, phoneNumber);
    }
}
