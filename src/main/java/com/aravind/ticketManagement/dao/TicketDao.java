package com.aravind.ticketManagement.dao;

import com.aravind.ticketManagement.model.Ticket;

import java.util.List;

public interface TicketDao  {
    public List<Ticket> getAllTickets();
    public Ticket findTicketById(int id);
    public int saveTicket(Ticket ticket);
    public void deleteTicket(int id);
}
