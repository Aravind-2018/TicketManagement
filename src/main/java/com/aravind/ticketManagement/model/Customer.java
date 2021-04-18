package com.aravind.ticketManagement.model;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Customer.tableName)
@Data
public class Customer {

    public static final String tableName = "customer";

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "emailId")
    private String emailId;

/*    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Ticket> tickets;*/
}
