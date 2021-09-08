package DAO;

import DBO.TestUser;

public class CustomerDAO extends AbstractDAO<TestUser>{
    public CustomerDAO() {
        setClazz(TestUser.class);
    }
}
