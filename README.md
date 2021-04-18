# Ticket Management
## Manage and track the support tickets

TicketManage is an Utility API service collection for tracking and managing tickets.
## Features

- Create/View/Update/Delete Tickets
- Filter view by Agent/Customer/Status
- Send Mail response to Customer
- AutoClose the resolved tickets after **30** days 

## Tech

TicketManagement uses a number of projects to work properly:

- [SpringBoot] - Java Framwork
- [Hibernate] - ORM Framework
- [MySQL] - Database to store tickets
- [Twilio - Sendgrid] - Mail Utility Provider
- [Lombok] - To reduce boiler plate Code(Compile time)

## Installation

This requires Java, Mysql, Maven to run.

Run the following command to generate WAR inside project home path
 - mvn package
 - java -jar target/ticketManagement-0.0.1-SNAPSHOT.war

**Free Software, Hell Yeah!**

   [SpringBoot]: <https://github.com/spring-projects/spring-boot>
   [Hibernate]: <https://github.com/hibernate>
   [MySQL]: <https://github.com/mysql>
   [Twilio - Sendgrid]: <https://sendgrid.com/docs/api-reference/>
   [Lombok]: <https://projectlombok.org/>
 
