package DAO;

import DBO.User;

public class UserDAO extends AbstractDAO<User>{
    public UserDAO() {
        setClazz(User.class);
    }
}
