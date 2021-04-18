package com.aravind.ticketManagement.util;

import com.aravind.ticketManagement.model.Ticket;
import com.aravind.ticketManagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Scheduler {

    @Autowired
    TicketService tktService;

    @Scheduled(cron = "0 * 13 * * ?")
    public void closeResolvedTickets() {
        System.out.println("Scheduler running to close resolved tickets 30 days ago");
        List<Ticket> tickets= tktService.getAllTickets();
        for(Ticket ticket:tickets){
            if(Ticket.Status.RESOLVED.equals(ticket.getStatus()) && ticket.getResolvedAt()!=null){
                long days= TimeUnit
                        .MILLISECONDS
                        .toDays(new Date().getTime()-ticket.getResolvedAt().getTime())
                        % 365;
                if(days>30) {
                    ticket.setStatus(Ticket.Status.CLOSED);
                    tktService.saveTicket(ticket);
                }
            }
        }
    }

}
