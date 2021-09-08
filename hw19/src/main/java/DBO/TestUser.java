package DBO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class TestUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    @Column(name = "name")
    private String name;
    //    @Column(name = "date")
    private String date;
    //    @Column(name = "city")
    private String city;
    //    @Column(name = "phone")
    private String phone;

    public TestUser(int id, String name, String date, String city, String phone) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.city = city;
        this.phone = phone;
    }

    public TestUser(String name, String date, String city, String phone) {
        this.name = name;
        this.date = date;
        this.city = city;
        this.phone = phone;
    }

    public TestUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestUser customer = (TestUser) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(date, customer.date) && Objects.equals(city, customer.city) && Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, city, phone);
    }
}
