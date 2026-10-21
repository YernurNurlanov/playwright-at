package com.automation.tests;

import com.automation.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;

class BuyProductTest extends BaseTest {
    @Test
    void addProductAndVerifyCartBeforeCheckout() {
        homePage.open();
        productPage.openFirstProduct();
        String name = productPage.productName().innerText().trim();
        productPage.addToCart();

        // The cart state can be opened via the header cart link; assert the chosen item is present.
        page.getByRole(com.microsoft.playwright.options.AriaRole.LINK,
            new com.microsoft.playwright.Page.GetByRoleOptions().setName("Cart")).click();
        PlaywrightAssertions.assertThat(page.getByText(name, new com.microsoft.playwright.Page.GetByTextOptions().setExact(false))).isVisible();

        // Checkout requires a valid signed-in customer on the live demo. Do not fake payment completion.
        // The product/cart assertion is deterministic; full payment flow should use a dedicated test account.
        PlaywrightAssertions.assertThat(page).hasURL(java.util.regex.Pattern.compile(".*(checkout|cart).*"));
    }
}
