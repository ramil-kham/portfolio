package db;

import DBO.UserMongo;
import com.mongodb.client.model.Filters;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MongoDBTests {
    private static UserMongoDBClient client;

    @BeforeAll
    public static void setUpDbConnection() {
        client = new UserMongoDBClient();
    }

    @AfterAll
    public static void disconnect() {
//        client.deleteAllUsers();
        client.closeConnection();
    }

    @Test
    void getAllUsersTest() {
        List<UserMongo> usersForCreating = Arrays.asList(new UserMongo(), new UserMongo());
        client.createUsers(usersForCreating);
        List<UserMongo> usersAfterCreating = client.findAllUsers();
        Assertions.assertArrayEquals(usersForCreating.toArray(), usersAfterCreating.toArray());
        System.out.println(client.findAllUsers());
    }

    @Test
    void addNewUserTest() {
        UserMongo userDB = new UserMongo();
        client.createUser(userDB);
        UserMongo user = client.findUserByFilter(Filters.eq("name", userDB.getName()));
        Assertions.assertEquals(userDB.getName(), user.getName());
        System.out.println(user);
        System.out.println(userDB);
    }

    @Test
    void findUserByIdTest() {
        UserMongo user = new UserMongo();
        client.createUser(user);
        UserMongo userAfterCreate = client.findUserById(user.getId().getOid());
        Assertions.assertEquals(userAfterCreate, user);
    }

    @Test
    void updateUserTest() {
        UserMongo userForCreating = new UserMongo();
        UserMongo userForUpdating = new UserMongo();
        client.createUser(userForCreating);
        client.updateUser(Filters.eq("_id", userForCreating.getId().getOid()), userForUpdating);
        Assertions.assertEquals(client.findUserById(userForCreating.getId().getOid()), userForUpdating);
    }

    @Test
    void deleteUserTest() {
        UserMongo userForCreating = new UserMongo();
        client.createUser(userForCreating);
        client.deleteUser(userForCreating);
        Assertions.assertNull(client.findUserById(userForCreating.getId().getOid()));
    }
}
