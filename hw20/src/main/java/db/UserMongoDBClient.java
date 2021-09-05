package db;

import DAO.UserMongoDAO;
import DBO.UserMongo;
import com.mongodb.client.MongoClient;
import org.bson.conversions.Bson;

import java.util.List;

public class UserMongoDBClient {
    private UserMongoDAO userMongoDAO;

    public UserMongoDBClient() {
        this.userMongoDAO = new UserMongoDAO();
    }

    public UserMongo findUserById(String id) {
        return userMongoDAO.findById(id);
    }

    public UserMongo findUserByFilter(Bson filter) {
        return userMongoDAO.findByFilter(filter);
    }

    public List<UserMongo> findAllUsers() {
        return userMongoDAO.findAll();
    }

    public void createUser(UserMongo user) {
        userMongoDAO.create(user);
    }

    public void deleteUser(UserMongo user) {
        userMongoDAO.delete(user);
    }

    public void createUsers(List<UserMongo> users) {
        userMongoDAO.create(users);
    }

    public void updateUser(Bson filter, UserMongo user) {
        userMongoDAO.update(filter, user);
    }

    public void deleteAllUsers() {
        userMongoDAO.delete();
    }

    public void closeConnection() {
        MongoClient client = userMongoDAO.getClient();
        if(client!=null) client.close();
    }
}
