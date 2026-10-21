package com.automation.pages;

import com.automation.factory.UserFactory.User;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RegisterPage extends BasePage {
    public RegisterPage(Page page) { super(page); }

    public void open() {
        page.navigate("/auth/register");
    }

    public void register(User user) {
        page.getByTestId("first-name").fill(user.firstName());
        page.getByTestId("last-name").fill(user.lastName());
        page.getByTestId("dob").fill(user.dateOfBirth());
        page.getByTestId("country").selectOption(user.country());
        page.getByTestId("postal_code").fill(user.postalCode());
        page.getByTestId("house_number").fill(user.houseNumber());
        page.getByTestId("street").fill(user.street());
        page.getByTestId("city").fill(user.city());
        page.getByTestId("state").fill(user.state());
        page.getByTestId("phone").fill(user.phone());
        page.getByTestId("email").fill(user.email());
        page.getByTestId("password").fill(user.password());

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click();
    }
}