package com.eventmanagement.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
