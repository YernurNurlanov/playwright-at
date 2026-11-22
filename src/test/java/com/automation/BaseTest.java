package com.automation;

import com.automation.pages.*;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public abstract class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected AccountPage accountPage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected boolean isCI = Boolean.parseBoolean(System.getenv("CI"));

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-test");
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        context = browser.newContext(new Browser.NewContextOptions().setBaseURL(baseUrl()));
        page = context.newPage();
        page.setDefaultTimeout(isCI ? 15_000 : 10_000);
        homePage = new HomePage(page);
        registerPage = new RegisterPage(page);
        loginPage = new LoginPage(page);
        accountPage = new AccountPage(page);
        productPage = new ProductPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);
        forgotPasswordPage = new ForgotPasswordPage(page);
    }

    protected String baseUrl() {
        String configured = System.getProperty("baseURL");
        if (configured == null || configured.isBlank()) {
            configured = System.getenv("BASE_URL");
        }
        return configured == null || configured.isBlank() ? "https://practicesoftwaretesting.com" : configured;
    }

    @AfterEach
    void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
