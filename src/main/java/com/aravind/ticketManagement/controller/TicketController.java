package com.aravind.ticketManagement.controller;

import com.aravind.ticketManagement.model.Ticket;
import com.aravind.ticketManagement.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value="/ticketManagement",produces ="application/json")
@RequestMapping(path="/ticketManagement")
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService tktService){
        ticketService=tktService;
    }

    @ApiOperation(value="get All Tickets",response=Ticket[].class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="All Tickets Details Retrieved",response=Ticket[].class),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    @RequestMapping(value = "/tickets", method= RequestMethod.GET)
    public ResponseEntity<List<Ticket>> findAll() {
        System.out.println(ticketService.getAllTickets().size());
        return new ResponseEntity<List<Ticket>>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    @ApiOperation(value="get Tickets By Id",response=Ticket.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="specific Ticket Details",response=Ticket.class),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    @RequestMapping(value = "/tickets/{id}", method= RequestMethod.GET)
    public Ticket findTicketById(@PathVariable int id) {
        return ticketService.findTicketById(id);
    }

    @ApiOperation(value="get Tickets By AgentId",response=Ticket[].class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="AgentFiltered Tickets Details",response=Ticket[].class),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    @RequestMapping(value = "/tickets/agent/{agentId}", method= RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getTicketsByAgent(@PathVariable int agentId){
        List<Ticket> ticketsByAgent =ticketService.getAllTickets()
                .stream()
                .filter(x-> x.getAssignedAgentId()==agentId)
                .collect(Collectors.toList());
        return new ResponseEntity<List<Ticket>>(ticketsByAgent,HttpStatus.OK);
    }

    @ApiOperation(value="get Tickets By CustomerId",response=Ticket[].class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="CustomerFiltered Tickets Details",response=Ticket[].class),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    @RequestMapping(value = "/tickets/customer/{customerId}", method= RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getTicketsByCustomer(@PathVariable int customerId){
        List<Ticket> ticketsByCustomer =ticketService.getAllTickets()
                .stream()
                .filter(x-> x.getCustomerId()==customerId)
                .collect(Collectors.toList());
        return new ResponseEntity<List<Ticket>>(ticketsByCustomer,HttpStatus.OK);
    }

    @ApiOperation(value="get Tickets By Status",response=Ticket[].class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Status Filtered Tickets Details",response=Ticket[].class),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    @RequestMapping(value = "/tickets/status/{status}", method= RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getTicketsByStatus(@PathVariable String status){
        List<Ticket> ticketsByStatus =ticketService.getAllTickets()
                .stream()
                .filter(x-> status.equals(x.getStatus()))
                .collect(Collectors.toList());
        return new ResponseEntity<List<Ticket>>(ticketsByStatus,HttpStatus.OK);
    }

    @ApiOperation(value="Create/Update Ticket details",response=String.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Ticket Saved Successfully",response=String.class),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    @RequestMapping(value = "/ticket", method= RequestMethod.PUT)
    public int createOrUpdateTicket(@RequestBody Ticket ticket){
        return (ticketService.saveTicket(ticket));
    }

    @ApiOperation(value="Update Status of a Ticket",response=String.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Ticket Status Updated Successfully",response=String.class),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    @RequestMapping(value = "/ticket/{ticketId}/updateStatus/{status}", method= RequestMethod.PUT)
    public int updateStatus(@PathVariable int ticketId, Ticket.Status status){
        Ticket ticket=ticketService.findTicketById(ticketId);
        ticket.setStatus(status);
        return (ticketService.saveTicket(ticket));
    }

    @ApiOperation(value="Add Response to a Ticket",response=String.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Ticket Responded Successfully",response=String.class),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    @RequestMapping(value = "/ticket/{ticketId}/addResponse", method= RequestMethod.PUT)
    public void AddResponse(@PathVariable int ticketId,@RequestBody String response){
        ticketService.addResponse(ticketId,response);
    }


}
