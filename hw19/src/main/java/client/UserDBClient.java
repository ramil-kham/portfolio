package client;

import DAO.UserDAO;
import DBO.User;

import java.util.List;

public class UserDBClient {
    private UserDAO userDAO;

    public UserDBClient() {
        this.userDAO = new UserDAO();
    }

    public void saveUser(User user) {
        userDAO.save(user);
    }

    public User findUserById(int id) {
        return userDAO.findById(id);
    }

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    public void deleteAllUsers() {
        userDAO.deleteAll();
    }

}
