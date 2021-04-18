package com.aravind.ticketManagement.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Ticket.tableName)
@Data
public class Ticket {

    public static final String tableName = "ticket";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name = "created_agent_id", columnDefinition = "int default 0")
    private int createdAgentId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "created_agent_id",insertable = false, updatable = false)
    private Agent createdAgent;

    @Column(name = "assigned_agent_id", columnDefinition = "int default 0")
    private int assignedAgentId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "assigned_agent_id",insertable = false, updatable = false)
    private Agent assignedAgent;

    @Column(name = "customer_id", columnDefinition = "int default 0")
    private int customerId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer_id",insertable = false, updatable = false)
    private Customer customer;

    @Column(name="status")
    private Status status;

    @Column(name="ticketType")
    private String type;

    @Column(name="description")
    private String description;

    @Column(name="title")
    private String title;

    @Column(name="priority")
    private String priority;

    @Column(name="response")
    private String response;

    @Column(name="createdAt")
    @CreatedDate
    private Date createdAt;

    @Column(name="resolvedAt")
    private Date resolvedAt;

    public enum Status {
        OPEN, CUSTOMER_PENDING, CUSTOMER_RESPONDED, RESOLVED, CLOSED;
    }

}
