package com.aravind.ticketManagement.controller;

import com.aravind.ticketManagement.model.Ticket;
import com.aravind.ticketManagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/ticketManagement")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService tktService){
        ticketService=tktService;
    }

    @RequestMapping(value = "/tickets", method= RequestMethod.GET)
    public ResponseEntity<List<Ticket>> findAll() {
        System.out.println(ticketService.getAllTickets().size());
        return new ResponseEntity<List<Ticket>>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets/{id}", method= RequestMethod.GET)
    public Ticket findTicketById(@PathVariable int id) {
        return ticketService.findTicketById(id);
    }

    @RequestMapping(value = "/tickets/agent/{agentId}", method= RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getTicketsByAgent(@PathVariable int agentId){
        List<Ticket> ticketsByAgent =ticketService.getAllTickets()
                .stream()
                .filter(x-> x.getAssignedAgentId()==agentId)
                .collect(Collectors.toList());
        return new ResponseEntity<List<Ticket>>(ticketsByAgent,HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets/customer/{customerId}", method= RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getTicketsByCustomer(@PathVariable int customerId){
        List<Ticket> ticketsByCustomer =ticketService.getAllTickets()
                .stream()
                .filter(x-> x.getCustomerId()==customerId)
                .collect(Collectors.toList());
        return new ResponseEntity<List<Ticket>>(ticketsByCustomer,HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets/status/{status}", method= RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getTicketsByStatus(@PathVariable String status){
        List<Ticket> ticketsByStatus =ticketService.getAllTickets()
                .stream()
                .filter(x-> status.equals(x.getStatus()))
                .collect(Collectors.toList());
        return new ResponseEntity<List<Ticket>>(ticketsByStatus,HttpStatus.OK);
    }

    @RequestMapping(value = "/ticket", method= RequestMethod.PUT)
    public int createOrUpdateTicket(@RequestBody Ticket ticket){
        return (ticketService.saveTicket(ticket));
    }

    @RequestMapping(value = "/ticket/{ticketId}/updateStatus/{status}", method= RequestMethod.PUT)
    public int updateStatus(@PathVariable int ticketId, Ticket.Status status){
        Ticket ticket=ticketService.findTicketById(ticketId);
        ticket.setStatus(status);
        return (ticketService.saveTicket(ticket));
    }

    @RequestMapping(value = "/ticket/{ticketId}/addResponse", method= RequestMethod.PUT)
    public void AddResponse(@PathVariable int ticketId,@RequestBody String response){
        ticketService.addResponse(ticketId,response);
    }


}
