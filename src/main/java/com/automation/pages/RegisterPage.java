package com.automation.pages;

import com.automation.factory.UserFactory.User;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RegisterPage extends BasePage {
    public RegisterPage(Page page) { super(page); }
    public void open() { page.navigate("/auth/register"); }

    public void register(User user) {
        page.getByLabel("First name").fill(user.firstName());
        page.getByLabel("Your last name").fill(user.lastName());
        page.getByLabel("YYYY-MM-DD").fill(user.dateOfBirth());
        // Country may be a native select on the current site.
        page.getByLabel("Country").fill(user.country());
        page.getByLabel("Your Postcode").fill(user.postalCode());
        page.getByLabel("e.g. 42").fill(user.houseNumber());
        page.getByLabel("Your Street").fill(user.street());
        page.getByLabel("Your City").fill(user.city());
        page.getByLabel("Your State").fill(user.state());
        page.getByLabel("Your phone").fill(user.phone());
        page.getByLabel("Your email").fill(user.email());
        page.getByLabel("Your password").fill(user.password());
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click();
    }
}
