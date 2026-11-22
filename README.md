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

## CI

[![Playwright Java CI](https://github.com/YernurNurlanov/playwright-at/actions/workflows/playwright.yml/badge.svg?branch=main)](https://github.com/YernurNurlanov/playwright-at/actions/workflows/playwright.yml)

**CI system:** GitHub Actions. Selected as the native CI system for GitHub repositories.
**Pipeline** (`.github/workflows/playwright.yml`):
- runs automatically on every `push` and `pull_request`, as well as manually via `workflow_dispatch`;
- sets up JDK 23 (`temurin`) with Maven dependency caching (`cache: ‘maven’`);
- compiles the project (`mvn test-compile`) and installs Playwright with system dependencies (`mvn exec:java -Dexec.mainClass=“com.microsoft.playwright.CLI” -Dexec.args=“install --with-deps”`);
- divides the test suite into 3 parallel groups (`RegisterAndLogin`, `ProductAndCompare`, `ResetPassword`) using the `matrix.testGroup` matrix and the Maven `-Dtest=...` flag;
- retains Maven Surefire reports (`junit-report-*`) and build/test artifacts (`test-artifacts-*`) for 14 days (`retention-days: 14`, `if: ${{ !cancelled() }}`).

**CI settings** (`BaseTest.java`, при `CI=true`):
- Automatic detection of the CI environment via `System.getenv(“CI”)`;
- Increased timeout for waiting for elements in CI mode (up to 25–60 seconds) and diagnostic logging of `baseURL`, the final URL, and `page.title()`.

### Runtime & CI Diagnostics

| Configuration | Total run time | Run tests step (slowest shard) | Run |
| --- | --- | --- | --- |
| 1 job, no sharding | ~4 min 30 s | ~3 min 50 s | [run](https://github.com/YernurNurlanov/playwright-at/actions) |
| 3 parallel groups (matrix) | ~3 min 40 s | ~3 min 07 s | [run](https://github.com/YernurNurlanov/playwright-at/actions/runs/45040609202) |

**Conclusion:** Инфраструктура CI (триггеры `push`/`pull_request`, кэш Maven, установка браузеров, матричный запуск в 3 параллельных джобах и выгрузка артефактов на 14 дней) работает корректно. Локально все 7 тестов проходят успешно (`BUILD SUCCESS`, ~1 мин 06 сек), однако на раннерах GitHub Actions (`ubuntu-latest`) публичный стенд `https://practicesoftwaretesting.com` перехватывает запросы из облачных IP-подсетей страницей проверки Cloudflare (`Title: Just a moment...`), из-за чего DOM-элементы форм (`first-name`, `email`, `product-name`) не рендерятся и тесты завершаются по `TimeoutError` даже при таймауте 60 секунд.

### Running locally

```bash
mvn clean test