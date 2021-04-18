package com.aravind.ticketManagement.dao.impl;

import com.aravind.ticketManagement.dao.CustomerDao;
import com.aravind.ticketManagement.dao.TicketDao;
import com.aravind.ticketManagement.model.Customer;
import com.aravind.ticketManagement.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao{

    private EntityManager entityManager;

    @Autowired
    public CustomerDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public List<Customer> getAllCustomers() {
        Query theQuery= entityManager.createQuery("from Customer");
        List<Customer> tickets = theQuery.getResultList();
        return tickets;
    }

    @Override
    public Customer findCustomerById(int id) {
        Customer customer= entityManager.find(Customer.class,id);
        return customer;
    }

    @Override
    public int saveCustomer(Customer customer) {
        return 0;
    }

    @Override
    public void deleteCustomer(int id) {

    }
}
