package com.aravind.ticketManagement.dao;

import com.aravind.ticketManagement.model.Customer;

import java.util.List;

public interface CustomerDao {
    public List<Customer> getAllCustomers();
    public Customer findCustomerById(int id);
    public int saveCustomer(Customer customer);
    public void deleteCustomer(int id);
}
