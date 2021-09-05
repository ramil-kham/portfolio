package DAO;

import DBO.UserMongo;
import utils.PropertyLoader;

import static utils.MongoDBConnectionFactory.createMongoClient;

public class UserMongoDAO extends AbstractMongoDAO<UserMongo>{
    public UserMongoDAO() {
        setClient(createMongoClient(PropertyLoader.getProperty("mongodbUrl")));
        setClazz(UserMongo.class);
        setCollectionName(PropertyLoader.getProperty("collection"));
        setDbName(PropertyLoader.getProperty("mongodb"));
        setMapper();
    }
}
