package com.aravind.ticketManagement.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Agent.tableName)
@Data
public class Agent {

    public static final String tableName = "agent";

    @Id
    @Column(name="id")
    private Integer id;

  /*  @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<Ticket> tickets;*/

    @Column(name="name")
    private String name;
}
