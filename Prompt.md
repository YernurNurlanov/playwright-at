# Prompt.md

**AI Tool Used:** ChatGPT (GPT-4o / Advanced Reasoning)  
**Date:** 20 October 2026

## Exact Prompt Given to the AI Tool:

Act as an expert Test Automation Architect specializing in Microsoft Playwright with Java and JUnit 5. I need a robust, scalable test automation framework built for the target website: https://practicesoftwaretesting.com/
The framework must follow these strict architecture guidelines:
1. Use POM with clean separation of concerns, structured under a Maven project layout.
2. Configure Maven dependencies for com.microsoft.playwright:playwright and org.junit.jupiter:junit-jupiter.
3. Use a BaseTest setup (BaseTest.java) to manage browser lifecycle (Chromium) and initialize page objects per test method.
4. Create a dynamic test data factory (UserFactory.java) using timestamps (System.currentTimeMillis()) to ensure registration emails are unique every single run.
5. Implement 5 independent test spec classes covering:
   - Register a new customer(A unique new account is created and the user ends up signed in or on the login page)
   - Log in (Valid credentials reach the account page; invalid ones show the error and do not)
   - Buy a product (Add to cart → cart shows the right item and quantity → checkout completes)
   - Compare two products (Open two products, capture name and price, assert the comparison you state)
   - Reset a password (The forgot-password flow gives the expected confirmation for a known email, and an error for an invalid one)
6. Strictly adhere to: No waitForTimeout, No CSS classes or positional XPaths in locators, and ensure all actions and assertions use proper web-first retry assertions.