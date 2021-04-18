package com.aravind.ticketManagement.service.impl;

import com.aravind.ticketManagement.dao.AgentDao;
import com.aravind.ticketManagement.dao.CustomerDao;
import com.aravind.ticketManagement.dao.TicketDao;
import com.aravind.ticketManagement.model.Customer;
import com.aravind.ticketManagement.model.Ticket;
import com.aravind.ticketManagement.service.TicketService;
import com.aravind.ticketManagement.util.Mailer;
import com.sendgrid.helpers.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    TicketDao ticketDao;
    CustomerDao customerDao;

    @Autowired
    public TicketServiceImpl(@Qualifier("ticketDaoImpl")TicketDao tktDao,
                             @Qualifier("customerDaoImpl") CustomerDao custDao){
        ticketDao = tktDao;
        customerDao = custDao;
    }

    @Override
    @Transactional
    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    @Override
    @Transactional
    public Ticket findTicketById(int id) {
        return ticketDao.findTicketById(id);
    }

    @Override
    @Transactional
    public int saveTicket(Ticket ticket) {
        return ticketDao.saveTicket(ticket);
    }

    @Override
    @Transactional
    public void deleteTicket(int id) {
        ticketDao.deleteTicket(id);
    }

    @Override
    public void addResponse(int id, String response) {
        Ticket ticket=ticketDao.findTicketById(id);
        if(ticket!=null){
            ticket.setResponse(response);
            ticketDao.saveTicket(ticket);
            Customer customer=customerDao.findCustomerById(ticket.getCustomerId());
            if(customer!=null && customer.getEmailId()!=null){
                Mail mail = Mailer.buildTicketEmail(customer.getEmailId(),ticket);
                try {
                    Mailer.sendmail(mail);
                }catch (IOException e){
                    System.out.print(e);
                }
            }
        }
    }
}
