# Playwright Java framework

Java 17 + Maven + Microsoft Playwright + JUnit 5. POM keeps browser setup, data generation, page interactions, and test intent separate.

## Run
```bash
mvn test
```
Install browser binaries on a new machine:
```bash
mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```
If you do not have the exec plugin configured, run `java -cp ... com.microsoft.playwright.CLI install chromium` or use Playwright's documented CLI installation command.

Override base URL:
```bash
mvn test -DbaseURL=https://practicesoftwaretesting.com
```

## Important constraints / known assumptions
- No `waitForTimeout` is used. Assertions use Playwright Java's retrying `Assertions.assertThat`.
- Locators use accessible labels/roles or stable `data-test` attributes; no CSS class selectors or positional XPath.
- Registration requires all fields shown by the live site. Country selection and address fields may evolve; check current accessible labels if the website changes.
- The public demo's checkout/payment and password reset behaviour can change. A browser-only test cannot verify email delivery or complete an actual external payment. The checkout scenario uses the site's demo payment option and should be adjusted to the current UI if the site changes.
- The site does not expose a native side-by-side product comparison feature consistently. `CompareProductsTest` compares the captured product names and prices and asserts a deterministic price ordering; this tests the comparison logic, not a website comparison widget.
- Valid login requires credentials for an account that already exists. Set `E2E_USER_EMAIL` and `E2E_USER_PASSWORD` environment variables for the login happy path. The registration test generates a unique account but does not share it with other tests.
- Password reset APIs commonly avoid revealing whether an email exists. The reset test checks confirmation for a known email and a syntactically invalid address; do not assert different messages for unknown vs known valid email unless the application explicitly guarantees that behaviour.
- Treat this as a maintainable starting framework, not a claim that every live-site selector and business flow has been verified end-to-end. Keep locators grounded in the live DOM and update them when the application changes.
