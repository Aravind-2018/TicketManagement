package com.aravind.ticketManagement.service;

import com.aravind.ticketManagement.model.Ticket;

import java.util.List;

public interface TicketService {
    public List<Ticket>  getAllTickets();
    public Ticket findTicketById(int id);
    public int saveTicket(Ticket ticket);
    public void deleteTicket(int id);
    public void addResponse(int id,String response);
}
