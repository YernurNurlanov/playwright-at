# Description

## List of Test Cases Automated

1. Register a New Customer (RegisterTest.java)

- Objective: Verify that a new user can successfully register with unique details.
- Steps: Navigate to register page, fill out valid customer details (using a dynamic data factory to guarantee a unique email per run, with correct field ordering for the Angular SPA), and submit.
- Expected Result: Account is created successfully and user is redirected to the login page.

2. Log In (LoginTest.java)

- Objective: Verify authentication behavior for both valid and invalid credentials.
- Steps: Valid: Enter seeded customer credentials (customer2@practicesoftwaretesting.com / welcome01). Invalid: Enter dynamically generated invalid credentials and submit.
- Expected Result: Valid login redirects successfully to the account page (/account). Invalid login displays an error message ("Invalid email or password") and stays on the login view.

3. Buy a Product (BuyProductTest.java)

- Objective: Verify adding a product to the cart and validating cart state before checkout.
- Steps: Open the home page, select and open the first product from the catalog, grab its name, click "Add to cart", navigate to the cart/checkout page, and verify the correct item is present.
- Expected Result: Cart displays the correct selected product name and the application redirects/stays on the cart/checkout view.

4. Compare Two Products (CompareProductsTest.java)

- Objective: Verify capturing and comparing prices of two different products.
- Steps: Open the catalog, select the first product to capture its name and parsed price, navigate back to the home page using page.goBack(), select a second product to capture its name and price, and compare them programmatically.
- Expected Result: Both products are confirmed to be different, their prices are successfully parsed as positive BigDecimal values, and a comparative price analysis result is logged to the console.

5. Reset Password (ResetPasswordTest.java)

- Objective: Verify the forgot-password recovery workflow and validation.
- Steps: Navigate to the forgot-password page, test submission with a malformed email format, and test with a valid registered email using forgotPasswordPage.submit().
- Expected Result: Malformed email displays a validation error message ("email is invalid"). Valid registered email displays a confirmation/success message.