package com.automation.tests;

import com.automation.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

class CompareProductsTest extends BaseTest {
    private BigDecimal parsePrice(String raw) {
        Matcher matcher = Pattern.compile("[0-9]+(?:[.,][0-9]{1,2})?").matcher(raw.replace(",", ""));
        assertTrue(matcher.find(), "Could not parse product price: " + raw);
        return new BigDecimal(matcher.group().replace(",", "."));
    }

    @Test
    void captureAndCompareTwoProducts() {
        homePage.open();
        productPage.openFirstProduct();
        String firstName = productPage.productName().innerText().trim();
        BigDecimal firstPrice = parsePrice(productPage.productPrice().innerText());
        page.goBack();

        var productLinks = page.locator("a[data-test='product-name']");
        PlaywrightAssertions.assertThat(productLinks.first()).isVisible();
        PlaywrightAssertions.assertThat(productLinks.nth(1)).isVisible();
        productLinks.nth(1).click();

        String secondName = productPage.productName().innerText().trim();
        BigDecimal secondPrice = parsePrice(productPage.productPrice().innerText());

        assertNotEquals(firstName, secondName, "The test must capture two different products");
        assertTrue(firstPrice.signum() > 0 && secondPrice.signum() > 0, "Both product prices must be positive");

        String comparison = firstPrice.compareTo(secondPrice) < 0
            ? firstName + " is cheaper than " + secondName
            : firstPrice.compareTo(secondPrice) > 0
                ? firstName + " is more expensive than " + secondName
                : "Both products have the same price";
        assertFalse(comparison.isBlank());
        System.out.printf("Product comparison: %s (%s) vs %s (%s). Result: %s%n",
            firstName, firstPrice, secondName, secondPrice, comparison);
    }
}
