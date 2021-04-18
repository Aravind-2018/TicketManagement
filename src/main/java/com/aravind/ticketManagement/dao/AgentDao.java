package com.aravind.ticketManagement.dao;

import com.aravind.ticketManagement.model.Agent;

import java.util.List;

public interface AgentDao {
    public List<Agent> getAllAgents();
    public Agent findAgentById(int id);
    public int saveAgent(Agent agent);
    public void deleteAgent(int id);
}
