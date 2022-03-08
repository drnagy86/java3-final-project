package com.nagy.support;

public class Ticket {
    private String customer;
    private String subject;
    private String body;

    public final String DEFAULT_STRING = "Undefined";

    public Ticket() {
        this.customer = DEFAULT_STRING;
        this.subject = DEFAULT_STRING;
        this.body = DEFAULT_STRING;
    }

    public Ticket(String customer, String subject, String body) {
        validateCustomerName(customer);
        validateSubject(subject);
        validateBody(body);
        this.customer = customer;
        this.subject = subject;
        this.body = body;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        validateCustomerName(customer);
        this.customer = customer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        validateSubject(subject);
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        validateBody(body);
        this.body = body;
    }

    private void validateCustomerName(String customerName){
        if (customerName == null || customerName == ""){
            throw new IllegalArgumentException("Name required");
        }
    }
    private void validateSubject(String subject){
        if (subject == null || subject == ""){
            throw new IllegalArgumentException("Subject required");
        }
    }

    private void validateBody(String body){
        if (body == null || body == ""){
            throw new IllegalArgumentException("Subject required");
        }
    }

}
