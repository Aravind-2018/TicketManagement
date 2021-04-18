package com.aravind.ticketManagement.util;

import com.aravind.ticketManagement.model.Ticket;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class Mailer {

    public static Mail buildTicketEmail(String emailId, Ticket ticket) {
        Email from = new Email("yogesh@sinecycle.com");
        String subject = "Ticket Id : "+ticket.getId();
        Email to = new Email(emailId);
        Content content = new Content("text/plain", ticket.getResponse());
        Mail mail = new Mail(from, subject, to, content);

        return mail;
    }

    public static void sendmail(Mail mail) throws IOException {
        final SendGrid sg = new SendGrid("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");

        final Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        final Response response = sg.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
    }
}
