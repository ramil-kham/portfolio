package client;

import DAO.CustomerDAO;
import DBO.TestUser;

import java.util.List;

public class CustomerDBClient {
    private CustomerDAO customerDAO;

    public CustomerDBClient() {
        this.customerDAO = new CustomerDAO();
    }

    public void saveCustomer(TestUser customer) {
        customerDAO.save(customer);
    }

    public TestUser findCustomerById(int id) {
        return customerDAO.findById(id);
    }

    public List<TestUser> findAllCustomers() {
        return customerDAO.findAll();
    }

    public void updateCustomer(TestUser customer) {
        customerDAO.update(customer);
    }

    public int findIndexCustomer() {
        return customerDAO.findIndex();
    }

    public void deleteCustomer(TestUser customer) {
        customerDAO.delete(customer);
    }

    public void deleteAllCustomers() {
        customerDAO.deleteAll();
    }
}
