package com.aravind.ticketManagement.dao.impl;

import com.aravind.ticketManagement.dao.CustomerDao;
import com.aravind.ticketManagement.dao.TicketDao;
import com.aravind.ticketManagement.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

    private EntityManager entityManager;

    @Autowired
    public TicketDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public List<Ticket> getAllTickets() {
        Query theQuery= entityManager.createQuery("from Ticket");
        List<Ticket> tickets = theQuery.getResultList();
        return tickets;
    }

    @Override
    public Ticket findTicketById(int id) {
        Ticket ticket= entityManager.find(Ticket.class,id);
        return ticket;
    }

    @Override
    public int saveTicket(Ticket ticket) {
        System.out.print(ticket);
        Ticket s=entityManager.merge(ticket);
        ticket.setId(s.getId());
        return ticket.getId();
    }

    @Override
    public void deleteTicket(int id) {
        Query delQry = entityManager.createQuery("delete from "+Ticket.tableName+" where id=:ticketId");
        delQry.setParameter("ticketId", id);
        delQry.executeUpdate();
    }
}
