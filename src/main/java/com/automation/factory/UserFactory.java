package com.automation.factory;

public final class UserFactory {
    private UserFactory() {}

    public static User newUser() {
        long stamp = System.currentTimeMillis();
        return new User(
            "Test" + stamp,
            "Customer",
            "qa.user." + stamp + "@example.com",
            "QaTest@" + (stamp % 1_000_000) + "X",
            "1998-05-14",
            "Albania",
            "1012",
            "42",
            "Test Street",
            "City",
            "State",
            "2025550147"
        );
    }

    public record User(String firstName, String lastName, String email, String password,
                       String dateOfBirth, String country, String postalCode, String houseNumber,
                       String street, String city, String state, String phone) {}
}
