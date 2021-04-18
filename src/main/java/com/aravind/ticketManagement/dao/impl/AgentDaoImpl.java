package com.aravind.ticketManagement.dao.impl;

import com.aravind.ticketManagement.dao.AgentDao;
import com.aravind.ticketManagement.dao.CustomerDao;
import com.aravind.ticketManagement.model.Agent;
import com.aravind.ticketManagement.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AgentDaoImpl implements AgentDao {

    private EntityManager entityManager;

    @Autowired
    public AgentDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public List<Agent> getAllAgents() {
        Query theQuery= entityManager.createQuery("from Agent");
        List<Agent> agents = theQuery.getResultList();
        return agents;
    }

    @Override
    public Agent findAgentById(int id) {
        Agent agent= entityManager.find(Agent.class,id);
        return agent;
    }

    @Override
    public int saveAgent(Agent agent) {
        return 0;
    }

    @Override
    public void deleteAgent(int id) {

    }
}
